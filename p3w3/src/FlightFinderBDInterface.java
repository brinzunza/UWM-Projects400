import java.util.PriorityQueue;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;


public interface FlightFinderBDInterface {


    //user inputs -1 if no specific amount of layovers desired
    public List<AirportInterface> searchByCost(String start, String end, int layovers);

    public double searchByCostDouble(String start, String end, int layovers);

    public void loadData(String fileName) throws FileNotFoundException;

    public boolean contains(String location);

    public AirportInterface getAirportBDObject(String name) throws NoSuchElementException;
}
