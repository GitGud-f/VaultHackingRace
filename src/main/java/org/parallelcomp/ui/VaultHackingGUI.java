package org.parallelcomp.ui;

import org.parallelcomp.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class VaultHackingGUI extends JFrame {

    private Vault vault;
    private HackerThread ascending;
    private HackerThread descending;
    private HackerThread random;
    //private HackerThread binary;
    private PoliceThread police;

    private JProgressBar ascProgressBar;
    private JProgressBar descProgressBar;
    private JProgressBar randProgressBar;
    private JProgressBar binProgressBar;
    private JProgressBar policeProgressBar;

    private JButton startButton;
    private JLabel statusLabel;

    private javax.swing.Timer uiTimer;

    private volatile boolean gameOver = false;

    public static void launchGUI() {
        SwingUtilities.invokeLater(VaultHackingGUI::new);
    }

    private VaultHackingGUI() {
        Random rnd = new Random();
        int password = rnd.nextInt(10000);
        vault = new Vault(password);

        ascending = new AscendingHackerThread(vault, this);
        descending = new DescendingHackerThread(vault, this);
        random = new RandomHackerThread(vault, this);
        //binary = new BinarySearchHackerThread(vault, this);
        police = new PoliceThread(10, this);

        ascending.setPriority(Thread.MAX_PRIORITY);
        descending.setPriority(Thread.MAX_PRIORITY);
        random.setPriority(Thread.MAX_PRIORITY);
      //  binary.setPriority(Thread.MAX_PRIORITY);

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Vault Hacking Race");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        ascProgressBar = new JProgressBar(0, 10000);
        add(createHackerPanel("Ascending Hacker", ascProgressBar), gbc);
        gbc.gridy++;

        descProgressBar = new JProgressBar(0, 10000);
        add(createHackerPanel("Descending Hacker", descProgressBar), gbc);
        gbc.gridy++;

        randProgressBar = new JProgressBar(0, 10000);
        add(createHackerPanel("Random Hacker", randProgressBar), gbc);
        gbc.gridy++;

     //   binProgressBar = new JProgressBar(0, 10000);
      //  add(createHackerPanel("Binary Search Hacker", binProgressBar), gbc);
      //  gbc.gridy++;

        JPanel policePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        policePanel.add(new JLabel("Police Time Remaining:"));
        policeProgressBar = new JProgressBar(0, 10);
        policeProgressBar.setValue(10);
        policeProgressBar.setStringPainted(true);
        policePanel.add(policeProgressBar);
        add(policePanel, gbc);
        gbc.gridy++;

        startButton = new JButton("Start Hacking Race");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Race in progress...");
                startSimulation();
                startButton.setEnabled(false);
            }
        });
        add(startButton, gbc);
        gbc.gridy++;

        statusLabel = new JLabel("Press start to begin");
        add(statusLabel, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createHackerPanel(String name, JProgressBar progressBar) {
        progressBar.setStringPainted(true);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(name + ":"));
        panel.add(progressBar);
        return panel;
    }

    private void startSimulation() {
        uiTimer = new javax.swing.Timer(100, e -> updateProgressBars());
        uiTimer.start();

        ascending.start();
        descending.start();
        random.start();
      //  binary.start();
        police.start();
    }

    private void updateProgressBars() {
        if (ascending.isAlive()) {
            ascProgressBar.setValue(ascending.getProgress());
        }
        if (descending.isAlive()) {
            descProgressBar.setValue(descending.getProgress());
        }
        if (random.isAlive()) {
            randProgressBar.setValue(random.getProgress());
        }
      //  if (binary.isAlive()) {
       //     binProgressBar.setValue(binary.getProgress());
       // }
        policeProgressBar.setValue(police.getTimeRemaining());
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void setGameOver() {
        gameOver = true;
    }

    public void setWinner(String message) {
        setGameOver();
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText(message);
            uiTimer.stop();
        });
    }
}