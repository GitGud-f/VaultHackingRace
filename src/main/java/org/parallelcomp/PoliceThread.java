package org.parallelcomp;

public class PoliceThread extends Thread {
    @Override
    public void run() {
        for (int i = 10; i >= 0; i--) {
            try {
                Thread.sleep(1000); // 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Time remaining: " + i + " seconds");
        }
        System.out.println("Game over for you hackers");
        System.exit(0);
    }
}