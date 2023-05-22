//neccesary imports
import java.util.LinkedList;
import java.util.Stack;

/* Programming Assignment 2 Problem 2b
 * Goal: Using a single Queue in the constructor, write a class -- CustomQStack -- that emulates a Stack using the Queue.
 *       Methods required are as follows...
 *               --> add() : adds item to Queue (CustomSQueue), throws IllegalStateException if arbitrarily large size
 *                           (100 in this case) of Queue (CustomSQueue) is reached, else returns true.
 *               --> poll() : removes head of Queue (CustomSQueue). If Queue (CustomSQueue) is empty, null is returned.
 *                            else the head of Queue (CustomSQueue) which was removed is returned. 
*/
class CustomSQueue{
    
    //these are the two stacks we will use
    //
    private Stack<Integer> stackA = new Stack<Integer>();//stackA is in order of the stack we are given in the constructor

    private Stack<Integer> stackB = new Stack<Integer>();//stackB is the reverse order of the stack we are given in the constructor

    //constructor
    public CustomSQueue(Stack<Integer> inputStack){//take in a normal Stack for customizatino into CustomSQueue
        //set stackA to inputStack --> puts stackA in order of inputStack
        stackA = inputStack;
    }

    //add(): add item to Queue (CustomSQueue), throw IllegalStateException if array is full (past the arbitrary size 100), 
    //       else return true upon success of adding item to Queue.
    //notes on strategy: The plan is to test for array fullness (size past 100) and throw exception if needed. We then will
    //                   dump stackB (if it has anything) into stackA, and knowing that stackA is in the order of inputStack,
    //                   we will simply push(item) into stackA.
    public boolean add(int item){
        //test if stackA is past arbitrary size (100)...
        if (stackA.size() > 100){
            throw new IllegalStateException();//...throw IllegalStateException if it is
        }
        
        //now transfer stackB contents (if it has anything) into stackA 
        while (!stackB.isEmpty()){
            //pop stackB
            int t = stackB.pop();
            //push popped stackB contents to stackA
            stackA.push(t);
        }//now, if stackB had any contents (which should have been in reversed inputStack order), the contents have been
         //put into stackA in inputStack order.

        //now we just push the int item into stackA
        stackA.push(item);
        return true;//after all is executed successfully, we return true
    }

    //poll(): If the CustomSQueue is empty return null, otherwise remove the head of the CustomSQueue and return the removed head
    //notes on strategy: The plan here is to check if the "Queue" (CustomSQueue) is empty, and then to dump stackA into stackB (if
    //                   stackA has anything to dump). We can then know that stackB is in reverse order of stackA, therefore the 
    //                   bottom of stackA (head) is the top of stackB. Head is at the top, so we simply pop() it, and return what
    //                   we pop(). 
    public Integer poll(){
        //check if both stacks are empty (if true, this means that there are no contents in the hypothetical "Queue" a.k.a. CustomSQueue)
        if (stackA.isEmpty() && stackB.isEmpty()){
            return null;//if true return null
        }
        
        //transfer stackA contents (if any) into stackB by stackB.push(stackA.pop()) --> This makes stackB the reverse order of StackA
        while (!stackA.isEmpty()){
            //take stackA top and pop() it
            int t = stackA.pop();
            //take that, and push() it into stackB
            stackB.push(t);
        }

        //now we can pop() stackB, and return the result in one line
        return stackB.pop();
    }
    
    //main: this is where we test things
    public static void main(String args[]){
        //create e and add elements 10, 2, 33, 8, 901 to it
        Stack<Integer> e = new Stack<Integer>();
        e.push(10);
        e.push(2);
        e.push(33);
        e.push(8);
        e.push(901);

        //create CustomSQueue d, input e as the Stack to convert
        CustomSQueue d = new CustomSQueue(e);
        //poll() d, and print it
        int x = d.poll();
        System.out.print("This should print 10: ");
        System.out.println(x);//this should print 10
        
        //another poll() on d to test it again
        x = d.poll();
        System.out.print("this should print 2: ");
        System.out.println(x);//this should print 2
        
        //we add one thing to d, and poll() everything off of d. If everything worked correctly, d should be empty
        d.add(2);
        d.poll();
        d.poll();
        d.poll();
        d.poll();

        //adding in 3 to d, 3 should be the only element
        d.add(3);

        //poll() d and print it
        x = d.poll();
        System.out.print("This should print 3: ");
        System.out.println(x);//this should print 3
        
        //this tests if our poll() empty check (returns null) works 
        System.out.print("This should print null: ");
        System.out.println(d.poll());//this should print null
        
        //this adds 100 numbers (0 - 100) to d
        for (int i = 0; i <= 100; i++){
            d.add(i);
        }

        //this tests our fullness check on add()
        //when we add one more value, our size will go from 100, to 101
        //this exceeds 100, and we should have an IllegalStateException error thrown
        //-----------Uncomment the below to throw the error------------------------
        // System.out.println(d.add(1));
        //-------------------------------------------------------------------------
    }
}