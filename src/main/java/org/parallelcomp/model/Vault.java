package org.parallelcomp.model;

public class Vault {
    private final int password;

    public Vault(int password) {
        if (password < 0 || password > 9999)
            throw new IllegalArgumentException("Password must be between 0 and 9999");
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

    //! I added this function to be able to implement the binary search hacker!
    public int comparePassword(int guess){
        try {
            Thread.sleep(5); // Simulate 5ms verification delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (guess < password) return -1;
        if (guess > password) return 1;
        return 0;
    }
    public int getPassword() {
        return password;
    }
}