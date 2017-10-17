package exceptions;

public class CloseDocumentException extends Exception {
    public CloseDocumentException(){
    }

    public CloseDocumentException(String message){
        super(message);
    }

    public CloseDocumentException(Throwable cause){
        super(cause);
    }

    public CloseDocumentException(String message, Throwable cause){
        super(message, cause);
    }
}
