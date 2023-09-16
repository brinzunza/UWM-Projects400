//Edge
public class CostInterfaceBD {
    private AirportBD pred;
    private AirportBD succ;
    private double cost;
    public CostInterfaceBD(AirportBD pred, AirportBD succ, double cost){
        this.pred = pred;
        this.succ = succ;
        this.cost = cost;
    }
    public static double getCost(AirportBD a, AirportBD b) throws IllegalArgumentException{
        return 4;
    }
    public AirportBD getAirportPred(){
        return pred;
    }

    public AirportBD getAirportSucc(){
        return succ;
    }

    public double getCost(){
        return cost;
    }

}
