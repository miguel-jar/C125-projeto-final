package br.inatel.c125.characters;

import br.inatel.c125.general.Settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Fighter extends Character implements SpecialMove {

    private int stamina;
    private final int strength;
    protected Support support;

    public Support getSupport() {
        return support;
    }

    public Fighter(String name, int height, int weight, int stamina, int strength, boolean hasSupport) {

        super(name, height, weight);

        this.stamina = stamina;
        this.strength = strength;

        if (hasSupport) {
            try {
                defineSupport();
            } catch (IOException e) {
                System.out.println("Could not define support.");
                this.support = null;
            }

        } else
            this.support = null;
    }

    private void defineSupport() throws IOException {
        // Lembre-se de renomear o arquivo .txt se necessário para manter a consistência
        Path supportFile = Paths.get("src/br/inatel/c125/files/standards/support.txt");

        String[] parameters = Files.readAllLines(supportFile).get(0).split(",");

        String name;
        int height, weight;

        name = parameters[0];
        height = Integer.parseInt(parameters[1]);
        weight = Integer.parseInt(parameters[2]);

        this.support = new Support(name, height, weight);
    }

    @Override
    public void run() {

        if (stamina > Settings.getFighterStaminaReduction()) {
            stamina -= Settings.getFighterStaminaReduction();
            System.out.println(this.name + ": Running...");
        } else {
            stamina = 0;
            this.walk();
        }
    }

    public void kick(Character character) {

        if (character.health > strength) {
            character.health -= strength;

            if (character instanceof Referee)
                System.out.println(this.name + ": Kicking Referee...");
            else
                System.out.println(this.name + ": Kicking...");
        } else
            this.finisher(character);
    }

    public void punch(Character character) {

        if (character.health > strength) {
            character.health -= strength;
            System.out.println(this.name + ": Punching...");
        } else
            this.finisher(character);
    }

    public void interactWithCrowd() {
        System.out.println(this.name + ": Are you enjoying this???");
    }

    public void tauntEnemy() {
        System.out.println(this.name + ": COCOH COCOH COCOH !!!");
    }

    public void finisher(Character character) {

        System.out.println(this.name + ": Finishing " + character.name);

        if (character.health > Settings.getFighterFinisherReduction()) {
            character.health -= Settings.getFighterFinisherReduction();
        } else
            character.health = 0;
    }

    @Override
    public void signature(Character character) {
        if (character.health > Settings.getFighterSignatureReduction()) {
            character.health -= Settings.getFighterSignatureReduction();
            System.out.println(this.name + ": Performing special attack...");
        } else
            this.finisher(character);
    }

    public void comeback() {

        System.out.println(this.name + " retreated to catch some air");

        stamina += 20;
        health += 15;

        if (health > 100) {
            health = 100;
        }

        if (stamina > 100) {
            stamina = 100;
        }
    }

    public void pin(Fighter fighter, Referee referee) {

        System.out.println(this.name + " pinning " + fighter.name);

        if (fighter.health > 0)
            referee.countDown(fighter);
        else
            referee.countDown();
    }
}