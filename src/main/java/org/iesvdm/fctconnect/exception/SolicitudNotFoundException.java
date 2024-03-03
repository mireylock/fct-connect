package org.iesvdm.fctconnect.exception;

public class SolicitudNotFoundException extends RuntimeException {
    public SolicitudNotFoundException (Long id) {
        super ("No encontrada solicitud con id: "+id);
    }
}
