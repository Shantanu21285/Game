package com.badlogic.TankStars;

abstract public class Tank
{
    private Player player;
    private Shooter shooter;

    abstract public String spawnTank();

    abstract public String spawnTankinGame_1();
    abstract public String spawnTankinGame_2();
    public Tank returnTank()
    {
        return this;
    }

}
