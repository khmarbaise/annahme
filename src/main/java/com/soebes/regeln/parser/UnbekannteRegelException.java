package com.soebes.regeln.parser;

public class UnbekannteRegelException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1505851328166130090L;

    public UnbekannteRegelException() {
        super();
    }

    public UnbekannteRegelException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnbekannteRegelException(String message) {
        super(message);
    }

    public UnbekannteRegelException(Throwable cause) {
        super(cause);
    }

}
