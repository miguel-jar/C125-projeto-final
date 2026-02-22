package br.inatel.c125;

import br.inatel.c125.characters.Fighter;
import br.inatel.c125.characters.Referee;
import br.inatel.c125.general.Settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        try {
            Settings.setSettings();

            int playAgain;
            Scanner scanner = new Scanner(System.in);

            do {

                Fighter player, enemy;

                System.out.println("\n...................................... Character Selection ......................................");

                try {

                    String[] parameters = Settings.chooseFighter(scanner);

                    String name;
                    int height, weight, stamina, strength;
                    boolean support;

                    name = parameters[0];
                    height = Integer.parseInt(parameters[1]);
                    weight = Integer.parseInt(parameters[2]);
                    stamina = Integer.parseInt(parameters[3]);
                    strength = Integer.parseInt(parameters[4]);
                    support = Boolean.parseBoolean(parameters[5]);

                    player = new Fighter(name, height, weight, stamina - Settings.getStaminaModifier(), strength - Settings.getStrengthModifier(), support);

                    System.out.println("\nFighter " + name + " selected");

                } catch (IOException e) {
                    System.out.println("Error: Could not select player. Restart the game and try again.");
                    break;
                }

                try {

                    String[] parameters = Settings.chooseFighter(scanner);

                    String name;
                    int height, weight, stamina, strength;
                    boolean support;

                    name = parameters[0];
                    height = Integer.parseInt(parameters[1]);
                    weight = Integer.parseInt(parameters[2]);
                    stamina = Integer.parseInt(parameters[3]);
                    strength = Integer.parseInt(parameters[4]);
                    support = Boolean.parseBoolean(parameters[5]);

                    enemy = new Fighter(name, height, weight, stamina + Settings.getStaminaModifier(), strength + Settings.getStrengthModifier(), support);

                    System.out.println("\nEnemy " + name + " selected\n");

                } catch (IOException e) {
                    System.out.println("Error: Could not select enemy. Restart the game and try again.");
                    break;
                }

                System.out.println("...................................... Difficulty Selection ......................................");

                try {
                    Settings.setDifficulty(scanner);
                } catch (IOException e) {
                    System.out.println("Error: Could not set difficulty. Restart the game and try again.");
                    break;
                }

                Referee referee;

                try {
                    Path supportFile = Paths.get("src/br/inatel/c125/files/standards/referee.txt");

                    String[] parameters = Files.readAllLines(supportFile).get(0).split(",");

                    String name;
                    int height, weight;

                    name = parameters[0];
                    height = Integer.parseInt(parameters[1]);
                    weight = Integer.parseInt(parameters[2]);

                    referee = new Referee(name, height, weight);

                } catch (IOException e) {
                    System.out.println("Error: Could not create referee. Restart the game and try again.");
                    break;
                }

                Random r = new Random();

                System.out.println("\nStarting match...\n");

                System.out.println("............................................ Simulation ............................................\n");

                while ((player.getHealth() > 0) && (enemy.getHealth() > 0)) {

                    int choice = r.nextInt(0, 54);

                    switch (choice) {
                        case 0, 1, 2, 3, 4, 5, 6, 7 -> {
                            player.run();
                            player.kick(enemy);
                        }

                        case 8, 9, 10, 11, 12, 13, 14, 15 -> {
                            player.jump();
                            player.punch(enemy);
                        }

                        case 16, 17, 18 -> {
                            player.signature(enemy);
                            player.pin(enemy, referee);
                        }

                        case 19 -> {
                            player.finisher(enemy);
                            player.pin(enemy, referee);
                        }

                        case 20 -> {
                            player.jump();
                            player.kick(referee);
                            referee.disqualifyFighter(player);
                        }

                        case 21, 22 -> player.comeback();

                        case 23, 24 -> {
                            try {
                                player.getSupport().interfere(enemy);
                            } catch (NullPointerException e) {
                                player.tauntEnemy();
                            }
                        }

                        case 25, 26 -> {
                            try {
                                player.getSupport().signature(enemy);
                                referee.ejectSupport(player);
                            } catch (NullPointerException e) {
                                player.interactWithCrowd();
                            }
                        }

                        case 27, 28, 29, 30, 31, 32, 33, 34 -> {
                            enemy.run();
                            enemy.punch(player);
                        }

                        case 35, 36, 37, 38, 39, 40, 41, 42 -> {
                            enemy.jump();
                            enemy.kick(player);
                        }

                        case 43, 44, 45 -> {
                            enemy.signature(player);
                            enemy.pin(player, referee);
                        }

                        case 46 -> {
                            enemy.finisher(player);
                            enemy.pin(player, referee);
                        }

                        case 47 -> {
                            enemy.jump();
                            enemy.punch(referee);
                            referee.disqualifyFighter(enemy);
                        }

                        case 48, 49 -> enemy.comeback();

                        case 50, 51 -> {
                            try {
                                enemy.getSupport().interfere(player);
                            } catch (NullPointerException e) {
                                enemy.tauntEnemy();
                            }
                        }

                        case 52, 53 -> {
                            try {
                                enemy.getSupport().signature(player);
                                referee.ejectSupport(enemy);
                            } catch (NullPointerException e) {
                                enemy.interactWithCrowd();
                            }
                        }
                    }

                    System.out.println();

                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

                if (enemy.getHealth() == -1) {
                    System.out.println("What a shame, you won!!");
                } else if (player.getHealth() == -1) {
                    System.out.println("Congratulations, you lost!!");
                } else if (player.getHealth() == 0) {
                    enemy.pin(player, referee);
                    System.out.println("\nCongratulations, you lost!!");
                } else {
                    player.pin(enemy, referee);
                    System.out.println("\nWhat a shame, you won!!");
                }


                do {
                    System.out.println("\nDo you want to play again?");
                    System.out.println(" [0] No ");
                    System.out.println(" [1] Yes ");
                    System.out.print("\nChoice: ");
                    playAgain = scanner.nextInt();
                } while ((playAgain > 1) || (playAgain < 0));

            } while (playAgain == 1);

            scanner.close();

        } catch (IOException e) {
            System.out.println("Could not initialize the game. Please try again.");
            System.out.println(e);
        }
    }
}