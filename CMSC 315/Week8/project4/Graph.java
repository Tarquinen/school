/**
 * Daniel Smolsky
 * Programming Project 4 - Graphs
 * April 21, 2024
 * The Graph class represents a graph data structure, consisting of vertices and edges.
 * It provides methods to add vertices, add edges, perform depth-first search (DFS) and
 * breadth-first search (BFS), and detect cycles in the graph.
 */

import java.util.*;

public class Graph {
   protected List<Vertex> vertices;
   protected List<List<Edge>> neighbors;
   protected List<Integer> dfsSearchOrder;
   protected List<Integer> bfsSearchOrder;
   protected boolean hasCycles;
   protected List<List<Integer>> allCycles;

   class Edge {
      protected int u;
      protected int v;

      public Edge(int u, int v) {
         this.u = u;
         this.v = v;
      }

      @Override
      public boolean equals(Object o) {
         return u == ((Edge)o).u && v == ((Edge)o).v;
      }

      @Override
      public String toString() {
         return "(" + u + ", " + v + ")";
      }
   }

   // default constructor
   public Graph() {
      this.vertices = new ArrayList<>();
      this.neighbors = new ArrayList<>();
      this.dfsSearchOrder = new ArrayList<>();
      this.bfsSearchOrder = new ArrayList<>();
   }

   // adds a vertex to the graph
   public boolean addVertex(Vertex v) {
      if (!vertices.contains(v)) {
         vertices.add(v);
         neighbors.add(new ArrayList<Edge>());
         return true;
      }
      else return false;
   }

   // removes a vertex from the graph
   public boolean removeVertex(Vertex v) {
      if (vertices.contains(v)) {
         removeEdgesOfVertex(v);
         vertices.set(getIndex(v), null);
         return true;
      }
      return false;
   }
   
   // adds an edge to the graph between vertices u and v
   public boolean addEdge(int u, int v) {
      return addEdge(new Edge(u, v));
   }

   // adds an edge to the graph
   public boolean addEdge(Edge e) {
      if (!neighbors.get(e.u).contains(e)) {
         neighbors.get(e.u).add(e);
         neighbors.get(e.v).add(new Edge(e.v, e.u));
         return true;
      }
      else return false;
   }

   // removes an edge from the graph between vertices u and v
   public boolean removeEdge(int u, int v) {
      return removeEdge(new Edge(u, v));
   }

   // removes an edge from the graph
   public boolean removeEdge(Edge e) {
      boolean removed = neighbors.get(e.u).remove(e);
      if (removed) {
         neighbors.get(e.v).remove(new Edge(e.v, e.u));
      }
      return removed;
   }

   // more efficient method to remove all edges of a vertex than calling removeEdge() for each edge
   public void removeEdgesOfVertex(Vertex v) {
      int vIndex = getIndex(v);
      List<Integer> neighborsOfVertex = getNeighbors(vIndex);
      
      // clears all edges OF the vertex
      neighbors.get(vIndex).clear();

      // clears all edges TO the vertex
      for (int neighbor : neighborsOfVertex) {
         neighbors.get(neighbor).remove(new Edge(neighbor, vIndex));
      }
   }

   // returns the depth-first search list of vertices and their parents
   public int[] dfs() {
      // find the first vertex in the graph
      int startVertex = 0;
      for (int i = 0; i < getSize(); i++) {
         if (vertices.get(i) != null) {
            startVertex = i;
            break;
         }
      }

      // clear previous dfs search order and cycles
      dfsSearchOrder.clear();
      hasCycles = false;

      // initialize parent array and isVisited array
      int[] parent = new int[getSize()];
      Arrays.fill(parent, -1);
      boolean[] isVisited = new boolean[getSize()];

      dfs(startVertex, parent, isVisited);
      return parent;
   }

   // depth-first search helper method
   private void dfs(int v, int[] parent, boolean[] isVisited) {
      isVisited[v] = true;
      dfsSearchOrder.add(v);
      for (int neighbor: getNeighbors(v)) {
         // if the neighbor has not been visited, set the current vertex as the parent and recursively call dfs
         if (!isVisited[neighbor]) {
            parent[neighbor] = v;
            dfs(neighbor, parent, isVisited);
         }
         // if the neighbor has been visited and is not the parent of the current vertex, then there is a cycle
         else if (parent[v] != neighbor) {
            hasCycles = true;
         }
      }
   }
   
   // returns the depth-first search String
   public String getDfsString() {
      dfs();
      String dfsString = "";
      for (int i = 0; i < dfsSearchOrder.size(); i++) {
         if (i != dfsSearchOrder.size() - 1)
            dfsString += getVertex(dfsSearchOrder.get(i)).getName() + ", ";
         else
            dfsString += getVertex(dfsSearchOrder.get(i)).getName();
      }
      return dfsString;
   }

   // returns a list of all the cycles in the graph
   public void findCyclesDFS() {
      allCycles = new ArrayList<>();
      boolean[] isVisited = new boolean[getSize()];
      List<Integer> currentPath = new ArrayList<>();

      for (int i = 0; i < getSize(); i++) {
         if (vertices.get(i) != null) {
            findCyclesDFS(i, i, isVisited, currentPath, allCycles);
         }
      }
      allCycles = removeDuplicateCycles(allCycles);
   }

