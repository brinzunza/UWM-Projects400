//Node
public class AirportBD implements AirportInterface {

    private String name;
    private String country;

public AirportBD(String AirportName, String country){
    name = AirportName;
    this.country = country;
}

    public String getAirportName(){
        return "SFO";
    }
    public String getCountry(){
        return this.country;
    }

}
