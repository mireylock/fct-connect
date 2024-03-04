package org.iesvdm.fctconnect.exception;
public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Long id, Class entity) {
        super("No encontrado " + entity.getSimpleName() + " con id: " + id);
    }
}
