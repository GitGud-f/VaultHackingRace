package org.parallelcomp.model;

import org.parallelcomp.ui.VaultHackingGUI;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomHackerThread extends HackerThread {
    private final Random random = new Random();
    private final Set<Integer> tried = new HashSet<>();

    public RandomHackerThread(Vault vault, VaultHackingGUI gui) {
        super(vault, gui);
        this.setName("Random Hacker");
    }

    @Override
    public void guessPassword() {
        while (tried.size() < 10000) {
            if (gui != null && gui.isGameOver()) {
                return;
            }
            int guess = random.nextInt(10000);
            if (tried.add(guess)) {
                this.currentGuess = tried.size();
                if (vault.isCorrectPassword(guess)) {
                    reportSuccess(guess);
                    return;
                }
            }
        }
    }
}