package org.parallelcomp;

public class DescendingHackerThread extends HackerThread {
    public DescendingHackerThread(Vault vault) {
        super(vault);
        this.setName("DescendingHacker");
    }

    @Override
    public void guessPassword() {
        for (int guess = 9999; guess >= 0; guess--) {
            if (vault.isCorrectPassword(guess)) {
                reportSuccess(guess);
            }
        }
    }
}