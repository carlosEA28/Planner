package br.com.plann.er.Plann.er.exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found");
    }
}
