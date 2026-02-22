package br.inatel.c125.general;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Settings {

    private static boolean isPlayer = true;

    private static int staminaModifier, strengthModifier;
    private static int fighterStaminaReduction, fighterFinisherReduction, fighterSignatureReduction;
    private static int supportSignatureReduction;

    public static int getStaminaModifier() {
        return staminaModifier;
    }

    public static int getStrengthModifier() {
        return strengthModifier;
    }

    public static int getFighterStaminaReduction() {
        return fighterStaminaReduction;
    }

    public static int getFighterFinisherReduction() {
        return fighterFinisherReduction;
    }

    public static int getFighterSignatureReduction() {
        return fighterSignatureReduction;
    }

    public static int getSupportSignatureReduction() {
        return supportSignatureReduction;
    }

    public static void setSettings() throws IOException {
        // Lembre-se de renomear a pasta e o arquivo para manter o padrão se desejar
        Path settingsFile = Paths.get("src/br/inatel/c125/files/standards/settings.txt");
        List<String> settings = Files.readAllLines(settingsFile);

        fighterStaminaReduction = Integer.parseInt(settings.get(0).split("=")[1]);
        fighterFinisherReduction = Integer.parseInt(settings.get(1).split("=")[1]);
        fighterSignatureReduction = Integer.parseInt(settings.get(2).split("=")[1]);

        supportSignatureReduction = Integer.parseInt(settings.get(3).split("=")[1]);
    }

    public static void setDifficulty(Scanner keyboard) throws IOException {

        Path difficultyFile = Paths.get("src/br/inatel/c125/files/difficulties.txt");
        List<String> difficulties = Files.readAllLines(difficultyFile);

        int choice;

        do {
            System.out.println("\nChoose difficulty:");

            for (int i = 0; i < difficulties.size(); i++) {
                System.out.println(" [" + i + "] " + difficulties.get(i).split(",")[0]);
            }

            System.out.print("\nChoice: ");
            choice = keyboard.nextInt();

            if ((choice > 2) || (choice < 0))
                System.out.println("\nInvalid choice. Try again.");

        } while ((choice >= difficulties.size()) || (choice < 0));

        String[] data = difficulties.get(choice).split(",");

        System.out.println("\nDifficulty set to " + data[0].toUpperCase());

        strengthModifier = Integer.parseInt(data[2]);
        staminaModifier = Integer.parseInt(data[1]);
    }

    public static String[] chooseFighter(Scanner keyboard) throws IOException {

        int playerChoice;

        Path fightersFile = Paths.get("src/br/inatel/c125/files/fighters.txt");
        List<String> fighters = Files.readAllLines(fightersFile);

        do {

            if (isPlayer)
                System.out.println("\nChoose your fighter:");
            else
                System.out.println("\nChoose your enemy:");

            for (int i = 0; i < fighters.size(); i++) {
                System.out.println(" [" + i + "] " + fighters.get(i).split(",")[0]);
            }

            System.out.print("\nChoice: ");
            playerChoice = keyboard.nextInt();

            if ((playerChoice < 0) || (playerChoice >= fighters.size()))
                System.out.println("\nInvalid choice. Try again.");

        } while ((playerChoice < 0) || (playerChoice >= fighters.size()));

        isPlayer = !isPlayer;

        return fighters.get(playerChoice).split(",");
    }
}