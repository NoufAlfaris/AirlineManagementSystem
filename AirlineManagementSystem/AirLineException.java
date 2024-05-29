//user defined exception, if the number of flights in an airline 
//is less than 50, airline won't be created and an exception is thrown
public class AirLineException extends Exception {

  public AirLineException (String airNum){
      super(airNum);

    }
}
