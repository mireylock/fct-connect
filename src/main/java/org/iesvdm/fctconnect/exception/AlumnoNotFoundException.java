package org.iesvdm.fctconnect.exception;

public class AlumnoNotFoundException extends RuntimeException{
    public AlumnoNotFoundException(Long id) {
            super("No encontrado Alumno con id: " +id);
        }
}
