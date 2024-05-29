//sub-class for flights that are destined out of Saudi Arabia.
public class InternationalFlight extends Flight{
    

    public InternationalFlight(String id, String cityDest, String countryDest, double duration, int day, int month, int year, int size)throws MnthsException{
        super(id, cityDest, countryDest, duration, day, month, year, size);
        
    } //parameterized constructor
    public InternationalFlight(InternationalFlight f){ //copy constructor 
        super(f);
    }

    public double getTicketPrice(String id){
        if((day > 20 && day<= 31) && month ==12)
            return 2*460*duration; //double the price for christmas season
        if((month == 7 || month == 8) || (month == 1 && (day>=1 && day<=5)))
            return 1.5*460*duration; 
        /*increase price if the flight is during summer holiday, 
        OR if the flight's during new year's*/
        if(eligibleForDiscount(id))
            return ((460*duration) - (0.2*460*duration)); // a 20% discount
        return 460*duration; //normal ticket price
    }

}
