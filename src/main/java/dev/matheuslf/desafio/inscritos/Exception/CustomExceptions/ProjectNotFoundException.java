
package dev.matheuslf.desafio.inscritos.Exception.CustomExceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(String msg) {
        super(msg);
    }
}
