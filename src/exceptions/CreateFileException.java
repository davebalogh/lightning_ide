package exceptions;

public class CreateFileException extends Exception {
    public CreateFileException(){
    }

    public CreateFileException(String message){
        super(message);
    }

    public CreateFileException(Throwable cause){
        super(cause);
    }

    public CreateFileException(String message, Throwable cause){
        super(message, cause);
    }
}
