//user defined exception, if the entry for date is invalid
//throws new exception
public class MnthsException extends Exception  {
    
    public MnthsException(String Mnth){
        super(Mnth);
}
}