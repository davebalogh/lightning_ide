package exceptions;

public class NewDocumentException extends Exception {
    public NewDocumentException(){
    }

    public NewDocumentException(String message){
        super(message);
    }

    public NewDocumentException(Throwable cause){
        super(cause);
    }

    public NewDocumentException(String message, Throwable cause){
        super(message, cause);
    }
}
