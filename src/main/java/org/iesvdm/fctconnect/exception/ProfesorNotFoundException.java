package org.iesvdm.fctconnect.exception;

public class ProfesorNotFoundException extends RuntimeException {
    public ProfesorNotFoundException(Long id) {
        super("No encontrado Profesor con id: " +id);
    }

}
