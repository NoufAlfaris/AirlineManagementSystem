//sub-class for flights that are destined to a city inside of Saudi Arabia.
public class LocalFlight extends Flight{

    public LocalFlight(String id, String cityDest, String countryDest, double duration, int day, int month, int year, int size){
        super(id, cityDest, countryDest, duration, day, month, year, size);
        
    }  
    
    public LocalFlight(LocalFlight f){
        super(f);
    }

    public double getTicketPrice(String id){
        if((month == 1 && (day>=1 && day<=5)) || (month == 7 || month == 8))
            return 1.5*310*duration; //increase price for New Year's, OR if the flight is during summer holiday
        if(eligibleForDiscount(id))
            return ((310*duration) - (0.2*310*duration)); // a 20% discount
        return 310*duration; //normal ticket price
    }
}
