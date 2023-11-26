package Week6;

class myExample {
        // public variable
        public int exampleNumber;
 
        // constructor
        public myExample()
        {
          exampleNumber = 1;
        }
      }

public class disc {
    public static void main (String[] args) {

        /*
        the following is used to demonstrate how to pass an object to a method
        and update its public variable in a pass-by-reference manner
        */

        //create an object of myExample
        myExample example = new myExample();
        //access public variable and print it
        System.out.println("original number (pass by reference) " + example.exampleNumber);
        //call update method
        update(example);
        //print the updated number
        System.out.println("updated number (pass by reference) " + example.exampleNumber);


         /*
        the following is used to demonstrate how to pass an object to a method
        and update its private variable in a pass-by-value manner
        */

        //create a primitive variable
        int exampleNumber = 1;
        //access primitive variable and print it
        System.out.println("original number (pass by value) " + exampleNumber);
        //call update method
        update(exampleNumber);
        //print the updated number
        System.out.println("updated number (pass by value) " + exampleNumber);

        /* as you can see, the primitive variable is not updated because it is passed by
        value and a copied version of the variable is passed to the method instead of the 
        original 
        */
    }

    public static int update (myExample example) {
        //update public variable
        example.exampleNumber ++;
        return example.exampleNumber;
    }

    public static int update (int exampleNumber) {
        //update primitive variable
        exampleNumber ++;
        return exampleNumber;
    }
}


