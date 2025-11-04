package org.parallelcomp.model;

import org.parallelcomp.ui.VaultHackingGUI;

public class AscendingHackerThread extends HackerThread {
    public AscendingHackerThread(Vault vault, VaultHackingGUI gui) {
        super(vault, gui);
        this.setName("Ascending Hacker");
    }

    @Override
    public void guessPassword() {
        for (int guess = 0; guess <= 9999; guess++) {
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
}
