package com.example.confmanager.misc;

/**
 * Excepcion para errores manejados de app.
 *
 * @author yamil
 */
public class SchedulerException extends Exception {

    public SchedulerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchedulerException(String message) {
        super(message);
    }

}
