package exceptions;

public class OpenFileException extends Exception{
    public OpenFileException(){
    }

    public OpenFileException(String message){
        super(message);
    }

    public OpenFileException(Throwable cause){
        super(cause);
    }

    public OpenFileException(String message, Throwable cause){
        super(message, cause);
    }

}
