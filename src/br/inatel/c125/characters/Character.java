package br.inatel.c125.characters;

public abstract class Character {
    protected final String name;
    protected final int height, weight;
    protected int health;

    public Character(String name, int height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.health = 100;
    }

    public int getHealth() {
        return health;
    }

    public void walk() {
        System.out.println(this.name + ": Walking...");
    }

    public void run() {
        System.out.println(this.name + ": Running...");
    }

    public void jump() {
        System.out.println(this.name + ": Jumping...");
    }
}