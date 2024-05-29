//user defined exception, if the format of passenger's national ID
//is invalid, throws new exception
public class IdException extends Exception{

    public IdException(String id){

        super(id);
    }
    
}
