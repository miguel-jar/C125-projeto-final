# 🤼 Wrestling Simulator CLI

A Java-based Command Line Interface (CLI) simulation game inspired by professional wrestling mechanics. This project features character attributes, special moves, referee interactions, and support/manager interference logic.

## 🚀 Features
* Character System: Uses inheritance with a base Character class and specific implementations for Fighter, Referee, and Support.

* Dynamic Attributes: Characters have health, stamina, and strength which affect their performance during the match.

* Special Moves: Implementation of Signature and Finishing moves through a SpecialMove interface.

* Match Simulation: A random-event-driven simulation where fighters can punch, kick, taunt, or perform "comebacks".

* Referee Logic: The referee manages the "Pin" counts and can disqualify fighters or eject meddling supports.

* External Configuration: Match balance (damage/stamina reduction) and character data are loaded from external .txt files.

## 🎮 How to Play
1. **Character Selection:** Choose your fighter and your opponent from the list loaded from fighters.txt.

2. **Difficulty:** Select a difficulty level which applies modifiers to the enemy's strength and stamina.

3. **The Match:** The simulation runs automatically.

    * Fighters will lose stamina as they run and jump.

    * If health is low, a fighter might attempt a Pin.

    * Watch out! If you hit the referee, you might be disqualified.

4. **Victory/Defeat:** The match ends when a fighter is pinned for a 3-count or disqualified.

## 🛠️ Project Structure

The project is organized into the following packages:

* **characters:** Contains the core logic for Fighter, Referee, Support, and the base Character.

* **others:** Contains Settings for game balance and file I/O management.

* **files:** Data storage for character stats, difficulty levels, and default settings.

## 📺 Presentation
You can watch the video demonstration of this project and its code structure [here](https://www.youtube.com/watch?v=0MR4HoaREXU)