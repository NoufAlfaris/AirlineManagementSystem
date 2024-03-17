//a class representing our Airline system and its functions
public class Airline{
   private String name;
   private int nOfFlights;
   private Flight fList[];

   public Airline (String name, int size){
      this.name=name;
      fList=new Flight[size];
      nOfFlights=0;
   }
   //method that checks for the abillity of opening new flight 
   public boolean addFlight(Flight f){
      if(nOfFlights<fList.length){
         if(f instanceof LocalFlight)
            fList[nOfFlights++] = new LocalFlight((LocalFlight) f);
         else
            fList[nOfFlights++] = new InternationalFlight((InternationalFlight) f);
         return true;
      }
      return false;
   }
   //method that remove flight if the trip is cancelled
   public boolean removeFlight(String id){
      for(int i=0;i<nOfFlights;i++)
         if(fList[i].getId().equals(id)){
            for(int j=i;j<nOfFlights-1;j++)
            fList[j]=fList[j+1];
         fList[--nOfFlights]=null;
         return true;
      }
      return false;
   }


   //method that search for an available plane to a specified destination
   public Flight searchFlightByDest(String cityDest) {
      for (int i=0 ; i<nOfFlights ; i++)
         if((fList[i].getCityDestination().equalsIgnoreCase(cityDest)) && (fList[i].isFlightAvailable())) //returns flight with same destination sent in the parameter if found and isn't full
            return fList[i];
     
      return null; //returns null if no flight has the same destination as the one sent in the parameter or it's full
   }
   //method that search for a specific flight by id
   public Flight searchFlightByID(String id) {
      for (int i=0 ; i<nOfFlights ; i++)
         if(fList[i].getId().equals(id)) //returns flight with same id sent in the parameter is found 
            return fList[i];
     
      return null; //returns null if no flight has the same id as the one sent in the parameter
   }
   

   //method toString to show the flight details
   public String toString () {
      String flights = "";
      for (int i = 0; i<nOfFlights; i++)
         flights += fList[i] + "\n"; //create string of all flights info
      return "Airline name: " + name + "\nNumber of Flights: " + nOfFlights + "\n\nFlights information:\n\n" + flights; //returns all airline info
   } 


}
