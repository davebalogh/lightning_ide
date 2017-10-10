package exceptions;

public class FileErrorException extends Exception {
    public FileErrorException(){
    }

    public FileErrorException(String message){
        super(message);
    }

    public FileErrorException(Throwable cause){
        super(cause);
    }

    public FileErrorException(String message, Throwable cause){
        super(message, cause);
    }
}
