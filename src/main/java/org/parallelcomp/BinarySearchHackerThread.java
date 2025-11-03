package org.parallelcomp;

public class BinarySearchHackerThread extends HackerThread {
    public BinarySearchHackerThread(Vault vault) {
        super(vault);
        this.setName("Binary-Search Hacker");
    }

    @Override
    public void guessPassword() {
        int low = 0;
        int high = 9999;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int result = vault.comparePassword(mid);

            if (result == 0) {
                reportSuccess(mid);
                return;
            } else if (result < 0) {
                low = mid + 1;  // guess too low
            } else {
                high = mid - 1; // guess too high
            }
        }

        // Should never reach here if password is in [0,9999], added this forr safety only
        System.out.println(this.getName() + " failed to find the password.");
    }
}
