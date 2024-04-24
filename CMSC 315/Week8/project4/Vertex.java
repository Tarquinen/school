/**
 * Daniel Smolsky
 * Programming Project 4 - Graphs
 * April 21, 2024
 * The Vertex class represents a vertex in a graph, with an (x,y) coordinate and a unique name.
 * Each vertex has a static count that is incremented with each new vertex creation.
 * The class provides methods to access the vertex's coordinates and name, as well as a toString() method for easy printing.
 */

public class Vertex {
   private double x, y;
   private String name;
   private static int count = 0;

   public Vertex(double x, double y) {
      this.x = x;
      this.y = y;
      this.name = generateName();
      count++;
   }

   public double getX() {
      return x;
   }

   public double getY() {
      return y;
   }

   public String getName() {
      return name;
   }

   @Override
   public boolean equals(Object o) {
      if (o == this) return true;
      if (!(o instanceof Vertex)) return false;
      Vertex v = (Vertex) o;
      return v.x == x && v.y == y;
   }   

   @Override
   public String toString() {
      return "Vertex " + name + " at (" + x + ", " + y + ")";
   }

   private String generateName() {
      int letters = count / 26;
      String name = "";
      
      if (count < 26) {
         name = Character.toString((char)(count + 65));
      } else {
         name += Character.toString((char)(letters + 64));
         name += Character.toString((char)((count % 26) + 65));
      }
      
      return name;
   }
}
