package org.parallelcomp;

public class Vault {
    private final int password;

    public Vault(int password) {
        this.password = password;
    }

    public boolean isCorrectPassword(int guess) {
        try {
            Thread.sleep(5); // Simulate 5ms verification delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return this.password == guess;
    }
}