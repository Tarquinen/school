import java.util.Stack;
import java.util.ArrayList;

public class Tree {
   protected ArrayList<TreeNode> inorderList = new ArrayList<>();
   protected TreeNode root;
   protected int size = 0;

   public static class TreeNode {
      protected int element;
      protected int level;
      protected TreeNode left;
      protected TreeNode right;

      public TreeNode(int e) {
         element = e;
      }
   }

   public Tree() {

   }

   public Tree (String s) throws InvalidInputSyntax {
      validateString(s); // check the string isn't missing parenthesis and only has integers

      // convert the string to an array of tokens
      String[] tokens = parseStringToArray(s);

      // create a list of branches in the tree with each branch containing a list of integers
      int treeBranch = 0;
      ArrayList<ArrayList<Integer>> branchList = new ArrayList<>();

      for (String token : tokens) {
         // create the root node
         if (root == null) {
            Integer value = toNumber(token);
            if (value != null) {
               root = createNewNode(value);
            }
         }
         
         // increase the tree branch for each left parenthesis and * symbol
         if (token.equals("(") || token.equals("*")) {
            treeBranch++;
         }
         else if (token.equals(")")) {
            treeBranch--;
         }
         
         // if there are not enough branches in the list, add a new level
         if (branchList.size() < treeBranch) {
            branchList.add(new ArrayList<Integer>());
         }

         // add the token to the list at the current branch 
         if (token.equals("*")) {
            branchList.get(treeBranch - 1).add(null);
            treeBranch--; // decrease back down to the parent branch 
         }
         else if (toNumber(token) != null) {
            branchList.get(treeBranch - 1).add(toNumber(token));
         }
      }

      validateList(branchList); // check the list has the correct number of nodes in each branch
      createTreeFromList(root, branchList, 0, 0); // create the tree from the list
      inorder(); // create the inorder list used to check if the tree is a binary search tree
   }

   // create the tree from the list of tree branches recursively
   private void createTreeFromList(TreeNode parentNode, ArrayList<ArrayList<Integer>> inputList, 
                                    int parentCoordslevel, int parentCoordsSlot) {

      // count the number of nulls in the parent's row
      int nullCount = 0;
      for (int i = 0; i < parentCoordsSlot; i++) {
         if (inputList.get(parentCoordslevel).get(i) == null) {
            nullCount++;
         }
      }

      parentCoordsSlot -= nullCount;
      int childCoordsLevel = parentCoordslevel + 1;
      int leftChildSlot = parentCoordsSlot * 2;
      int rightChildSlot = leftChildSlot + 1;

      // recursively create the tree from the list
      try {
         int leftChildElement = inputList.get(childCoordsLevel).get(leftChildSlot); // throws error if child is null or doesn't exist
         TreeNode leftChildNode = createNewNode(leftChildElement);
         leftChildNode.level = childCoordsLevel;
         parentNode.left = leftChildNode;
         createTreeFromList(leftChildNode, inputList, childCoordsLevel, leftChildSlot);
      }
      catch (IndexOutOfBoundsException | NullPointerException e) {
      }

      try {
         int rightChildElement = inputList.get(childCoordsLevel).get(rightChildSlot);
         TreeNode rightChildNode = createNewNode(rightChildElement);
         rightChildNode.level = childCoordsLevel;
         parentNode.right = rightChildNode;
         createTreeFromList(rightChildNode, inputList, childCoordsLevel, rightChildSlot);
      }
      catch (IndexOutOfBoundsException | NullPointerException e) {
      }

   }

   // check if the tree is a binary search tree
   public boolean isBinarySearchTree() {
      for (int i = 0; i < inorderList.size() - 1; i++) {
         if (inorderList.get(i).element > inorderList.get(i + 1).element) {
            return false;
         }
      }
      return true;
   }

   // helper method for checking if the tree is balanced
   public boolean isBalanced() {
      return isBalanced(root);
   }

   // check if the tree is balanced recursively
   protected boolean isBalanced(TreeNode root) {
      if (root == null) return true;
      int leftHeight = getHeight(root.left);
      int rightHeight = getHeight(root.right);

      if (Math.abs(leftHeight - rightHeight) > 1) return false;
      return isBalanced(root.left) && isBalanced(root.right);
   }

