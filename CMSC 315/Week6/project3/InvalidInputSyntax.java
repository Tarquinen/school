public class InvalidInputSyntax extends Exception{
   private String message;

   public InvalidInputSyntax(String message) {
      super(message);
      this.message = message;
   }

   public InvalidInputSyntax() {
      this("Invalid syntax");
   }

   @Override
   public String getMessage() {
      return message;
   }
}