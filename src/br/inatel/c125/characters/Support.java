package br.inatel.c125.characters;

import br.inatel.c125.general.Settings;

public class Support extends Character implements SpecialMove {

    public Support(String name, int height, int weight) {
        super(name, height, weight);
    }

    public void interfere(Character character) {
        System.out.println(this.name + ": Interfering with " + character.name + "...");
    }

    @Override
    public void signature(Character character) {

        System.out.println(this.name + " performed a signature move on " + character.name);

        if (character.health > Settings.getSupportSignatureReduction()) {
            character.health -= Settings.getSupportSignatureReduction();
        } else {
            character.health = 0;
        }
    }
}