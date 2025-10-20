
package dev.matheuslf.desafio.inscritos.Exception.CustomExceptions;

public class ObjectAlreadyExistsException extends RuntimeException {
    public ObjectAlreadyExistsException(String msg) {
        super(msg);
    }
}
