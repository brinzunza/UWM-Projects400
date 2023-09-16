// --== CS400 File Header Information ==--
// Name: Bruno Inzunza
// Email: binzunza@wisc.edu
// Group and Team: DJ Red
// Group TA: Yuye Jiang
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import java.util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
        extends BaseGraph<NodeType,EdgeType>
        implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in it's node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in it's node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;
        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }
        public int compareTo(SearchNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations.  The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *         or when either start or end data do not correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        if(!containsNode(start) || !containsNode(end)){
            throw new NoSuchElementException("Start or end node data does not correspond to the graph node");
        }

        Hashtable<NodeType,Node> visited = new Hashtable();
        PriorityQueue<SearchNode> pq = new PriorityQueue<>();
        SearchNode current = new SearchNode(nodes.get(start), 0, null);
        pq.add(current);

        while(!pq.isEmpty()){
            SearchNode currNode = pq.remove();
            if(!visited.contains(currNode.node)){
                visited.put(currNode.node.data, currNode.node);

                List<Edge> neighbors = currNode.node.edgesLeaving;
                SearchNode pre = currNode.predecessor;
                double cost = currNode.cost;
                for(int i = 0; i < neighbors.size(); i++){
                    current = new SearchNode(neighbors.get(i).successor, cost + neighbors.get(i).data.doubleValue(), currNode);
                    pq.add(current);
                }
            }
            if(currNode.node.data.equals(end)){
                return currNode;
            }
        }
        throw new NoSuchElementException("No connecting path from start to end");
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value.  This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path.  This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        SearchNode last = computeShortestPath(start, end);

        LinkedList<NodeType> result = new LinkedList<>();

        while(last.predecessor != null){
            result.addFirst(last.node.data);
            last = last.predecessor;
        }
        result.addFirst(last.node.data);
        return result;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path from the node containing the start data to the node containing the
     * end data.  This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        SearchNode last = computeShortestPath(start, end);

        return last.cost;
    }

    /**
     * checks the functionality of shortestPathCost and shortestPathData from node A to E
     */
    @Test
    void test1() {
        DijkstraGraph<Character, Number> graph2 = new DijkstraGraph<>();
        graph2.insertNode('A');
        graph2.insertNode('B');
        graph2.insertNode('C');
        graph2.insertNode('D');
        graph2.insertNode('E');
        graph2.insertNode('F');
        graph2.insertNode('G');
        graph2.insertNode('H');

        graph2.insertEdge('A','B',4);
        graph2.insertEdge('A','C',2);
        graph2.insertEdge('A','E',15);
        graph2.insertEdge('B','D',1);
        graph2.insertEdge('B','E',10);
        graph2.insertEdge('C','D',5);
        graph2.insertEdge('D','F',0);
        graph2.insertEdge('D','E',3);
        graph2.insertEdge('F','H',4);
        graph2.insertEdge('F','D',2);
        graph2.insertEdge('G','H',4);

        System.out.println(graph2.shortestPathCost('A','E'));
        System.out.println(graph2.shortestPathData('A','E').toString());

        //assertEquals(8, graph2.shortestPathCost('A', 'E'));
        assertEquals(new ArrayList<Character>(Arrays.asList('A','B','D','E')), graph2.shortestPathData('A', 'E'));

    }

    /**
     * checks the functionality of the exceptions to make sure that incorrect nodes are caught properly
     */
    @Test
    void test2(){
        DijkstraGraph<Character, Number> graph2 = new DijkstraGraph<>();
        graph2.insertNode('A');
        graph2.insertNode('B');
        graph2.insertNode('C');
        graph2.insertNode('D');
        graph2.insertNode('E');
        graph2.insertNode('F');
        graph2.insertNode('G');
        graph2.insertNode('H');

        graph2.insertEdge('A','B',4);
        graph2.insertEdge('A','C',2);
        graph2.insertEdge('A','E',15);
        graph2.insertEdge('B','D',1);
        graph2.insertEdge('B','E',10);
        graph2.insertEdge('C','D',5);
        graph2.insertEdge('D','F',0);
        graph2.insertEdge('D','E',3);
        graph2.insertEdge('F','H',4);
        graph2.insertEdge('F','D',2);
        graph2.insertEdge('G','H',4);
        try{
            graph2.shortestPathCost('A','G');
            fail("Exception not thrown");
        }
        catch(NoSuchElementException e){

        }
        try{
            graph2.shortestPathCost('A', 'V');
            fail("Exception not thrown");
        }
        catch(NoSuchElementException e){

        }
    }

    /**
     * checks the functionality of shortestPathCost and shortestPathData with different starting and ending inputs
     */
    @Test void test3(){
        DijkstraGraph<Character, Number> graph2 = new DijkstraGraph<>();
        graph2.insertNode('A');
        graph2.insertNode('B');
        graph2.insertNode('C');
        graph2.insertNode('D');
        graph2.insertNode('E');
        graph2.insertNode('F');
        graph2.insertNode('G');
        graph2.insertNode('H');

        graph2.insertEdge('A','B',4);
        graph2.insertEdge('A','C',2);
        graph2.insertEdge('A','E',15);
        graph2.insertEdge('B','D',1);
        graph2.insertEdge('B','E',10);
        graph2.insertEdge('C','D',5);
        graph2.insertEdge('D','F',0);
        graph2.insertEdge('D','E',3);
        graph2.insertEdge('F','H',4);
        graph2.insertEdge('F','D',2);
        graph2.insertEdge('G','H',4);

        assertEquals(5, graph2.shortestPathCost('B', 'H'));
        assertEquals(new ArrayList<Character>(Arrays.asList('B', 'D', 'F', 'H')), graph2.shortestPathData('B', 'H'));
    }
}
