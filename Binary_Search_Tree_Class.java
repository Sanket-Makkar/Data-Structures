class Node {
    int key;
    Node left, right;

    public Node(int data){
        key = data;
        left = right = null;
    }
}

class BST_Class {
    //node class that defines BST node
    // BST root node 
    Node root;

    // Constructor for BST =>initial empty tree
    BST_Class(){
        root = null;
    }
    //delete a node from BST
    void deleteKey(int key) {
        //the variables below help us search for our temp (node to delete)
        Node temp = root;
        //and its parent
        Node parent = null;

        //search for temp, update parent as we go
        while (temp != null && temp.key != key){
            parent = temp;
            if (temp.key > key)
                temp = temp.left;
            else
                temp = temp.right;
        }
    
        //if we look through list and input key is not on list
        if (temp == null){
            //we don't have to do anything
            return;
        }
        
        //otherwise... we have to delete
        //cases 1 and 2
        if (temp.left == null || temp.right == null){
            //track child of temp 
            Node tempKid = null;
            
            //if temp is null on left side, set it's kid to null (temp.left)
            if (temp.left != null){
                tempKid = temp.left;
            }
            //else set it's kid to null or whatever value is right of temp
            //(temp.right);
            else{
                tempKid = temp.right;
            }

            //if we are at the root, we just assign root as temp child
            //(replace root with child of root)
            if (temp == root){
                root = tempKid;
            }
            
            //first we check what side of parent temp is on
            else if(temp.key < parent.key){
                //then we skip over temp by linking parent to temp's kid
                parent.left = tempKid;
            }
            else{
                //then we skip over temp by linking parent to temp's kid
                parent.right = tempKid;
            }
            //we are done now, so we end method
            return;
        }
        //case 3 (2 children parent)
        else{
            //we create a parent replacement var
            Node replaceParent = temp;
            //we create a temp replacement var
            Node replaceTemp = temp.right;
            
            //we find the lowest value node on temp's right subtree
            while (replaceTemp.left != null){
                //update replaceParent and replaceTemp as we go
                replaceParent = replaceTemp;
                replaceTemp = replaceTemp.left;
            }
            
            //set temp.key to its replacement key (replaceTemp.key)
            temp.key = replaceTemp.key;
            //now eliminate the new leaf node that is replaceTemp
            if (replaceTemp.key < replaceParent.key){//check if replaceTemp is on left side of replaceParent
                //now skip over replaceTemp
                replaceParent.left = null;
            }
            else{//check if replaceTemp is on right of replaceParent
                //skip over replaceTemp
                replaceParent.right = null;
            }
            
        }
    }

    int minValue(Node root)  {
        //temp is a var we will use to traverse the list
        Node temp = root;
        //we traverse the to its left most element
        while (temp.left != null){
            temp = temp.left;
        }
        //we return the left most element
        return temp.key;
    }

    // insert a node in BST
    void insert(int key)  {
        root = insert_Recursive(root, key);
    }

    //recursive insert function
    Node insert_Recursive(Node root, int key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            return root;
        }
        //traverse the tree
        if (key < root.key)     //insert in the left subtree
            root.left = insert_Recursive(root.left, key);
        else if (key > root.key)    //insert in the right subtree
            root.right = insert_Recursive(root.right, key);
        // return pointer
        return root;
    }

    boolean search(int key)  {
        Node temp = root;
        
        while (temp != null && temp.key != key){
            if (temp.key > key)
                temp = temp.left;
            else
                temp = temp.right;
        }
        
        if (temp == null){
            return false;
        }
        else{
            return true;
        }
    }

    //PostOrder Traversal - Left:Right:rootNode (LRn)
    void postOrder(Node node)  {
        //if node is null we stop
        if (node == null)
            return;
        else{
            //else we recurse left
            postOrder(node.left);
            //recurse right
            postOrder(node.right);
            //visit
            System.out.println(node.key);
        }
    }
    // InOrder Traversal - Left:rootNode:Right (LnR)
    void inOrder(Node node){
        if (node == null)
            return;
        //first traverse left subtree recursively
        inOrder(node.left);

        //then go for root node
        System.out.print(node.key + " ");

        //next traverse right subtree recursively
        inOrder(node.right);
    }
    //PreOrder Traversal - rootNode:Left:Right (nLR)
    void preOrder(Node node){
        //if node is null we stop
        if (node == null)
            return;
        else{
            //else we visit node
            System.out.println(node.key);
            //recurse left
            preOrder(node.left);
            //recurse right
            preOrder(node.right);
        }
    }

    // Wrappers for recursive functions
    void postOrder_traversal()  {
        postOrder(root);  }
    void inOrder_traversal() {
        inOrder(root);   }
    void preOrder_traversal() {
        preOrder(root);  }
}

class Main{
    public static void main(String[] args)  {
        //create a BST object
        BST_Class bst = new BST_Class();
        //insert data into BST
        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        bst.insert(50);
        //print the BST
        System.out.println("The BST Created with input data(Left-root-right):");
        bst.inOrder_traversal();

        
        //delete leaf node
        System.out.println("\nThe BST after Delete 12(leaf node):");
        bst.deleteKey(12);
        bst.inOrder_traversal();
        //delete the node with one child
        System.out.println("\nThe BST after Delete 90 (node with 1 child):");
        bst.deleteKey(90);
        bst.inOrder_traversal();

        //delete node with two children  
        System.out.println("\nThe BST after Delete 45 (Node with two children):");
        bst.deleteKey(45);
        bst.inOrder_traversal();
        //search a key in the BST
        boolean ret_val = bst.search (50);
        System.out.println("\nKey 50 found in BST: " + ret_val );
        ret_val = bst.search (12);
        System.out.println("Key 12 found in BST: " + ret_val );


        //construct a BST
        BST_Class tree = new BST_Class();
        tree.root = new Node(45);
        tree.root.left = new Node(10);
        tree.root.right = new Node(90);
        tree.root.left.left = new Node(7);
        tree.root.left.right = new Node(12);
        //PreOrder Traversal
        System.out.println("BST => PreOrder Traversal:");
        tree.preOrder_traversal();
        //InOrder Traversal
        System.out.println("\nBST => InOrder Traversal:");
        tree.inOrder_traversal();
        //PostOrder Traversal
        System.out.println("\nBST => PostOrder Traversal:");
        tree.postOrder_traversal();
        
        //test minValue (should return 7)
        System.out.println();
        System.out.print("This is our tree's min value: ");
        System.out.println(tree.minValue(tree.root));
    }
}