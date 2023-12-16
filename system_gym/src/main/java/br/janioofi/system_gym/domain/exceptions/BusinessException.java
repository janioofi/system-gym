package br.janioofi.system_gym.domain.exceptions;

public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
