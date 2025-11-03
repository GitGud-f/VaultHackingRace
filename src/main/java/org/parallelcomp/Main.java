package org.parallelcomp;


public class Main {
    public static void main(String[] args) {
        Vault vault = new Vault(4500); // Change as needed

        HackerThread asc = new AscendingHackerThread(vault);
        HackerThread desc = new DescendingHackerThread(vault);
        // HackerThread bins = new BinarySearchHackerThread(vault);
        PoliceThread police = new PoliceThread();

        asc.setPriority(Thread.MAX_PRIORITY);
        desc.setPriority(Thread.MAX_PRIORITY);
        // bins.setPriority(Thread.MAX_PRIORITY);

        police.start();
        asc.start();
        desc.start();
        // bins.start();
    }
}