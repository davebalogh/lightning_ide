package exceptions;

public class NotOpenDocumentException extends Exception{
    public NotOpenDocumentException(){
    }

    public NotOpenDocumentException(String message){
        super(message);
    }

    public NotOpenDocumentException(Throwable cause){
        super(cause);
    }

    public NotOpenDocumentException(String message, Throwable cause){
        super(message, cause);
    }
}
