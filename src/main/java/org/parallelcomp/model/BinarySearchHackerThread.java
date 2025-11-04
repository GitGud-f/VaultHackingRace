package org.parallelcomp.model;

import org.parallelcomp.ui.VaultHackingGUI;

public class BinarySearchHackerThread extends HackerThread {
    private int low = 0;
    private int high = 9999;
    public BinarySearchHackerThread(Vault vault, VaultHackingGUI gui) {
        super(vault, gui);
        this.setName("Binary-Search Hacker");
    }

    @Override
    public void guessPassword() {
        while (low <= high) {
            if (gui != null && gui.isGameOver()) {
                return;
            }
            int mid = low + (high - low) / 2;
            this.currentGuess = mid;
            int result = vault.comparePassword(mid);

            if (result == 0) {
                reportSuccess(mid);
                return;
            } else if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // Should never reach here if password is in [0,9999]
        System.out.println(this.getName() + " failed to find the password.");
    }

    @Override
    public int getProgress() {
        return getMaxGuesses() - (high - low + 1);
    }
}
