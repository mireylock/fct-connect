package org.iesvdm.fctconnect.exception;

public class EmpresaNotFoundException extends RuntimeException {
    public EmpresaNotFoundException(Long id) {
        super("No encontrado Empresa con id: " +id);
    }

}
