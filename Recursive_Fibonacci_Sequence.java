//Programming Assingment Problem 2
//Important Note: when considering fibonacci numbers I start the sequence as 0, 1, 1...
//Another Note: I chose to use a sorted array in ascending order since it was more efficient for certain methods

public class Fibonacci {
    //iterative approach
    //Note on Time Complexity: The time complexity is O(n)
    //Another Note: I consider the n variable to be the nth term
    //              For example : 1st term = 0, 2nd term = 1, third term = 1
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
    
    ///////////////////////////////////
    //problem 2 beginning
    ///////////////////////////////////

    //creating the array 
    //we will sort this array as elements are added or removed since sorting it allows some operations to be more efficient
    private int[] arr = new int[100];
    
    //setting itemCount to track the number of elements currently in array
    private int itemCount = 0;

    /* Name: add
     * Functionality: Given an input item, add it to the array as an element while maintaining sorted order of array. 
     *                If the array is full, return false. else, return true;
     */
    public boolean add(int item){
        //check if array is full, return false if it is full
        if (itemCount == arr.length)
            return false;
        
        //this will help us know when we have found the right index for our item, as we will sort it into the array by ascending value
        boolean foundIndex = false;

        //this just tracks the index we are looking for 
        int index = 0;
        
        //loop through until we find the right position for our index
        while (foundIndex == false && index < itemCount){
            //once the value of our item is smaller than what is infront of it, we have found our index location
            if (arr[index] > item)
                foundIndex = true;
            else{//otherwise continue and increment index
                index++;
            }
        }
        
        //now that we know the index location, we shift all values after and including the index to the right
        for (int i = itemCount; i >= index; i--){
            arr[i + 1] = arr[i];
        }

        //then we assign our item to its correct index, maintaining the array in ascending sorted order.
        arr[index] = item;
        
        //increase itemCount to make sure element number counted is correct
        itemCount++; 

        //return true to indicate success
        return true;
    }

    /* Name: remove
     * Functionality: Given an index in the array, remove the element at that index and shift all items back into sorted order, leaving no gaps.
     */
    public boolean remove(int index){
        //if the index is out of bounds then return false
        if (index < 0 || index >= itemCount)
            return false;
        //otherwise set the index to 0 (a placeholder value for now)
        arr[index] = 0;

        //all values after the index should be shifted left
        for (int i = index; i < itemCount; i++){
            arr[i] = arr[i + 1];
        }

        //reduce the itemCount to correctly decrease number of elements counted
        itemCount--;
        //return true to confirm success
        return true;
    }

    /* Name: ifContains
     * Functionality: Given an item, scan the array using binary search to determine if it contains the item. If it does, return true. Else, return false.
     */
    public boolean ifContains(int item){
        //the first element index in the list
        int initial = 0;

        //the last element index in the list
        int end = itemCount;

        //the "middle" we look at
        int pos = (initial + end) / 2;

        //loop through, executing binary search
        while (initial <= end){
            //if our item value is larger than middle, recalculate our initial to the middle
            if (arr[pos] < item){
                initial = pos + 1;
            }
            //if our item value is smaller than middle, recalculate our end to the middle
            else if (arr[pos] > item){
                end = pos - 1;
            }
            //otherwise, if we find it then return true to confirm it is contained in the array
            else if (arr[pos] == item)
                return true;
            //finally, recalculate "middle" value to be the new middle depending on what we changed
            pos = (initial + end) / 2;
        }
        //if nothing is found retur false to indicate failure
        return false;
    }

    /* Name: grab
     * NOTE: I was unsure whether I needed to just return the value, or also print it, so I did both
     * Functionality: find a random value in our list and return and print it
     */
    public int grab(){
        //create a random index between 0 and our list size
        int randPoint = (int)(Math.floor(Math.random() * itemCount));
        
        //print out the array value at the random index
        System.out.println(arr[randPoint]);

        //return the array value at the random index
        return arr[randPoint];
    }

    /* Name: numItems
     * NOTE: Once again, I didn't know whether I should print or return the value, so I did both
     * Functionality: Find the number of unique items in the list and print and return it
     */
    public int numItems(){
        //our goal is to find the number of identical values and to return and print the total elements - identical values
        //this gives us the number of unique items

        //this keeps track of the number of similar items
        int numSimilar = 0;

        //this basically is just an index for personal use
        int pos = 0;
        
        //we iterate through the array with a while loop
        while (pos < itemCount - 1){
            //since the array is in sorted order, identical values are next to each other
            //so every time we see two identical values next to each other we increment numSimilar
            if (arr[pos] == arr[pos + 1]){
                numSimilar += 1;
            }
            pos++;
        }
        
        //print total elements - similar elements 
        System.out.println(itemCount - numSimilar);

        //return total elements - similar elements 
        return itemCount - numSimilar;
    }
    
    //main method
    //important to note that: I put both problems 1 and 2 in this method and indicated separation with comments
    public static void main (String [] args){
        //BELOW IS PROBLEM 1
        System.out.println("THIS IS PROBLEM 1");
        
        //we create an instance of Fibonacci
        Fibonacci a1 = new Fibonacci();
        
        //we call the iterative method, as it is more efficient since O(n) is more efficient than O(n^2)
        System.out.print("the fifth fibonacci number is... ");
        a1.fibIterative(5);//this prints out the 5th fibonacci number
        System.out.println("------------------");//separating line between problem 1 and problem 2 below
        System.out.println();//white space for viewing ease
        /////////////////////////////////////////////////////////////////////////////////

        //BELOW IS PROBLEM 2
        System.out.println("THIS IS PROBLEM 2");
        
        //this iterates through the first five fibonacci numbers, adding them
        for (int i = 1; i <= 5; i++){
            a1.add(a1.fibIterative(i));
        }//array should now have 0, 1, 1, 2, 3
                
        //below here are just ways to test the other methods
        System.out.println("~~~~~");//this separates the results above from whatever you do below
        
        //add the fibonacci numbers via fibIterative, the iterative method
        int num1 = a1.fibIterative(10);//10th fibonacci number is printed (34)
        int num2 = a1.fibIterative(2);//2nd fibonacci number is printed (1)
        int num3 = a1.fibIterative(6);//6th fibonacci number is printed (5)
        
        a1.add(num1);
        a1.add(num2);
        a1.add(num3);
        //the value order is 0, 1, 1, 1, 2, 3, 5, 34 in the array (not printed above in that order as the array is not iterated through)
                
        a1.remove(2); //value order is now 0, 1, 1, 2, 3, 5, 34
        a1.add(8); //value order is now 0, 1, 1, 2, 3, 5, 8, 34
        a1.add(2); //value order is now 0, 1, 1, 2, 2, 3, 5, 8, 34
        a1.add(80); //value order is now 0, 1, 1, 2, 2, 3, 5, 8, 34, 80

        //since the array contains two, this should print "Does the array contain two: true"
        System.out.print("Does the array contain two: ");
        System.out.println(a1.ifContains(2));

        System.out.println("random items are printed below: ");
        //these should grab any random set of values from our array 4 times
        a1.grab();
        a1.grab();
        a1.grab();
        a1.grab();

        System.out.println();//white space for viewing ease 
        //This should print out 8, since we have eight unique values in the array
        System.out.println("Number of unique items in array is ");
        a1.numItems();
    }
}
