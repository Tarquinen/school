import java.util.Scanner;

public class project3 {
   public static void main(String[] args) throws InvalidInputSyntax {
      String testString1 = "(53 (28 (11 * *) (41 * *)) (83 (67 * *) *))";
      String testString2 = "(63 (51 (20 (13 * *) *) *) *)";
      String testString3 = "(13 (53 * *) (11 (59 * *) *))";
      String testString4 = "(53 * (83 (67 * *) *))";

      while (true) {
         Scanner input = new Scanner(System.in);
         System.out.println("Enter a binary tree: ");
         // String s = input.nextLine();

         Tree tree = new Tree(testString3);

         // print the tree
         tree.printTree();
         System.out.println("Inorder list: " + tree.getInorderList());

         if (tree.isBinarySearchTree()) {
            if (tree.isBalanced())
               System.out.println("It is a balanced binary search tree");
            else {
               System.out.println("It is a binary search tree but it is not balanced");
               // print balanced tree
               System.out.println("Original tree has height " + tree.getHeight());
               // print "Balanced tree has height y"
            }
         }
         else {
            System.out.println("It is not a binary search tree");
            // print binary tree
            System.out.println("Original tree has height " + tree.getHeight());
            // print "Balanced tree has height y"
         }

         System.out.print("More trees? Y or N: ");
         char c;
         while (true) {
            c = input.next().charAt(0);
            if (c != 'Y' && c != 'y' && c != 'N' && c != 'n') {
               System.out.println("Y or N: ");
            } 
            else
               break;
         }
         if (c == 'N' || c == 'n')
            break;

         input.close();
      }
   }
}