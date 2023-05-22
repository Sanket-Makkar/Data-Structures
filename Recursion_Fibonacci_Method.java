/**
 * Programming Assignment Problem 1
 * -- iterative and recursive fibonacci nth term approaches
 * Goal: Write a recursive and an iterative method to 
 *       find the nth fibonacci term, and print that term in
 *       each method. For instance, an input of 3 into the 
 *       methods should print the number 1. 
 */
//Important Note: when considering fibonacci numbers I start the sequence as 0, 1, 1...
public class Fibonacci
{
    //iterative approach
    //Note on Time Complexity: The time complexity is O(n)
    public int fibIterative(int n){
        if (n < 0)
            return -1;
        int onebehind = 1, twobehind = 1, current = 1;
        
        if (n == 2){
            System.out.println(1);
            return 1;
        }
        else if (n == 1){
            System.out.println(0);
            return 0;
        }
        int fibval = 0;
        for (int z = 3; z < n; z++){//iterates through n-3 times, = n times
            current = twobehind + onebehind;
            twobehind = onebehind;
            onebehind = current;
        }
        System.out.println(current);    
        return current;
    }
    
    //recursive approach
    //Note on complexity: The time complexity is O(n^2)
    public int fibRecursive(int n){
        int total = 0;
        if (n <= 3){    
            return 1;
        }
        total = fibRecursive(n - 1) + fibRecursive(n - 2);//creates a tree-like set of method calls, resulting in n^2 complexity
        return total;
    }
    
    //main method
    public static void main (String [] args){
        //creating instance of Fibonacci Class
        Fibonacci a1 = new Fibonacci();
        //calling the iterative fibonacci number approach, as O(n) is more efficient than O(n^2)
        a1.fibIterative(10);
    }
}
