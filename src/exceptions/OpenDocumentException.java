package exceptions;

public class OpenDocumentException extends Exception {
    public OpenDocumentException(){
    }

    public OpenDocumentException(String message){
        super(message);
    }

    public OpenDocumentException(Throwable cause){
        super(cause);
    }

    public OpenDocumentException(String message, Throwable cause){
        super(message, cause);
    }
}
