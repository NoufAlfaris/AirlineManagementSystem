//a class that serves as a blueprint for individuals who book a flight within an airline system.
public class Passenger{
    
    private String firstName;
    private String lastName;
    private String nationalID;
    private int age;
    private String profession;


   public Passenger(String fName, String lName, String id, int age, String profession){
      firstName=fName;
      lastName=lName;
      nationalID=id;
      this.age=age;
      this.profession = profession;
   }

   public int getAge(){
      return age;
   }

   public String getProfession(){
      return profession;
   }

   public String getNationalID(){
      return nationalID;
   }

   //method toString show the passenger informations
   public String toString() {
      return "Full Name: " + firstName + " " + lastName + "\nNational ID: " + nationalID + "\nAge: " + age + "\nProfession: " + profession; //return passenger info
   }

}
