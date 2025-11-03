package org.parallelcomp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the vault password (0–9999): ");
        int password = scanner.nextInt();

        if (password < 0 || password > 9999) {
            System.err.println("Invalid password! Must be between 0 and 9999.");
            System.exit(1);
        }

        Vault vault = new Vault(password); // Change as needed

        HackerThread asc = new AscendingHackerThread(vault);
        HackerThread desc = new DescendingHackerThread(vault);
        // HackerThread bins = new BinarySearchHackerThread(vault);
        // HackerThread random = new RandomHackerThread(vault);
        PoliceThread police = new PoliceThread();

        desc.setPriority(Thread.MAX_PRIORITY);
        asc.setPriority(Thread.MAX_PRIORITY);
        // bins.setPriority(Thread.MAX_PRIORITY);
        // random.setPriority(Thread.MAX_PRIORITY);

        police.start();
        asc.start();
        desc.start();
        // bins.start();
        // random.start();
    }
}