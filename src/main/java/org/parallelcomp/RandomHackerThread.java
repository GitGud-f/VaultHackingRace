package org.parallelcomp;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomHackerThread extends HackerThread {
    private final Random random = new Random();
    private final Set<Integer> tried = new HashSet<>();

    public RandomHackerThread(Vault vault) {
        super(vault);
        this.setName("Random Hacker");
    }

    @Override
    public void guessPassword() {
        while (tried.size() < 10000) {
            int guess = random.nextInt(10000);
            if (tried.add(guess)) { // true if not seen before
                if (vault.isCorrectPassword(guess)) {
                    reportSuccess(guess);
                    return;
                }
            }
        }
    }
}