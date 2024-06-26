//super-class managing flight operations for an airline based in Saudi Arabia.
import java.io.*;
public abstract class Flight implements Serializable{
    protected String id;
    protected String cityDest;
    protected String countryDest;
    protected double duration;
    protected Passenger[] pList;
    protected int nOfPassengers;
    protected int day;
    protected int month;
    protected int year;


    public Flight(String id, String cityDest, String countryDest, double duration, int day, int month, int year, int size)throws MnthsException{
        this.id = id;
        this.cityDest = cityDest;
        this.duration = duration;
        this.countryDest = countryDest;
        nOfPassengers = 0;
        pList = new Passenger[size];
        this.year = year;
        //throws exception for invalid day/month .
        if(month<1 || month>12) 
        throw new MnthsException("Invalid number for month");
        else
            this.month = month;

           
            switch(this.month){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (day>=1 && day<=31)
                        this.day = day;
                    else
                    throw new MnthsException("Invalid number of Day for this month");
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if(day>=1 && day<=30)
                        this.day = day;
                    else
                    throw new MnthsException("Invalid number of for this month");
                    break;
                case 2:
                    if ((this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0)) { // leap year
                        if (day >= 1 && day <= 29)
                            this.day = day;
                        else
                        throw new MnthsException("Invalid number of Day for this month");
                    } 
                    else {
                        if (day >= 1 && day <= 28)
                            this.day = day;
                        else
                        throw new MnthsException("Invalid number of Day for this month");
                    }
                    break;
    
    
            }


        }
        
    

    public Flight(Flight f){  //copy constructor
        this.id = f.id;
        this.cityDest = f.cityDest;
        this.countryDest = f.countryDest;
        this.duration = f.duration;
        this.nOfPassengers = f.nOfPassengers;
        this.pList = new Passenger[f.pList.length];
        for(int i = 0; i<nOfPassengers; i++)
            this.pList[i] = f.pList[i];
        this.day = f.day;
        this.month = f.month;
        this.year = f.year;
    }

   

    public boolean addPassenger(Passenger p){ //adding a passenger to the flight
        if(nOfPassengers<pList.length )
            if(searchPassenger(p.getNationalID()) == null) {
                pList[nOfPassengers++]=p;
                return true;
            }

        return false; //if the passenger exists in the flight, or the flight is full return false
     }



    //method to remove a passenger if they want to cancel their booking
    public boolean removePassenger(String id){
        for(int i=0;i<nOfPassengers;i++)
            if(pList[i].getNationalID().equals(id)){
                for(int j=i;j<nOfPassengers-1;j++)
                    pList[j]=pList[j+1];
                pList[--nOfPassengers]=null;
                return true; //return true if passenger was removed successfully
            }
        return false; //return false if passenger is not found on the flight
    }



    public Passenger searchPassenger(String id){ //search for a passenger in the flight by their national ID
        for(int i = 0; i<nOfPassengers; i++){
            if(pList[i].getNationalID().equals(id))
                return pList[i];
        }
        return null; //if passenger not found, return null
     }

    public abstract double getTicketPrice(String id); //abstract method to calculate the ticket's price, implemented on the sub-classes


    public boolean eligibleForDiscount(String id){ //see if the passenger is eligible for discount based on their profession/age
        Passenger p = searchPassenger(id);
        if(p == null)
            return false; //if passenger is not found, return false
        if(p.getProfession().equalsIgnoreCase("Student") || p.getProfession().equalsIgnoreCase("military personnel") || p.getAge() >=65)
            return true; //if the passenger is a student, works in the military, or is a senior citizen; they are eligible for discount
        return false; //if neither of the conditions are correct, the passenger is not eligible for discount
    }


    public boolean isFlightAvailable() {
     if (nOfPassengers < pList.length)
        return true;
    
     return false;
   }
   

   public String toString() {
      String passengers = "";
      for (int i=0 ; i<nOfPassengers ; i++)
        passengers += pList[i] +"\n"; //create string of all passengers info
      return "Flight ID: " + id + "\nFlight Type: " + getClass().getName() + "\nDestination: " + cityDest + "-" + countryDest +
         "\nDuration: " + duration + " hours" + "\nDate: " + day + "/" + month + "/" + year +
         "\nTicket Price(without discount): " + getTicketPrice("") + "SAR" + "\nNumber of passengers: " + nOfPassengers +
         "\n\nPassengers Information:\n" + passengers +
         "-------------------------------------------------------\n"; //returns all flight info
   }

    public String getId(){
        return id;
    }

    public String getCityDestination(){
        return cityDest;
    }
}
