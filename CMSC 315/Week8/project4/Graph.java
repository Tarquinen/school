import java.util.*;

public class Graph {
   protected List<Vertex> vertices;
   protected List<List<Edge>> neighbors;
   protected List<String> names;
   protected List<Integer> dfsList;

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

   public Graph() {
      this.vertices = new ArrayList<>();
      this.neighbors = new ArrayList<>();
      this.names = new ArrayList<>();
      this.dfsList = new ArrayList<>();
   }

   public Graph(Vertex[] vertices, int[][] edges) {
      for (int i = 0; i < vertices.length; i++)
         addVertex(vertices[i]);
      createAdjacencyList(edges, vertices.length);
   }

   public boolean addVertex(Vertex v) {
      if (!vertices.contains(v)) {
         vertices.add(v);
         names.add(v.getName());
         neighbors.add(new ArrayList<Edge>());
         return true;
      }
      else return false;
   }

   public boolean removeVertex(Vertex v) {
      if (vertices.contains(v)) {
         int index = getIndex(v);
         List<Edge> edges = new ArrayList<>(neighbors.get(index)); // Copy to avoid ConcurrentModificationException
            for (Edge e : edges) {
               removeEdge(e);
            }
         vertices.set(index, null);
         names.remove(v.getName());
         return true;
      }
      return false;
   }
   
   public boolean addEdge(int u, int v) {
      return addEdge(new Edge(u, v));
   }

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

   public boolean removeEdge(int u, int v) {
      return removeEdge(new Edge(u, v));
   }

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

   public List<Integer> dfs(int v) {
      boolean[] isVisited = new boolean[getSize()];
      Arrays.fill(isVisited, false);
      dfsList.clear();
      dfs(v, isVisited);
      return dfsList;
   }

   private void dfs(int v, boolean[] isVisited) {
      dfsList.add(v);
      isVisited[v] = true;
      for (int neighbor: getNeighbors(v)) {
         if (!isVisited[neighbor]) {
            dfs(neighbor, isVisited);
         }
      }
   }

   public String getDfsString() {
      dfs(0);
      String dfsString = "";
      for (int i = 0; i < dfsList.size(); i++) {
         if (i != dfsList.size() - 1)
            dfsString += getVertex(dfsList.get(i)).getName() + ", ";
            // dfsString += dfsList.get(i) + ", ";
         else
            dfsString += getVertex(dfsList.get(i)).getName();
            // dfsString += dfsList.get(i);
      }
      return dfsString;
   }

   private void createAdjacencyList(int[][] edges, int numberOfVertices) {
      for (int i = 0; i < edges.length; i++)
         addEdge(edges[i][0], edges[i][1]);
   }

   public boolean isConnected() {
      return dfs(0).size() == getSize();
   }

   public int getSize() {
      return vertices.size();
   }

   public List<Vertex> getVertices() {
      return vertices;
   }

   public Vertex getVertex(int index) {
      return vertices.get(index);
   }

   public int getIndex(Vertex v) {
      for (int i = 0; i < vertices.size(); i++) {
         if (vertices.get(i) == null) continue;
         if (vertices.get(i).getX() == v.getX() && vertices.get(i).getY() == v.getY()) {
               return i;
         }
      }
      return -1;
   }

   public int getIndex(double x, double y) {
      for (int i = 0; i < vertices.size(); i++) {
         if (vertices.get(i) == null) continue;
         if (vertices.get(i).getX() == x && vertices.get(i).getY() == y) {
               return i;
         }
      }
      return -1;
   }

   public List<Integer> getNeighbors(int index) {
      List<Integer> result = new ArrayList<>();
      for (Edge e: neighbors.get(index))
         result.add(e.v);
      return result;
   }

   public List<String> getNames() {
      return names;
   }

   public int getDegree(int v) {
      return neighbors.get(v).size();
   }
}
