package org.parallelcomp;

public class AscendingHackerThread extends HackerThread {
    public AscendingHackerThread(Vault vault) {
        super(vault);
        this.setName("AscendingHacker");
    }

    @Override
    public void guessPassword() {
        for (int guess = 0; guess <= 9999; guess++) {
            if (vault.isCorrectPassword(guess)) {
                reportSuccess(guess);
            }
        }
    }
}
