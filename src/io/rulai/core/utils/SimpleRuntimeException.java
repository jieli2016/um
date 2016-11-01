package io.rulai.core.utils;

public class SimpleRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 46466L;

    public SimpleRuntimeException(String message) {
        super(message);
    }

    public SimpleRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleRuntimeException(Throwable cause) {
        super(cause);
    }
}
