import java.util.List;

public interface FlightFinderFrontendInterface {
    //publicFlightFinderFrontendXX (Scanner userInput, PathFinder backend);
        public void runCommandLoop();
        public char mainMenuPrompt();
        public void loadDataCommand();
        public List<String> chooseAirportPrompt();
        public List<AirportInterface> searchCheapestPath(String origin, String destination, int numLayover);
        public Double searchCheapestPathCost(String origin, String destination, int numLayover);
    
    }
    
    