public class hashTable {
        
    //this is a mini node class which will store hashtable entries
    public class entryNode{
        //each entryNode has a key (which is a string)
        private String key;
        //a next pointer (to another entryNode if needed)
        private entryNode next = null;
        //a frequency counter for the frequency of the word
        private int frequency = 0;
        
        //this is the constructor -- we just input a string as the word
        entryNode(String inputKey){
            key = inputKey;
        }
    }
    
    //now, we create our hashTable class
    //we create an array named "table" of entryNode type
    private entryNode[] table;
    //create a tableSize and set it to 0 since we have not declared tableSize as of now -- this is declared in constructor
    private int tableSize = 0;
    //and we have a totalElements counter so that we can track load factor later on
    private int totalElements = 0;
    //we also have a string array named storeKeys which tracks all keys input to the table
    private String[] storeKeys;
    //the variable storeKeysPos below is for tracking the last element of storeKeys
    private int storeKeysPos = 0;
    //this is the hashTable constructor
    hashTable(int size){//we need to input a size to create a hashTable
        //we make our table of length "size"
        table = new entryNode[size];
        //we set tableSize to "size"
        tableSize = size;
        storeKeys = new String[size];
    }
    
    /* Name: wordCount
     * Status: public
     * Purpose: to use the hashtable I have made and, given an input string, print out words and their frequencies
     */
    public void wordCount(String wordToAdd){
        //store string as array of individual words
        String[] tempArr = wordToAdd.split("\\P{Alpha}+");
    
        //we add the words to a hash table
        for (int i = 0; i < tempArr.length; i++){
            addWord(tempArr[i]);
        }
    
        //finally we print
        for (int i = 0; i < table.length; i++){
            if (table[i] != null){
                entryNode currTemp = table[i];
                int tempFrequency = 0;
                while (currTemp != null){
                    System.out.print("Word: ");
                    System.out.print(currTemp.key);
                    System.out.print(" ... ");
                    System.out.print("Frequency: ");
                    System.out.println(currTemp.frequency);
                    currTemp = currTemp.next;
                }
            }
        }
    
        //when we are done we clear the hashTable for later use
        table = new entryNode[tableSize]; //this clears all values from hashtable
        totalElements = 0; //this resets totalElements for later load factor calculation
    }
    
    /* Name: hashStr
     * Status: private
     * Purpose: this is a helper method used to hash input, it will be used in addWord, rehash, and search methods
     */
    private int hashStr(String toHash){
        //this just returns the standard java String hash, after mod by tableSize
        return Math.abs(toHash.hashCode()) % tableSize;
    }
    
    /* Name: addWord
    *  Status: private
    *  Purpose: Add a word to the hashtable, this will be used in our wordCount method.
    */
    private void addWord(String wordToAdd){
        //we will make our word lowercase
        wordToAdd = wordToAdd.toLowerCase();
    
        //check if load factor is over 1
        if (totalElements/tableSize >= 1){ //if so tableSize needs to be expanded and we need to rehash
            tableSize *= 2; //we expand tablSize by doubling it
            rehash(table, tableSize);//this rehashes and expands table 
        }
    
        //now we can add the word to the table
        int index = hashStr(wordToAdd);
        
        if (table[index] == null){  //check to see if slot is not full
            table[index] = new entryNode(wordToAdd); //add if empty slot
            table[index].frequency = 1; //set frequency to 1 since this word has just been added 
        }
        else if(wordToAdd.compareTo(table[index].key) == 0){ //else check to see if word is repeated
            table[index].frequency += 1; //if word is repeated then update frequency
        }
        else{ //slot is full 
            //now we traverse to end of chain with traversal variable trav
            entryNode trav = table[index];
            //we also create a boolean "notFound" which will determine if, as we traverse the chain, we find a matching entryNode key
            boolean notFound = false;
    
            //this while loop does the traversing
            while (trav.next != null && notFound == false){
                if (trav.key.compareTo(wordToAdd) == 0)//this if-statement tells us if we find a key in the chain that matches our wordToAdd 
                    notFound = true;//if so we set notFound to true
                else
                    trav = trav.next;//this increments trav as we traverse
            }
    
            //if we stopped the traversal because we found a matching key, then we can just increment that keys frequency
            if (wordToAdd.compareTo(trav.key) == 0){
                trav.frequency += 1; //we increment the frequency of the matching key here
            }
            //otherwise we are at the end of the chain
            else{
                //now, we know trav is at the end of the chain so we add a new entryNode to the chain, with wordToAdd as its string
                trav.next = new entryNode(wordToAdd); //we 
                trav.next.frequency = 1; //we set the added node frequency to 1 since it has just been added
            }
        }
    
        //since we added an element, we increment totalElements;
        totalElements += 1;
        storeKeys[storeKeysPos] = wordToAdd;
        storeKeysPos += 1;
    }

