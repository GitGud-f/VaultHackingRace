package org.parallelcomp.model;

import org.parallelcomp.ui.VaultHackingGUI;

public class DescendingHackerThread extends HackerThread {
    public DescendingHackerThread(Vault vault, VaultHackingGUI gui) {
        super(vault, gui);
        this.setName("DescendingHacker");
    }

    @Override
    public void guessPassword() {
        for (int guess = 9999; guess >= 0; guess--) {
            this.currentGuess = guess;
            if (gui != null && gui.isGameOver()) {
                return;
            }
            if (vault.isCorrectPassword(guess)) {
                reportSuccess(guess);
                return;
            }
        }
    }

    @Override
    public int getProgress() {
        return 9999 - currentGuess; // Convert to ascending progress for visualization
    }
}