   // helper method for getting the height of the tree
   public int getHeight() {
      return getHeight(root);
   }

   // get the height of the tree recursively
   protected int getHeight(TreeNode root) {
      if (root == null)
         return -1;
      return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
   }

   // helper method for inorder traversal
   public void inorder() {
      inorderList.clear();
      inorder(root);
   }
   
   // create the inorder list of the tree used to check if the tree is a binary search tree
   protected void inorder(TreeNode root) {
      if (root == null) return;
      inorder(root.left);
      inorderList.add(root);
      inorder(root.right);
   }

   // helper method for printing the tree
   public void printTree() {
      printTree(root);
   }

   //prints the visual representation of the tree
   protected void printTree(TreeNode root) {
      if (root == null) return;
      for (int i = 0; i < root.level; i++) {
         System.out.print("   ");
      }
      System.out.println(root.element);
      printTree(root.left);
      printTree(root.right);
   }

   // return an array list of the values in the tree
   public ArrayList<Integer> getInorderList() {
      ArrayList<Integer> result = new ArrayList<>();
      for (TreeNode node : inorderList) {
         result.add(node.element);
      }
      return result;
   }

   // convert a string to an integer, or return null if the string is not an integer, used in constructor
   private Integer toNumber(String s) {
      try {
         return Integer.parseInt(s);
      }
      catch (NumberFormatException e) {
         return null;
      }
   }

   // convert a string to an array of tokens
   private static String[] parseStringToArray(String s) {
      StringBuilder sb = new StringBuilder();
      ArrayList<String> result = new ArrayList<>();
      
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == ' ') {
            if (sb.length() > 0) {
               result.add(sb.toString());
               sb.setLength(0);
            }
         } 
         else if (c == '(' || c == ')' || c == '*') {
            if (sb.length() > 0) {
               result.add(sb.toString());
               sb.setLength(0);
            }
            result.add(String.valueOf(c));
         } 
         else {
            sb.append(c);
         }
      }
      
      if (sb.length() > 0) {
         result.add(sb.toString());
      }
      
      return result.toArray(new String[0]);
   }
   
   // used in the constructor
   private void validateString(String s) throws InvalidInputSyntax {
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         // track the parenthesis in a stack
         if (c == '(') {
            stack.push(c);
         }
         else if (c == ')') {
            if (stack.isEmpty()) {
               throw new InvalidInputSyntax("Missing Left Parenthesis");
            }
            stack.pop();
         }
         // check if the data is an integer
         else if (!Character.isDigit(c) && c != '*' && c != ' ') {
            throw new InvalidInputSyntax("Data is Not an Integer");
         }
      }
      // if the stack is not empty, then there is a missing parenthesis
      if (!stack.isEmpty()) {
         if (stack.pop() == ')') {
            throw new InvalidInputSyntax("Missing Left Parenthesis");
         }
         else {
            throw new InvalidInputSyntax("Missing Right Parenthesis");
         }
      }
   }

   // used in the constructor
   protected void validateList(ArrayList<ArrayList<Integer>> TreeList) throws InvalidInputSyntax {
      if (TreeList.get(0).size() != 1)
         throw new InvalidInputSyntax("Root branch should have 1 Node");
   
      for (int i = 0; i < TreeList.size() - 1; i++) {
         // get the num of elements in the branch that are not null
         int notNull = 0;
         for (int j = 0; j < TreeList.get(i).size(); j++) 
            if (TreeList.get(i).get(j) != null) notNull ++; 
   
         // check the next branch has twice as many elements
         if (notNull * 2 > TreeList.get(i + 1).size()) {
            throw new InvalidInputSyntax("Branch " + (i + 1) + " is missing nodes");
         }
         else if (notNull * 2 < TreeList.get(i + 1).size()) {
            throw new InvalidInputSyntax("Branch " + (i + 1) + " has extra nodes");
         }
      }
   }

   // used in the constructor to create a new node
   protected TreeNode createNewNode(int e) {
      return new TreeNode(e);
   }
}


