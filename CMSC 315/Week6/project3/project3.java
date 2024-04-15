/**
 * Daniel Smolsky
 * Programming Project 3 - Binary Search Tree
 * March 15, 2024
 * The project3 class is the main entry point for the binary search tree project.
 * It allows the user to input a string representation of a binary tree and
 * determines whether the tree is a balanced binary search tree, an unbalanced
 * binary search tree, or not a binary search tree at all. It also prints the
 * original tree and a balanced version of the tree if necessary.
 */

import java.util.Scanner;

public class project3 {
   public static void main(String[] args) throws InvalidInputSyntax {

      while (true) {
         Scanner input = new Scanner(System.in);
         System.out.print("Enter a binary tree: ");
         String s = input.nextLine();

         Tree tree = new Tree(s);

         tree.printTree();

         if (tree.isBinarySearchTree()) {
            if (tree.isBalanced())
               System.out.println("It is a balanced binary search tree");
            else {
               System.out.println("It is a binary search tree but it is not balanced");
               Tree balancedTree = new Tree(tree.getInorderList());
               balancedTree.printTree();
               System.out.println("Original tree has height " + tree.getHeight());
               System.out.println("Balanced tree has height " + balancedTree.getHeight());
            }
         }
         else {
            System.out.println("It is not a binary search tree");
            Tree binaryTree = new Tree(tree.getInorderList());
            binaryTree.printTree();
            System.out.println("Original tree has height " + tree.getHeight());
            System.out.println("Balanced tree has height " + binaryTree.getHeight());
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
         if (c == 'N' || c == 'n') {
            input.close();;
            break;
         }
      }
   }
}