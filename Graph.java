import java.util.LinkedList;
import java.util.Queue;
public class Graph
{
    private class Vertex{
        private String name;
        private LinkedList<String> edges = new LinkedList();
        private boolean encountered = false;
        private Vertex parent = null;
        Vertex(String inputName){
            name = inputName;
        }
    }
    
    private int numVertices = 0;
    private int numEdges;
    private Vertex[] vertices;
    public Graph(int maxSize){
        vertices = new Vertex[maxSize];
    }
    
    public boolean addNode(String name){
        if (numVertices >= vertices.length)
            return false;
        
        for (int i = 0; i < numVertices; i++)
            if (name.compareTo(vertices[i].name) == 0)
                return false;
        
        int pos = 0;
        if (numVertices != 0){
            while (name.compareTo(vertices[pos].name) > 0){   
                pos++;
                if (pos >= numVertices)
                    break;
            }
            
            
            for (int i = numVertices; i > pos; i--){
                vertices[i] = vertices[i - 1];
            }
        }
        Vertex toAdd = new Vertex(name);
        vertices[pos] = toAdd;
        numVertices++;
        return true;
    }
    
    public boolean addNodes(String[] names){
        boolean i = true;
        int pos = 0;
        while (i == true && pos < names.length){
            i = addNode(names[pos]);
            pos++;
        }
        
        if (i != true)
            return false;
        return true;
    }
    
    public boolean addEdge(String from, String to){
        if (from.compareTo(to) == 0)
            return false;
        
        int i = 0;
        while (vertices[i].name.compareTo(from) != 0){
            if (i < vertices.length)
                i++;
            else
                return false;
        }
        
        int j = 0;
        while (vertices[j].name.compareTo(to) != 0){
            if (j < vertices.length)
                j++;
            else
                return false;
        }
        
        if (vertices[i].edges.indexOf(to) != -1)
            return false;
        if (vertices[j].edges.indexOf(from) != -1)
            return false;
        
        int posTo;
        for (posTo = 0; posTo < vertices[i].edges.size(); posTo++){
            if (vertices[i].edges.get(posTo).compareTo(to) >= 0){
                break;
            }
        }
    
        int posFrom;
        for (posFrom = 0; posFrom < vertices[j].edges.size(); posFrom++){
            if (vertices[j].edges.get(posFrom).compareTo(from) >= 0){
                break;
            }
        }

        vertices[i].edges.add(posTo, to);
        vertices[j].edges.add(posFrom, from);
        
        return true;
    }
    
    public boolean addEdges(String from, String[] tolist){
        for (int i = 0; i < tolist.length; i++){
            boolean successValue = addEdge(from, tolist[i]);
            if (successValue != true)
                return false;
        }
        return true;
    }
    
    public boolean removeNode(String name){
        int i = 0;
        while (i < numVertices){
            if (vertices[i].name.compareTo(name) == 0)
                break;
            i++;
        }
        
        if (i >= numVertices)
            return false;
 
        int j = 0;
        for (j = i; j < numVertices - 1; j++){
            vertices[j] = vertices[j + 1];
        }
        vertices[j] = null;
        numVertices--;

        for (int iterate = 0; iterate < numVertices; iterate++){
            vertices[iterate].edges.remove(name);
        }
        
        return true;
    }
    
    public boolean removeNodes(String[] nodelist){
        for (int i = 0; i < nodelist.length; i++){
            boolean success = removeNode(nodelist[i]);
            if (success == false)
                return false;
        }
        return true;
    }
    
    public void printGraph(){        
        for (int i = 0; i < numVertices; i++){
            System.out.print(vertices[i].name);
            if (vertices[i].edges != null)
                for (int j = 0; j < vertices[i].edges.size(); j++){
                    System.out.print(" ");
                    System.out.print(vertices[i].edges.get(j));
                }
            System.out.println();
        }
    }
    
    public String[] DFS(String from, String to, String neighborOrder){   
        int i;
        for (i = 0; i < numVertices; i++){
            if (vertices[i].name.compareTo(from) == 0)
                break;
        }
        String containsDFS = helperDFS(vertices[i], null, to, neighborOrder);
        
        String[] ourPath = containsDFS.split("\\P{Alpha}+");
        
        for (int j = 0; j < numVertices; j++)
            vertices[j].encountered = false;
            
        return ourPath;
    }
    
