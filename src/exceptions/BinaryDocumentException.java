package exceptions;

public class BinaryDocumentException extends Exception{
    public BinaryDocumentException(){
    }

    public BinaryDocumentException(String message){
        super(message);
    }

    public BinaryDocumentException(Throwable cause){
        super(cause);
    }

    public BinaryDocumentException(String message, Throwable cause){
        super(message, cause);
    }
}
