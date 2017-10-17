package exceptions;

public class DeleteDocumentException extends Exception {
    public DeleteDocumentException(){
    }

    public DeleteDocumentException(String message){
        super(message);
    }

    public DeleteDocumentException(Throwable cause){
        super(cause);
    }

    public DeleteDocumentException(String message, Throwable cause){
        super(message, cause);
    }
}
