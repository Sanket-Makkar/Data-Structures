/* Programming Assignment 2 Problem 1

 * Goal: Create a linear-time running method which reverses an input linkedlist of integers 
         without creating another linkedlist (using constant space). 

 * Strategy (mentioned in less detail in method): 
              
              The idea behind the method below is that we will find current node, iterating through current, and we will 
              track three nodes relative to current at any given time. 
              
              We track the node behind current (prev), the node infront of current (front), and current itself. 
              
              We point the current node to the head of the list, reversing the list for all points before that 
              particular node, and we will then point prev to the front node, re-linking the now separated list. 
              
              If we do this, we will gradually make our way through the list, de-linking ... reversing ... and then re-linking, 
              on each pass.  
 */
public void reverse(Node head){
    //create a node, "current", which tracks an input "head node" of a LinkedList
    Node current = head;

    
    //iterate through the input "head node" using current
    while (current.next != null){
        
        //we will look at three nodes at any given time, and we will start at the second node in the linked list

        //previous is the first node (it is right behind the second node in the linked list)
        Node prev = current;
        //current is the node we focus on at any given time
        current = current.next;
        //front is a node in front of current
        Node front = current.next;

        //we point currrent node to head, reversing the list for all nodes before and including current
        //this de-links the list, but our next step fixes that
        current.next = head;

        //we then point the prev node to what current used to point to, re-linking the list
        prev.next = front;
        
        //we set the new head to current, which is now at the front of the list
        head = current;

        //we set current = prev, as prev is now where current used to be and this allows us to increment current in the
        //next pass
        current = prev;

        //in the next pass we will increment prev to current (although that doesn't really matter as much now that current = prev)
        //                 we will increment current (which is now at prev) to current.next (the position "front" var used to occupy)
        //                 we will increment front to one node ahead of current
    }

}