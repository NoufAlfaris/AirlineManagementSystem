//the Main class where the Airline system could be managed.
import java.util.Scanner;
public class AirlineManagementSystem {
   public static void main(String [] args) {
      Scanner read = new Scanner(System.in);  
      System.out.println("****Airline Management System****");                                                                                                                                             
      System.out.println("----Create Airline----");  //Ask user for airline info then create an object of type airline
      System.out.print("Enter airline name: ");
      String airlineName = read.nextLine();
      System.out.print("Enter maximum flights capacity: ");
      int maximumFlights = read.nextInt();
      Airline a = new Airline(airlineName, maximumFlights);   
      char choice = ' '; //declare and initialize choice variable to use in menue
   
      do {
         System.out.println();
         System.out.println("Choose one of the following services:");
         System.out.println("1. Add flight");
         System.out.println("2. Remove flight");
         System.out.println("3. Add passanger to a flight"); //search for flight by destination then add a passenger if available
         System.out.println("4. Remove passenger from a flight"); //remove a passenger if they want to cancel their booking
         System.out.println("5. Display airline information");
         System.out.println("6. Exit");
         System.out.print("Your choice: ");  //Ask user what service they want
      
         choice = read.next().charAt(0);
         read.nextLine();
      
         switch (choice) {
            case '1':
               System.out.println();
               System.out.println("----Add Flight----");  //Ask user for fight info then create object of type Flight then initialize based on type (local or international)
               System.out.println("Enter flight information:");
               System.out.print("Flight ID: ");
               String addId = read.next();
            
               System.out.print("Local or International flight? (Enter L for local and I for International): ");
               char type = read.next().charAt(0);
               read.nextLine();
               
               System.out.print("City destination: ");
               String cityDest = read.nextLine();
               
               String countryDest;
               
               if (type == 'I' || type == 'i') {
                  System.out.print("Country destination: "); //ask for countryDest if flight is international
                  countryDest = read.nextLine();
               }
               else 
                  countryDest = "KSA";
            
               System.out.print("Duration in hours: ");
               double duration = read.nextDouble();
               
               System.out.println("Date:");
               System.out.print("Day: ");
               int day = read.nextInt();
               System.out.print("Month: ");
               int month = read.nextInt();
               System.out.print("Year: ");
               int year = read.nextInt();
            
               System.out.print("Maximum number of passengers: ");
               int size = read.nextInt(); 
            
               Flight fAdded; //declare object of type Flight
            
               if(type == 'I' || type == 'i') //initialize f based on flight type
                  fAdded = new InternationalFlight(addId, cityDest, countryDest, duration, day, month, year, size); //International flight
               else
                  fAdded = new LocalFlight(addId, cityDest, countryDest, duration, day, month, year, size); //local flight
               
            
               if(a.addFlight(fAdded)) //addFlight(Flight f) method checks if there is space in fList and returns true if there is, otherwise it returns false
                  System.out.println("Flight added successfully"); 
               else
                  System.out.println("Failed to add flight, airline has reached maximum capacity");
               
               System.out.println();
               break;
               
            case '2':
               System.out.println();
               System.out.println("----Remove Flight----");
               System.out.print("Enter Flight ID: ");
               String removeId = read.next(); //Ask user for flight ID they want removed
               if (a.removeFlight(removeId)) //removeFlight(String id) returns true if flight with following id is found and removed, returns false otherwise
                  System.out.println("Flight removed successfully");
               else
                  System.out.println("Failed to remove flight, flight with that ID is not found");
               
               System.out.println();   
               break;
                       
            case '3':
               System.out.println();
               System.out.println("----Add Passenger to a Flight----"); 
               System.out.print("Enter passenger's desired city destination: ");
               String desiredDestination = read.nextLine(); //Ask user for where they want to go               
               Flight bookFlight = a.searchFlightByDest(desiredDestination);

               if(bookFlight == null)
                  System.out.println("There aren't any available flights going to " + desiredDestination); //Flight not found
               else {
                  System.out.print("Enter passenger's first name: ");
                  String firstN = read.next();
                  System.out.print("Enter passenger's last name: "); 
                  String lastN = read.next();
                  System.out.print("Enter passenger's National ID: ");
                  String nationalId = read.next();
                  System.out.print("Enter passenger's age: ");
                  int age = read.nextInt();
                  System.out.print("Enter passenger's profession: ");
                  read.nextLine();
                  String profession = read.nextLine();
               
                  Passenger pAdded = new Passenger(firstN , lastN , nationalId, age, profession);

                  if(bookFlight.addPassenger(pAdded)) {
                     System.out.println("Passenger added to flight with following ID: " + bookFlight.getId()); 
                     if (pAdded.getAge() <= 2) //check if passenger is a child 2 years old or younger, ticket is free if they are
                        System.out.println("Ticket is free for kids aged 2 and younger");
                     else 
                        System.out.println("Ticket price: " + bookFlight.getTicketPrice(nationalId) + "SAR");
                     }
                  else
                     System.out.println("The passenger already exist in the flight, please ask the passenger to check their reservations");
               }
               
               System.out.println(); 
               break;

            case '4':
               System.out.println();
               System.out.println("----Remove Passenger from a Flight----");
               System.out.println("Enter the flight ID:");
               String id = read.next();
               Flight f1 = a.searchFlightByID(id);
               if(f1==null)
                  System.out.println("No such flight exist.");
               else{
                  System.out.println("Enter the passenger's national ID:");
                  String nationalId = read.next();
                  if(f1.removePassenger(nationalId))
                     System.out.println("Passenger removed successfully.");
                  else
                     System.out.println("The passenger doesn't have a booking on the flight.");

               }
               break;
            
            case '5':
               System.out.println();
               System.out.println(a); //display all info using toString() method
               System.out.println();
               break;

         
             
            default:
               System.exit(0);  //exit program  
         } 
      
      } while (choice != '6'); 
   
   }
}
