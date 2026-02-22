package br.inatel.c125.characters;

public class Referee extends Character {

    public Referee(String name, int height, int weight) {
        super(name, height, weight);
    }

    public void countDown() {
        System.out.println("\n" + this.name + ": 1... 2... 3... The match is over!!");
    }

    public void countDown(Fighter fighter) {

        System.out.println("\n" + this.name + ": 1... 2...");

        try {
            fighter.support.interfere(this);
            System.out.println("\n" + fighter.name + ": KickOut !!!");
        } catch (NullPointerException e) {
            System.out.println("\n" + fighter.name + ": KickOut !!!");
        }
    }

    public void ejectSupport(Fighter fighter) {
        System.out.println("\n" + this.name + ": Support " + fighter.support.name + " ejected!");
        fighter.support = null;
    }

    public void disqualifyFighter(Fighter fighter) {
        fighter.health = -1;
        System.out.println("\n" + this.name + ": Fighter " + fighter.name + " disqualified!");
    }

}