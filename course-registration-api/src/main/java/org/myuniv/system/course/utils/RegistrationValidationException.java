package org.myuniv.system.course.utils;

public class RegistrationValidationException extends RuntimeException {
    public RegistrationValidationException(String message) {
        super("Can't register for course- " + message);
    }
}
