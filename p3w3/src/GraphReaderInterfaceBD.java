import java.io.FileNotFoundException;

public class GraphReaderInterfaceBD {
     public GraphReaderInterfaceBD(){

     }
    public AirportBD[] readAirportsFromGraph(String filename) throws FileNotFoundException{
        AirportBD[] result = new AirportBD[2];
        return result;
    }

    public CostInterfaceBD[] readCostsFromGraph(String filename) throws FileNotFoundException{
        CostInterfaceBD[] result = new CostInterfaceBD[5];
        return result;
    }
}