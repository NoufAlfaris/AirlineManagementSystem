//user defined exception, if the user entered a negative age (impossible)
public class NegativeAge extends Exception{

    public NegativeAge (String age){

        super(age);
    }
    
}
