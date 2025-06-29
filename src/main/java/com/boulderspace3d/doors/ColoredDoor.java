package com.boulderspace3d.doors;

/**
 * Porte colorée qui nécessite un certain nombre de kills.
 */
public class ColoredDoor {
    public enum Color { RED, YELLOW, VIOLET }
    private Color color;
    private int requiredKills;
    private float[] doorColor;

    public ColoredDoor(Color color, int kills) {
        this.color = color;
        this.requiredKills = kills;
    }

    public Color getColor()         { return color; }
    public int getRequiredKills()   { return requiredKills; }
    public void setColor(float[] c) { this.doorColor = c; }
    public float[] getDoorColor()   { return doorColor; }
}