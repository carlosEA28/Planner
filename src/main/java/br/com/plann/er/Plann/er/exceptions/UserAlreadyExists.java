package br.com.plann.er.Plann.er.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("This user already exists");
    }
}
