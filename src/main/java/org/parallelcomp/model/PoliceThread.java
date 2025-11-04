package org.parallelcomp.model;

import org.parallelcomp.ui.VaultHackingGUI;

public class PoliceThread extends Thread {
    private VaultHackingGUI gui;
    private int time;

    public PoliceThread(int time, VaultHackingGUI gui) {
        this.time = time;
        this.gui = gui;
        this.setName("Police Thread");
    }
    @Override
    public void run() {
        while (this.time > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (gui != null && gui.isGameOver()) {
                return;
            }
            this.time--;
            System.out.println("Time remaining: " + this.time + " seconds");
        }
        String message = "Game over for you hackers";
        System.out.println(message);
        if (gui != null) {
            gui.setWinner(message);
        }
        else {
            System.exit(0);
        }
    }


    public int getTimeRemaining() {
        return this.time;
    }
}