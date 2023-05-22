import java.util.LinkedList;
import java.util.Queue;
public class WeightedGraph
{
    private class Vertex{
        private String name;
        private LinkedList<String> edges = new LinkedList();
        private boolean encountered = false;
        private Vertex parent = null;
        int weight;
        Vertex(String inputName){
            name = inputName;
        }
    }
    
    private int numVertices = 0;
    private int numEdges;
    private Vertex[] vertices;
    public WeightedGraph(int maxSize){
        vertices = new Vertex[maxSize];
    }
    
    public boolean addWeightedEdge(String from, String to, int weight){
        if (from.compareTo(to) == 0)
            return false;
        int i = 0;
        while (vertices[i].name.compareTo(from) != 0){
            if (i < vertices.length)
                i++;
            else
                return false;
        }
        
        if (vertices[i].edges.indexOf(to) != -1)
            return false;
            
        int posTo;
        for (posTo = 0; posTo < vertices[i].edges.size(); posTo++){
            if (vertices[i].edges.get(posTo).compareTo(to) >= 0){
                break;
            }
        }
        
        vertices[i].edges.add(posTo, to);
        vertices[i].weight = weight;
        
        return true;
    }
    
    public boolean addWeightedEdges(String from, String[] tolist, int[] weightlist){
        for (int i = 0; i < tolist.length; i++){
            boolean successValue = addWeightedEdge(from, tolist[i], weightlist[i]);
            if (successValue != true)
                return false;
        }
        return true;
    }
    
    public String[] secondShortestPath(String from, String to){
        //this is an explanation
        //we approach this problem by first finding the actual shortest path using Dijkstra's algorithm
        //after this, we remove edges one at a time, computing the shortest path in each case
        //eventually, we will find the shortest path out of the paths which are not the original shortes path
        //this is our final second shortest path
        return null;
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
    
    public void printWeightedGraph(){        
        for (int i = 0; i < numVertices; i++){
            System.out.print(vertices[i].name);
            if (vertices[i].edges != null)
                for (int j = 0; j < vertices[i].edges.size(); j++){
                    System.out.print(" ");
                    System.out.print(vertices[i].edges.get(j) + ":" + vertices[i].weight);
                }
            System.out.println();
        }
    }
    
    public static void main(String[] args){
        //we create a new graph of maximum size 100
        WeightedGraph testerGraph = new WeightedGraph(100);
        
        //in order for any of the terminal outputs to work, printGraph must work
        //therefore if terminal outputs match predictions, methods and printGraph work

        //we add in nodes using the addNodes method and an array of string nodename inputs
        String[] nodesToAdd = {"B", "A", "D", "C", "A"};
        testerGraph.addNodes(nodesToAdd);

        //our graph only consists of A,B,C,D nodes, since we ignore duplicate nodes when adding
        //now we will add a couple of weighted and directed edges using the addWeightedEdges method
        //since this method utilizes the other method (addWeightedEdge), we can be sure that both methods
        //work if the appropriate output is produced
        String[] edgesToAdd = {"A", "C", "D"};
        int[] weights = {3, 3, 3};
        testerGraph.addWeightedEdges("B", edgesToAdd, weights);
        testerGraph.printWeightedGraph();
        
        //we should get the output below
        /* A
         * B A:3 C:3 D:3
         * C
         * D
         * 
         */
    }
}
