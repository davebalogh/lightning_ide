package exceptions;

public class SaveDocumentException extends Exception {
    public SaveDocumentException(){
    }

    public SaveDocumentException(String message){
        super(message);
    }

    public SaveDocumentException(Throwable cause){
        super(cause);
    }

    public SaveDocumentException(String message, Throwable cause){
        super(message, cause);
    }
}
