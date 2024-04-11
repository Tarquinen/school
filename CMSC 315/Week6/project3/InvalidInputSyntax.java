public class InvalidInputSyntax extends Exception {
   private String message;

   public InvalidInputSyntax() {
      this("Invalid syntax");
   }

   public InvalidInputSyntax(String message) {
      super(message);
      this.message = message;
   }


   @Override
   public String getMessage() {
      return message;
   }
}