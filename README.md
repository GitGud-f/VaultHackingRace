# Vault Hacking Race

## Overview
This project is a simple parallel computing exercise designed to demonstrate the concepts of multi-threading in Java. It simulates a race between different hacker threads attempting to crack a vault's password while a police thread counts down to their arrival. The goal is to illustrate how threads can run concurrently, share resources (like the vault), and interact in a timed scenario.

The project includes:
- A console version (basic thread simulation).
- A GUI version using Swing to visualize progress bars for hackers and the police timer.

Key learning objectives:
- Creating and managing threads.
- Thread priorities and synchronization (implicit via shared vault access).
- Simulating delays to mimic real-world processing time.
- Handling thread termination and program exit.

This is an assignment for a parallel computing course at the Higher Institute for Applied Sciences and Technology (HIAST), focusing on threading basics.

## Requirements
- Java Development Kit (JDK) 8 or higher.
- No external libraries required (uses standard Java and Swing for GUI).

## Project Structure
```
src/
├── model/
│   ├── Vault.java                  # Vault class with password and verification logic
│   ├── HackerThread.java           # Abstract base for hacker threads
│   ├── AscendingHackerThread.java  # Hacker guessing from 0 to 9999
│   ├── DescendingHackerThread.java # Hacker guessing from 9999 to 0
│   ├── RandomHackerThread.java     # Hacker guessing randomly without repeats
│   ├── BinarySearchHackerThread.java # (Commented out) Efficient binary search hacker
│   └── PoliceThread.java           # Countdown timer thread for police arrival
├── ui/
│   └── VaultHackingGUI.java        # Swing-based GUI for visualization
└── Main.java                       # Entry point to launch GUI (or console version)
```

Note: The binary search hacker is commented out in the GUI but available in the model package for extension.

## How to Run
1. Clone the repository:
   ```
   git clone https://github.com/GitGud-f/VaultHackingRace
   ```
2. Compile the Java files:
   ```
   javac -d bin src/*.java src/model/*.java src/ui/*.java
   ```
3. Run the main class:
   ```
   java -cp bin Main
   ```
    - This launches the GUI version by default.
    - For the console version, uncomment the relevant line in `Main.java` and comment out the GUI launch.

### GUI Usage
- Click "Start Hacking Race" to begin.
- Watch progress bars for each hacker (ascending, descending, random) and the police timer.
- The simulation ends when a hacker cracks the password or the police timer reaches zero.
- Use "Restart" to run again.

### Console Version
- Runs in the terminal.
- Outputs guesses and timer updates in real-time.
- Ends with a message indicating the winner (hacker or police).

## Features
- **Random Password Generation**: Vault password is randomly set between 0 and 9999.
- **Thread Strategies**:
    - Ascending: Linear search from low to high.
    - Descending: Linear search from high to low.
    - Random: Unique random guesses to avoid duplicates.
    - (Optional) Binary: Efficient logarithmic search (commented out for basic demo).
- **Police Timer**: 10-second countdown with 1-second intervals.
- **Simulation Delay**: Each guess takes 5ms to simulate CPU time.
- **Thread Priorities**: Hackers set to maximum priority for fair competition.
- **Game Over Logic**: Uses `System.exit(0)` for simple termination (not ideal for production but suitable for demo).

## Expected Behavior
- Hackers race to guess the password concurrently.
- If a hacker succeeds first, it prints the password and ends the program.
- If the police timer expires (10 seconds), it ends the program with a "Game over" message.
- In GUI, progress is visualized; in console, outputs are printed.

## Extensions
- Uncomment and integrate the `BinarySearchHackerThread` to see how an efficient algorithm performs in a threaded environment.
- Add synchronization if needed for more complex shared state.
- Experiment with thread priorities or add more hackers to explore scheduling.

## License
This project is for educational purposes only. No license specified.
