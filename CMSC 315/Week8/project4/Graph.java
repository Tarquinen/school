import java.util.*;

public class Graph {
   protected List<Vertex> vertices;
   protected List<List<Edge>> neighbors;
   protected List<String> vertexNames;
   protected List<Integer> dfsSearchOrder;
   protected List<Integer> bfsSearchOrder;
   protected List<List<Integer>> cycles;
   protected boolean hasCycles;

   class Edge {
      public int u;
      int v;

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
      this.vertexNames = new ArrayList<>();
      this.dfsSearchOrder = new ArrayList<>();
      this.bfsSearchOrder = new ArrayList<>();
   }

   // adds a vertex to the graph
   public boolean addVertex(Vertex v) {
      if (!vertices.contains(v)) {
         vertices.add(v);
         vertexNames.add(v.getName());
         neighbors.add(new ArrayList<Edge>());
         return true;
      }
      else return false;
   }

   // removes a vertex from the graph
   public boolean removeVertex(Vertex v) {
      if (vertices.contains(v)) {
         int index = getIndex(v);
         List<Edge> edges = new ArrayList<>(neighbors.get(index)); // Copy to avoid ConcurrentModificationException
            for (Edge e : edges) {
               removeEdge(e);
            }
         vertices.set(index, null);
         vertexNames.remove(v.getName());
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
      if (e.u < 0 || e.u > getSize() - 1)
         throw new IllegalArgumentException("No such index: " + e.u);
      if (e.v < 0 || e.v > getSize() - 1)
         throw new IllegalArgumentException("No such index: " + e.u);

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
      if (e.u < 0 || e.u > getSize() - 1)
         throw new IllegalArgumentException("No such index: " + e.u);
      if (e.v < 0 || e.v > getSize() - 1)
         throw new IllegalArgumentException("No such index: " + e.u);

      boolean removed = false;
      Iterator<Edge> it = neighbors.get(e.u).iterator();
      while (it.hasNext()) {
         Edge current = it.next();
         if (current.equals(e)) {
            it.remove();
            removed = true;
            break;
         }
      }
      if (removed) {
         Iterator<Edge> reverseIt = neighbors.get(e.v).iterator();
         while (reverseIt.hasNext()) {
            Edge reverseEdge = reverseIt.next();
            if (reverseEdge.u == e.v && reverseEdge.v == e.u) {
               reverseIt.remove();
               break;
            }
         }
      }

      return removed;
   }

   // returns the depth-first search list of vertices and their parents
   public int[] dfs() {
      int startVertex = 0;
      for (int i = 0; i < getSize(); i++) {
         if (vertices.get(i) != null) {
            startVertex = i;
            break;
         }
      }
      dfsSearchOrder.clear();
      hasCycles = false;
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
         if (!isVisited[neighbor]) {
            parent[neighbor] = v;
            dfs(neighbor, parent, isVisited);
         }
         else if(parent[v] != neighbor) {
            hasCycles = true;
         }
      }
   }

   public List<List<Integer>> findAllCycles() {
      List<List<Integer>> allCycles = new ArrayList<>();
      boolean[] isVisited = new boolean[getSize()];
      List<Integer> currentPath = new ArrayList<>();

      for (int i = 0; i < getSize(); i++) {
         if (!isVisited[i] && vertices.get(i) != null) {
               findCyclesDFS(i, i, isVisited, currentPath, allCycles);
         }
      }
      allCycles = removeDuplicateCycles(allCycles);
      // // print all cycles
      // for (List<Integer> cycle : allCycles) {
      //    for (int i = 0; i < cycle.size(); i++) {
      //       if (i != cycle.size() - 1)
      //          System.out.print(getVertex(cycle.get(i)).getName() + " -> ");
      //       else
      //          System.out.print(getVertex(cycle.get(i)).getName() + " -> " + getVertex(cycle.get(0)).getName());
      //    }
      //    System.out.println();
      // }
      return allCycles;
   }

   private void findCyclesDFS(int start, int current, boolean[] isVisited, List<Integer> currentPath, List<List<Integer>> allCycles) {
      isVisited[current] = true;
      currentPath.add(current);

      for (int neighbor : getNeighbors(current)) {
         if (neighbor == start && currentPath.size() > 2) {
               // Add the current path as a new cycle
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

   // returns the breadth-first search list of vertices and their parents
   public int[] bfs() {
      int startVertex = 0;
      for (int i = 0; i < getSize(); i++) {
         if (vertices.get(i) != null) {
            startVertex = i;
            break;
         }
      }
      bfsSearchOrder.clear();
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

   // returns true if the graph has cycles
   public boolean hasCycles() {
      dfs();
      return hasCycles;
   }

   // returns true if the graph is connected
   public boolean isConnected() {
      dfs();
      return dfsSearchOrder.size() == getCurrentSize();
   }

   // returns the amount of vertices in the graph currently
   public int getCurrentSize() {
      int count = 0;
      for (Vertex v: vertices) {
         if (v != null) count++;
      }
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
      for (int i = 0; i < vertices.size(); i++) {
         if (vertices.get(i) == null) continue;
         if (vertices.get(i).getX() == v.getX() && vertices.get(i).getY() == v.getY()) {
               return i;
         }
      }
      return -1;
   }

   // returns the index of the specified vertex coordinates
   public int getIndex(double x, double y) {
      for (int i = 0; i < vertices.size(); i++) {
         if (vertices.get(i) == null) continue;
         if (vertices.get(i).getX() == x && vertices.get(i).getY() == y) {
               return i;
         }
      }
      return -1;
   }

   // returns the neighbors of the specified vertex
   public List<Integer> getNeighbors(int index) {
      List<Integer> result = new ArrayList<>();
      for (Edge e: neighbors.get(index))
         result.add(e.v);
      Collections.sort(result);
      return result;
   }

   // returns a list of all the vertex names
   public List<String> getVertexNames() {
      return vertexNames;
   }
}
