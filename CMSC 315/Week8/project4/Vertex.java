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

   // temp vertex for comparison, does not increment count
   public Vertex(double x, double y, String name) {
      this.x = x;
      this.y = y;
      this.name = name;
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
