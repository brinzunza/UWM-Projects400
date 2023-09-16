import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlightFinderGraphADTBD/*<AirportBD,CostInterface>*/ {

    public HashMap<String, AirportBD> AirportBDHash = new HashMap<>();

    public boolean insertNode(AirportBD data){
        return true;
    }
    public boolean removeNode(AirportBD data){
        return true;
    }
    public boolean containsNode(AirportBD data){
        return true;
    }
    public int getNodeCount(){
        return 4;
    }
    public boolean insertEdge(AirportBD pred, AirportBD succ, double weight){
        return true;
    }
    public boolean removeEdge(AirportBD pred, AirportBD succ){
        return true;
    }
    public boolean containsEdge(AirportBD pred, AirportBD succ){
        return true;
    }
    public CostInterfaceBD getEdge(AirportBD pred, AirportBD succ){
        return null;
    }
    public int getEdgeCount(){
        return 3;
    }
    public List<AirportBD> shortestPathData(AirportBD start, AirportBD end){
        AirportBD two = new AirportBD("city2", "country2");
        AirportBD one = new AirportBD("city1", "country2");
        List<AirportBD> result = new ArrayList<>();
        result.add(one);
        result.add(two);
        return result;
    }
    public AirportBD getAirportBDByPlace(AirportBD start, String country){
        return null;
    }
    public double shortestPathCost(AirportBD start, AirportBD end){
        return 4.5;
    }
    public List<AirportBD> shortestPathWithMaxNodes(AirportBD start, AirportBD end, int nodeNum){
        return null;
    }
    public double shortestCostWithMaxNodes(AirportBD start, AirportBD end, int nodeNum){
        return 7.5;
    }
}