    /* Name: rehash
     * Status: private
     * Purpose: This is just a helper method used to rehash the whole table in the event of a table expansion
     */
    private void rehash(entryNode[] oldTable, int newSize){
        //first we will create a new string holder called newStoreKeys which will be of size newSize and which we will
        //iterate through,adding all old input strings to newStoreKeys as we go
        String[] newStoreKeys = new String[newSize];
        for (int i = 0; i < storeKeys.length; i++){
            newStoreKeys[i] = storeKeys[i];//this iterates through storeKeys and adds all values in it to newStoreKeys
        }
        
        //now we set storeKeys = newStoreKeys, and we have a larger array to contain our strings
        storeKeys = newStoreKeys;
        
        //declare array newTable (this is table but set to the bigger size)
        entryNode[] newTable = new entryNode[newSize];
        
        //we iterate through storeKeys, and hash keys into newTable
        for (int i = 0; i < storeKeys.length; i++){
            if (storeKeys[i] != null){
                //this is an index
                int index = hashStr(storeKeys[i]);
        
                if (newTable[index] == null){  //check to see if slot is not full
                    newTable[index] = new entryNode(storeKeys[i]); //add if empty slot
                    newTable[index].frequency = 1; //set frequency to 1 since this word has just been added 
                }
                else if(storeKeys[i].compareTo(newTable[index].key) == 0){ //else check to see if word is repeated
                    newTable[index].frequency += 1; //if word is repeated then update frequency
                }
                else{ //slot is full 
                    //now we traverse to end of chain with traversal variable trav
                    entryNode trav = newTable[index];
                    //we also create a boolean "notFound" which will determine if, as we traverse the chain, we find a matching entryNode key
                    boolean notFound = false;
        
                    //this while loop does the traversing
                    while (trav.next != null && notFound == false){
                        if (trav.key.compareTo(storeKeys[i]) == 0)//this if-statement tells us if we find a key in the chain that matches our wordToAdd 
                            notFound = true;//if so we set notFound to true
                        else
                            trav = trav.next;//this increments trav as we traverse
                    }
        
                    //if we stopped the traversal because we found a matching key, then we can just increment that keys frequency
                    if (storeKeys[i].compareTo(trav.key) == 0){
                        trav.frequency += 1; //we increment the frequency of the matching key here
                    }
                    //otherwise we are at the end of the chain
                    else{
                        //now, we know trav is at the end of the chain so we add a new entryNode to the chain, with wordToAdd as its string
                        trav.next = new entryNode(storeKeys[i]); //we 
                        trav.next.frequency = 1; //we set the added node frequency to 1 since it has just been added
                    }
                }
                //since we added an element, we increment totalElements;
                totalElements += 1;
            }
        }
    
        //finally we set the table var to newTable, thus expanding table's size, and rehashing all values into the larger table
        table = newTable;
    }
    
    /* Name: main
     * Status: public
     * Purpose: This is the main method, where I have set up a demo for testing the wordCount method
     */
    public static void main(String[] args){
        //we initialize a new hashTable called myTable
        //we set its size to 10 on purpose, as it will need to successfully rehash to complete the demo below
        hashTable myTable = new hashTable(10); 
    
        //myString will be put through wordCount, and since it is over 10 words, the table will need to rehash successfully 
        String myString = "Repeat, repeat, repeat, repeat, repeat, repeat, repeat, repeat, repeat. This is a list of words. Apple, apple, pear, orange, orange, melon, melon, melon.";
        //we should get the following output
        /*  Word: repeat ... Frequency: 9
            Word: words ... Frequency: 1
            Word: is ... Frequency: 1
            Word: apple ... Frequency: 2
            Word: list ... Frequency: 1
            Word: a ... Frequency: 1
            Word: melon ... Frequency: 3
            Word: of ... Frequency: 1
            Word: this ... Frequency: 1
            Word: orange ... Frequency: 2
            Word: pear ... Frequency: 1
        */ 
        myTable.wordCount(myString);
        
        //thank you!
    }
}