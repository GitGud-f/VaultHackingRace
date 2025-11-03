package org.parallelcomp;

public abstract class HackerThread extends Thread {
    protected final Vault vault;

    public HackerThread(Vault vault) {
        this.vault = vault;
    }

    // Abstract method: each subclass defines its own guessing algorithm
    public abstract void guessPassword();

    @Override
    public final void run() {
        guessPassword(); // enforce that run() calls the strategy
    }

    // Helper to report success and terminate cleanly
    protected void reportSuccess(int guess) {
        System.out.println(this.getName() + " guessed the password: " + guess);
        System.exit(0);
    }
}