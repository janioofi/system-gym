package br.janioofi.system_gym.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(Long id){
        super("Record not found with id: " + id);
    }
}