    private String helperDFS(Vertex node, Vertex parent, String to, String neighborOrder){
        String nName = node.name;
        node.encountered = true;
        if (node.name.compareTo(to) == 0){
            return to;
        }
        if (neighborOrder.compareTo("alphabetical") == 0){
            for (int j = 0; j < node.edges.size(); j++){
                int i;
                for (i = 0; i < numVertices; i++){
                    if (vertices[i].name.compareTo(node.edges.get(j)) == 0)
                        break;
                }
                if (vertices[i].encountered == false){
                    nName += " " + helperDFS(vertices[i], node, to, neighborOrder);
                    if (nName.substring(nName.length() - 1).compareTo(to) == 0)
                        return nName;
                }
                nName = node.name;
            }
        }
        else if (neighborOrder.compareTo("reverse") == 0){
            for (int j = node.edges.size() - 1; j >= 0; j--){
                int i;
                for (i = 0; i < numVertices; i++){
                    if (vertices[i].name.compareTo(node.edges.get(j)) == 0)
                        break;
                }
                if (vertices[i].encountered == false){
                    nName += " " + helperDFS(vertices[i], node, to, neighborOrder);
                    if (nName.substring(nName.length() - 1).compareTo(to) == 0)
                        return nName;
                }
                nName = node.name;
            }
        }
        else{
            nName = "";
        }
        return nName;
    }
    
    public String[] BFS(String from, String to, String neighborOrder){
        String ret = from;
        if (neighborOrder.compareTo("alphabetical") == 0){    
            Queue<String> BFQ = new LinkedList<>();
            BFQ.add(from);
            while(BFQ.peek() != null && BFQ.peek().compareTo(to) != 0){
                String lookFor = BFQ.poll();
                int i = 0; 
                for (i = 0; i < numVertices; i++){
                    if (vertices[i].name.compareTo(lookFor) == 0)
                        break;
                }
                
                vertices[i].encountered = true;
                boolean used = false;
                for (int j = 0; j < vertices[i].edges.size(); j++){
                    String curr = vertices[i].edges.get(j);
                    
                    int search = 0;
                    for (search = 0; search < numVertices; search++){
                        if (vertices[search].name.compareTo(curr) == 0)
                            break;
                    }
                       
                    if (curr.compareTo(to) == 0){
                        break;
                    }
                    
                    if (vertices[search].encountered != true){
                        vertices[search].encountered = true;
                        
                        if (used == false)
                            ret = from;
                        else
                            ret += " " + vertices[search].name;
                        
                        used = true;
                        BFQ.add(vertices[search].name);
                    }
                    else
                        used = false;
                }
            }
            ret += to;
        }
        else if (neighborOrder.compareTo("reverse") == 0){
            Queue<String> BFQ = new LinkedList<>();
            BFQ.add(from);
            while(BFQ.peek() != null && BFQ.peek().compareTo(to) != 0){
                String lookFor = BFQ.poll();
                int i = 0; 
                for (i = 0; i < numVertices; i++){
                    if (vertices[i].name.compareTo(lookFor) == 0)
                        break;
                }
                
                vertices[i].encountered = true;
                boolean used = false;
                for (int j = vertices[i].edges.size() - 1; j >= 0; j--){
                    String curr = vertices[i].edges.get(j);

                    int search = 0;
                    for (search = 0; search < numVertices; search++){
                        if (vertices[search].name.compareTo(curr) == 0)
                            break;
                    }
                    
                    if (curr.compareTo(to) == 0){
                        break;
                    }
                    
                    if (vertices[search].encountered != true){
                        vertices[search].encountered = true;
                        
                        if (used == false)
                            ret += " " + vertices[search].name;
                        used = true;
                        BFQ.add(vertices[search].name);
                    }
                    else
                        used = false;
                }
            }
            ret += to;
        }
        else{
            ret = "";
        }
                
        String[] ourPath = ret.split("\\P{Alpha}+");

        for (int trav = 0; trav < numVertices; trav++)
            vertices[trav].encountered = false;
        return ourPath;
    }
    
    public String[] shortestPath(String from, String to){
        String ret = "";
        int init = 0;
        for (init = 0; init < numVertices; init++)
            if (vertices[init].name.compareTo(from) == 0)
                break;
        
        int[] shortestDist = new int[numVertices];
        Vertex[] parent = new Vertex[numVertices];
        
        while (vertices[init].encountered == false){
            for (int i = 0; i < vertices[init].edges.size(); i++){
                int j = 0;
                for (j = 0; j < numVertices; j++)
                    if (vertices[j].name.compareTo(vertices[init].edges.get(i)) == 0)
                        break;
                
                if (vertices[j].encountered == false){
                    shortestDist[j] += 1;
                    parent[j] = vertices[j];
                }
            }
            vertices[init].encountered = true;
            
            int minPos = 0;
            for (int i = 0; i < shortestDist.length; i++){
                if (shortestDist[i]  <= shortestDist[minPos] && vertices[i].encountered == false)
                    minPos = i;
            }
            
            init = minPos;
        }
        
        int i = 0;
        for (i = 0; i < shortestDist.length; i++){
            if (vertices[i].name.compareTo(to) == 0)
                break;
        }
        
        while (vertices[i].name.compareTo(from) != 0){
            if (parent[i] != null){
                ret += " " + parent[i].name;
                int going = 0;
                for (going = 0; going < numVertices; going++)
                    if (vertices[going].name.compareTo(parent[i].name) == 0)
                        break;
                
                i = going;
            }
        }
        
        String[] ourPath = ret.split("\\P{Alpha}+");
        
        for (int w = 0; w < numVertices; w++)
            vertices[w].encountered = false;
            
        return ourPath;
    }
    
