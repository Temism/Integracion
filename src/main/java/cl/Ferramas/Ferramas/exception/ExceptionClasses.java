package cl.Ferramas.Ferramas.exception;

public class ExceptionClasses extends  RuntimeException{

    public ExceptionClasses(String mensaje){
        super(mensaje);

    }
    public ExceptionClasses(String message, Throwable cause) {
        super(message, cause);
    }

}

