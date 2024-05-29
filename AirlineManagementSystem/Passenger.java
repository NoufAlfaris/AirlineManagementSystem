//a class that serves as a blueprint for individuals who book a flight within an airline system.
import java.io.*;
public class Passenger implements Serializable{
    
    private String firstName;
    private String lastName;
    private String nationalID;
    private int age;
    private String profession;


   public Passenger(String fName, String lName, String id, int age, String profession)throws NegativeAge,IdException{
      firstName=fName;
      lastName=lName;
      if(id.length()!=10)
         throw new IdException("National ID must consist of 10 Numbers");
      else
         nationalID=id;

      if(age<0)
         throw new NegativeAge("Age can't be negative");
      else
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
