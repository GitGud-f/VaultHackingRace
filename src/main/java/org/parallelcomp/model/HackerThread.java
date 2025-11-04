package org.parallelcomp.model;

import org.parallelcomp.ui.VaultHackingGUI;

public abstract class HackerThread extends Thread {
    protected VaultHackingGUI gui;
    protected final Vault vault;
    protected int currentGuess;
    protected int maxGuesses = 10000;

    public HackerThread(Vault vault, VaultHackingGUI gui) { this.vault = vault; this.gui = gui; this.currentGuess = 0; }

    // Abstract method: each subclass defines its own guessing algorithm
    public abstract void guessPassword();

    @Override
    public final void run() {
        guessPassword(); // enforce that run() calls the strategy
    }

    // Helper to report success and terminate cleanly
    protected void reportSuccess(int guess) {
        String message = this.getName() + " guessed the password: " + guess;
        if (gui != null) {
            gui.setWinner(message);
        } else {
            System.out.println(message);
            System.exit(0);
        }
        return;
    }

    public int getCurrentGuess() {
        return currentGuess;
    }

    public int getMaxGuesses() {
        return maxGuesses;
    }

    public int getProgress() {
        return currentGuess;
    }
}