   // depth-first search helper method to find cycles
   private void findCyclesDFS(int start, int current, boolean[] isVisited, List<Integer> currentPath, List<List<Integer>> allCycles) {
      isVisited[current] = true;
      currentPath.add(current);

      for (int neighbor : getNeighbors(current)) {
         if (neighbor == start && currentPath.size() > 2) {
               // Add the current path to the list of cycles
               allCycles.add(new ArrayList<>(currentPath));
         } else if (!isVisited[neighbor]) {
               findCyclesDFS(start, neighbor, isVisited, currentPath, allCycles);
         }
      }

      // Backtrack
      isVisited[current] = false;
      currentPath.remove(currentPath.size() - 1);
   }

   // removes duplicate cycles from the list of cycles
   private List<List<Integer>> removeDuplicateCycles(List<List<Integer>> allCycles) {
      List<List<Integer>> uniqueCycles = new ArrayList<>();
      Set<List<Integer>> seenCycles = new HashSet<>();

      for (List<Integer> cycle : allCycles) {
         List<Integer> sortedCycle = new ArrayList<>(cycle);
         Collections.sort(sortedCycle);
         if (!seenCycles.contains(sortedCycle)) {
               seenCycles.add(sortedCycle);
               uniqueCycles.add(new ArrayList<>(cycle)); // Add the original cycle
         }
      }
      return uniqueCycles;
   }
   
   // returns true if the graph has cycles, this is more efficient than findCyclesDFS() which returns all cycles
   public boolean hasCycles() {
      dfs();
      return hasCycles;
   }

   // returns the amount of cycles in the graph
   public int getCycleCount() {
      if (this.hasCycles()) {
         if (allCycles == null) findCyclesDFS();
         return allCycles.size();
      }
      else return 0;
   }

   // returns the cycle at the specified index
   public List<Integer> getCycle(int index) {
      if (this.hasCycles()) {
         if (allCycles == null) findCyclesDFS();
         if (index >= 0 && index < allCycles.size())
            return allCycles.get(index);
         else return null;
      }
      else return null;
   }

   // returns the breadth-first search list of vertices and their parents
   public int[] bfs() {
      // find the first vertex in the graph
      int startVertex = 0;
      for (int i = 0; i < getSize(); i++) {
         if (vertices.get(i) != null) {
            startVertex = i;
            break;
         }
      }
      bfsSearchOrder.clear();

      // initialize parent array, isVisited array, and queue
      int[] parent = new int[getSize()];
      Arrays.fill(parent, -1);
      boolean[] isVisited = new boolean[getSize()];
      Queue<Integer> queue = new LinkedList<>();
      queue.offer(startVertex);

      bfs(parent, isVisited, queue);
      return parent;
   }
   
   // breadth-first search helper method
   private void bfs(int[] parent, boolean[] isVisited, Queue<Integer> queue) {
      if (queue.isEmpty()) {
         return;
      }

      int currentVertex = queue.poll();
      isVisited[currentVertex] = true;
      bfsSearchOrder.add(currentVertex);

      for (int neighbor : getNeighbors(currentVertex)) {
         if (!isVisited[neighbor] && !queue.contains(neighbor)) {
            queue.offer(neighbor);
            parent[neighbor] = currentVertex;
         }
      }

      bfs(parent, isVisited, queue);
   }

   // returns the breadth-first search String
   public String getBfsString() {
      bfs();
      // print the bfs search order
      String bfsString = "";
      for (int i = 0; i < bfsSearchOrder.size(); i++) {
         if (i != bfsSearchOrder.size() - 1)
            bfsString += getVertex(bfsSearchOrder.get(i)).getName() + ", ";
         else
            bfsString += getVertex(bfsSearchOrder.get(i)).getName();
      }
      return bfsString;
   }

   // returns true if the graph is connected
   public boolean isConnected() {
      dfs();
      return dfsSearchOrder.size() == getCurrentSize();
   }

   // returns the amount of vertices in the graph currently
   public int getCurrentSize() {
      int count = 0;
      for (Vertex v: vertices) 
         if (v != null) count++;
      return count;
   }

   // returns the amount of vertices in the graph including null (deleted) vertices
   public int getSize() {
      return vertices.size();
   }

   // returns a list of all the vertices in the graph
   public List<Vertex> getVertices() {
      return vertices;
   }

   // returns the vertex at the specified index
   public Vertex getVertex(int index) {
      return vertices.get(index);
   }

   // returns the index of the specified vertex object
   public int getIndex(Vertex v) {
      return vertices.indexOf(v);
   }

   // returns the index of the specified vertex coordinates
   public int getIndex(double x, double y) {
      // can't get index by creating a new vertex object to compare with the list because
      // it will increment the count of vertices and generate a new name, too late to fix now
      for (int i = 0; i < vertices.size(); i++) {
         if (vertices.get(i) == null) continue;
         
         // compare the vertex coordinates
         if (vertices.get(i).getX() == x && vertices.get(i).getY() == y)
               return i;
      }
      return -1;
   }

   // returns the neighbors of the specified vertex
   public List<Integer> getNeighbors(int index) {
      List<Integer> result = new ArrayList<>();
      for (Edge e: neighbors.get(index))
         result.add(e.v);

      // return a sorted list of neighbors
      Collections.sort(result);
      return result;
   }

   // returns a list of all the vertex names
   public List<String> getVertexNames() {
      List<String> vertexNames = new ArrayList<>(); 
      for (Vertex v: vertices) {
         if (v != null) vertexNames.add(v.getName());
      }
      return vertexNames;
   }
}