    public String[] secondShortestPath(String from, String to){
        String[] path = shortestPath(from, to);
        int[] lengths = new int[path.length];
        int i = 0;
        for (i = 0; i < path.length; i++){
            Vertex[] tempVertices = vertices;
            removeNode(path[i]);
            lengths[i] = shortestPath(from, to).length;
            vertices = tempVertices;
        }
        
        int minPosLength = 0;
        int j = 0;
        for (j = 0; j < lengths.length; j++){
            if (lengths[minPosLength] > lengths[j])
                minPosLength = j;
        }
        
        Vertex[] tempVertices = vertices;
        removeNode(path[j]);
        String[] ret = shortestPath(from, to);
        vertices = tempVertices;
        
        return ret;
    }
    
    public static void main(String[] args){
        //we create a new graph of maximum size 100
        Graph testerGraph = new Graph(100);
        
        //in order for any of the terminal outputs to work, printGraph must work
        //therefore if terminal outputs match predictions, methods and printGraph work

        //we add in nodes using the addNodes method and an array of string nodename inputs
        System.out.println("nodesToAdd");
        String[] nodesToAdd = {"B", "A", "D", "C", "A"};
        testerGraph.addNodes(nodesToAdd);
        testerGraph.printGraph();
        System.out.println();

        //we add edges between the nodes to make a connected graph
        //we use addEdge for this process here
        System.out.println("addEdge");
        testerGraph.addEdge("B", "A");
        testerGraph.addEdge("C", "D");
        testerGraph.printGraph();
        System.out.println();
        //we use addEdges for this process here
        System.out.println("addEdges");
        String[] edgesToAdd = {"D", "D", "A"};
        testerGraph.addEdges("A", edgesToAdd);
        //we again use addEdges but for a list of size 1
        String[] otherEdges = {"D"};
        testerGraph.addEdges("B", otherEdges);
        testerGraph.printGraph();
        System.out.println();
        
        //in adjacency list format, our graph looks as shown below
        /*  A B D
         *  B A D
         *  C D
         *  D A B C
         */
        
        //we will now run DFS on our graph (testerGraph) in alphabetical order to find
        //a path from A to C
        //the appropriate path will be ABD since this is looking at neighbors in alphabetical order
        //and we go from A, down to node B, down to node D (our destination)
        String[] arr = testerGraph.DFS("A", "D", "alphabetical");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i]);
        System.out.println("\n");
        
        //we will now run DFS on our graph in "reverse" (nonalphabetical) order
        //to find a path from A to C
        //the appropriate path will be AD, since we consider neighbors in reverse alphabetical order
        //and going in reverse alphabetical order immediately gets us to node D
        arr = testerGraph.DFS("A", "D", "reverse");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i]);
        System.out.println("\n");
        
        
        //we will now run BFS on our graph in "reverse" (nonalphabetical) order
        //to find a path from A to C
        //the appropriate path will be ADC (again)
        arr = testerGraph.BFS("A", "C", "alphabetical");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i]);
        System.out.println("\n");
        
        //we will now run BFS on our graph in "reverse" (nonalphabetical) order
        //to find a path from A to C
        //the appropriate path will be AD, since D is directly connected to A
        arr = testerGraph.BFS("A", "D", "reverse");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i]);
        System.out.println("\n");
        
        //now we can try the removeNode method -- we should get the below output
        /*  B D
         *  C D
         *  D B C
         */
        System.out.println("removeNode");
        testerGraph.removeNode("A");
        testerGraph.printGraph(); 
        System.out.println();

        //we now use the removeNodes method (to remove multiple nodes at once)
        //we should be left with just 
        /*   B
         */
        System.out.println("removeNodes");
        String[] nodesToRemove = {"D", "C", "D"};
        testerGraph.removeNodes(nodesToRemove);
        testerGraph.printGraph();      
        System.out.println();
    }
}
