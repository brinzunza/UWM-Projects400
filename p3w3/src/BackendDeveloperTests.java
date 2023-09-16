import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BackendDeveloperTests {

    /**
     * testing the functionality of searchByCost and their correct city and country
     */
    @Test
    void BackendDeveloperTest1(){
        FlightFinderBD temp = new FlightFinderBD();
        AirportBD one = new AirportBD("SFO", "USA");
        AirportBD two = new AirportBD("DTW", "country2");
        temp.AirportBDs.insertNode(one);
        temp.AirportBDs.insertNode(two);
        temp.AirportBDs.insertEdge(one, two, 4.5);
        List<AirportBD> result = new ArrayList<>();
        result.add(one);
        result.add(two);
        assertEquals("SFO", temp.searchByCost(one, two, -1).get(0).getAirportName());
        assertEquals("country2",temp.searchByCost(one,two,-1).get(1).getCountry());
    }

    /**
     * testing the functionality of searchByCostDouble
     */
    @Test
    void BackendDeveloperTest2(){
        FlightFinderBD temp = new FlightFinderBD();
        List<AirportBD> result = new ArrayList<>();
        AirportBD one = new AirportBD("city1", "country1");
        AirportBD two = new AirportBD("city2", "country2");
        GraphReaderInterfaceBD temp2 = new GraphReaderInterfaceBD();
        result.add(one);
        result.add(two);
        temp.AirportBDs.insertNode(one);
        temp.AirportBDs.insertNode(two);
        assertEquals(4.5, temp.searchByCostDouble(one, two, -1));
    }

    /**
     * testing the functionality of loadData when a file does not exist
     */
    @Test
    void BackendDeveloperTest3(){
        try {
            FlightFinderBD temp = new FlightFinderBD();
            temp.loadData("NoFile");
            fail();
        }
        catch(Exception e){

        }
    }

    /**
     * tests the functionality of the contains function
     */
    @Test
    void BackendDeveloperTest4(){
        FlightFinderBD temp = new FlightFinderBD();
        AirportBD one = new AirportBD("city1", "country1");
        AirportBD two = new AirportBD("city2", "country2");
        temp.AirportBDs.insertNode(one);
        assertEquals(true, temp.contains(one));
    }

    /**
     * tests the functionality of the getAirportBDObject function
     */
    @Test
    void BackendDeveloperTest5(){
        FlightFinderBD temp = new FlightFinderBD();
        AirportBD one = new AirportBD("SFO", "country1");
        AirportBD two = new AirportBD("city2", "country2");
        temp.AirportBDs.insertNode(one);
        temp.AirportBDs.AirportBDHash.put("SFO", one);
        assertEquals(one, temp.getAirportBDObject("SFO"));
        try{
        temp.getAirportBDObject("city2");
            fail();
        }
        catch(NoSuchElementException e){

        }
    }

    //Testing the insertion and deletion functionalities
    @Test
    void CodeReviewOfAlgorithmEngineerTest1(){
        FlightFinderBD back = new FlightFinderBD();
        FlightFinderGraphADTAE ae = new FlightFinderGraphADTAE();
        AirportDW a1 = new AirportDW("SFO", "USA");
        AirportDW a2 = new AirportDW("DTW", "USA");
        AirportDW a3 = new AirportDW("NYW", "USA");
        ae.insertNode(a1);
        ae.insertNode(a2);
        ae.insertNode(a3);
        ae.removeNode(a3);
        CostDW e1 = new CostDW(a1,a2,10.0);
        ae.insertEdge(a1,a2,e1);
        assertEquals(true, ae.containsNode(a1));
        assertEquals(false, ae.containsNode(a3));
        assertEquals(true,ae.containsEdge(a1,a2));
    }

    //Testing the computeShortestPath functionality
    @Test
    void CodeReviewOfAlgorithmEngineerTest2(){
        FlightFinderBD back = new FlightFinderBD();
        FlightFinderGraphADTAE ae = new FlightFinderGraphADTAE();
        AirportDW a1 = new AirportDW("SFO", "USA");
        AirportDW a2 = new AirportDW("DTW", "USA");
        AirportDW a3 = new AirportDW("NYW", "USA");
        AirportDW a4 = new AirportDW("TLO", "USA");
        ae.insertNode(a1);
        ae.insertNode(a2);
        ae.insertNode(a3);
        ae.insertNode(a4);
        CostDW e1 = new CostDW(a1,a2,10.0);
        CostDW e2 = new CostDW(a1,a3,1.0);
        CostDW e3 = new CostDW(a3,a2,2.0);
        ae.insertEdge(a1,a2,e1);
        ae.insertEdge(a1,a3,e2);
        ae.insertEdge(a3,a2,e3);
        assertEquals(3.0,ae.shortestPathCost(a1,a2));
    }

    //Integration test between backend developer and algorithm engineer
    @Test
    void IntegrationTest1(){
        FlightFinderBD back = new FlightFinderBD();
        FlightFinderGraphADTAE ae = new FlightFinderGraphADTAE();
        AirportDW a1 = new AirportDW("SFO", "USA");
        AirportDW a2 = new AirportDW("DTW", "USA");
        AirportDW a3 = new AirportDW("NYW", "USA");
        AirportDW a4 = new AirportDW("TLO", "USA");
        back.AirportDW.insertNode(a1);
        back.AirportDW.insertNode(a2);
        back.AirportDW.insertNode(a3);
        back.AirportDW.insertNode(a4);
        CostDW e1 = new CostDW(a1,a2,10.0);
        CostDW e2 = new CostDW(a1,a3,1.0);
        CostDW e3 = new CostDW(a3,a2,2.0);
        back.AirportDW.insertEdge(a1,a2,e1);
        back.AirportDW.insertEdge(a1,a3,e2);
        back.AirportDW.insertEdge(a3,a2,e3);
        assertEquals(3.0,back.searchByCostDouble("SFO","DTW",-1));
    }


    //Integration test between data wrangler, algorithm engineer, and backend developer
    @Test
    void IntegrationTest2(){
        FlightFinderBD back = new FlightFinderBD();
        try {
            back.loadData("C:\\Users\\Bruno\\IdeaProjects\\p3w3\\src\\airportGraph.dot");
            //System.out.println(back.AirportDW.getEdgeCount());
        }
        catch(FileNotFoundException e){
            fail();
        }
    }
}
