import java.util.*;

public class Graph {
   protected List<Vertex> vertices = new ArrayList<>();
   protected List<List<Edge>> neighbors = new ArrayList<>();
   protected List<String> names = new ArrayList<>();

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
         System.out.println("vertice index: " + getIndex(v));
         return true;
      }
      else return false;
   }

   public boolean removeVertex(Vertex v) {
      if (vertices.contains(v)) {
         int index = getIndex(v);
         System.out.println("removing vertice at index: " + getIndex(v) + " named " + v.getName());
         List<Edge> edges = new ArrayList<>(neighbors.get(index)); // Copy to avoid ConcurrentModificationException
            for (Edge e : edges) {
               removeEdge(e);
            }
         vertices.set(index, null);
         
         // print out the vertices and neighbors
         System.out.println("vertices: ");
         for (Vertex vertex: vertices) {
            if (vertex != null) {
               System.out.println(vertex);
            }
            else {
               System.out.println("vertex removed");
            }
         }
         System.out.println("neighbors: ");
         for (List<Edge> neighbor: neighbors) {
            System.out.println(neighbor);
         }
         return true;
      }
      return false;
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
            System.out.println("removing edge: " + e + " success");
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
               System.out.println("removing reverse edge: " + reverseEdge + " success");
               break;
            }
         }
      }

      return removed;
   }



   private void createAdjacencyList(int[][] edges, int numberOfVertices) {
      for (int i = 0; i < edges.length; i++)
         addEdge(edges[i][0], edges[i][1]);
   }

   public boolean addEdge(int u, int v) {
      return addEdge(new Edge(u, v));
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
      return vertices.indexOf(v);
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
