//neccesary imports
import java.util.LinkedList;
import java.util.Queue;

/* Programming Assignment 2 Problem 2a
 * Goal: Using a single Queue in the constructor, write a class -- CustomQStack -- that emulates a Stack using the Queue.
 *       Methods required are as follows...
 *               --> empty() : boolean that returns true if stack is empty, false if stack is full
 *               --> push(int item) : adds item to top of the stack and returns int item added
 *               --> pop() : removes item from top of the stack and returns int item removed
*/

class CustomQStack{
    //the Queue (var named q) we will use to simulate a stack 
    private Queue<Integer> q;

    //constructor that takes a Queue as input (a)
    public CustomQStack(Queue<Integer> a){
        //set our Queue (q) as our input Queue (a)
        q = a;
    }

    //empty() : boolean that returns true if stack is empty, false if stack is full
    public boolean empty(){
        //if we peek into q, and we see null, we know q is empty...
        //... if so return true
        if (q.peek() == null)
            return true;
        //otherwise... we return false
        else
            return false;
    }

    //pop() : removes item from top of the stack and returns int item removed
    //notes on strategy: Since we want to emulate a stack FILO structure, we need to 
    //                   remove the bottom-most item near the back end of the Queue.
    //                   To do this we will q.poll() and transfer q values into another 
    //                   tempQueue for all values but the last, and then tempQueue.poll() all
    //                   tempQueue values into q, thus removing the last item added to the CustomQStack.
    public int pop(){
        //we create int tempVal, to store values we want to q.poll() later 
        int tempVal = 0;

        //we create tempQueue to temporarily hold q values in the future in a Queue structure
        Queue<Integer> tempQueue = new LinkedList<Integer>();

        //we iterate through q, calling q.poll() to continue iterating through Queue elements
        while (q.peek() != null){
            //tempVal holds q.poll()
            tempVal = q.poll();

            //now that we have removed the element in the front end of the stack
            //we need to check if we are at the end of the stack
            if (q.peek() != null)
                tempQueue.add(tempVal);//if we are not at the end of q we transfer q values to tempQueue via tempVal

            //the if statement above allows us to give tempQueue everything other than the
            //bottom-most item near the back end of the CustomQStack
        }
        
        //now that tempQueue holds everything but the last item added, we tempQueue.poll() all tempQueue values back into
        //q, thus putting things back in order.
        while (tempQueue.peek() != null){
            q.add(tempQueue.poll());
        }
        
        //the last tempVal will the be item removed, so we just return it.
        return tempVal;
    }

    //push(int item) : adds item to top of the stack and returns int item added
    public int push(int item){
        //for this one we just have to add our input (int item) to q
        q.add(item);
        return item;//we also return the int item
    }
    
    //MAIN: this is where we test things
    public static void main(String args[]){

        //Create a Queue e, add 5,6,7,8,9 to it
        Queue<Integer> e = new LinkedList<Integer>();
        e.add(5);
        e.add(6);
        e.add(7);
        e.add(8);
        e.add(9);
        //e should look like this 
        // FrontEnd <-- 5, 6, 7, 8, 9 <-- BackEnd
        
        //now we create a CustomQStack d (based off of e)
        CustomQStack d = new CustomQStack(e);
        //d looks like this
        // Top <-- 9, 8, 7, 6, 5 <-- Bottom

        //we d.pop() once, this should print 9
        int x = d.pop();
        System.out.print("This should print 9: ");
        System.out.println(x);

        //we d.pop() again, this should print 8
        x = d.pop();
        System.out.print("This should print 8: ");
        System.out.println(x);

        //this should print false, as d is not empty
        System.out.print("This should print false: ");
        System.out.println(d.empty());
        
        //lets add 1 to d, this should look like: Top <-- 120, 7, 6, 5 <-- Bottom
        d.push(120);
        
        //To see if things are as they should be, we can print d by pop() and then printing what got popped
        //below should print 120, 7, 6, 5 (Top to Bottom) 
        System.out.print("This is d from Top to Bottom, should be 120, 7, 6,  -->  ");
        while (d.empty() != true){
            System.out.print(d.pop());
            //I just want to make things look nice by adding commas and whitespace appropriately below
            if (d.empty() == true)
                System.out.println("");
            else
                System.out.print(", ");
        }

        //finally, we see if d empty
        //add and get pop something just for good measure
        d.push(99);
        d.pop();
        
        //this should print true
        System.out.print("This should print true: ");
        System.out.println(d.empty());
    }
}