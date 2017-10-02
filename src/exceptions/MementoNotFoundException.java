package exceptions;

public class MementoNotFoundException extends Exception {

    public MementoNotFoundException(){
    }

    public MementoNotFoundException(String message){
        super(message);
    }

    public MementoNotFoundException(Throwable cause){
        super(cause);
    }

    public  MementoNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
