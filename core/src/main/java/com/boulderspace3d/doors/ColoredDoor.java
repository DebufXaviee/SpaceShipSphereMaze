package com.boulderspace3d.doors;
public class ColoredDoor{
    public enum Color{RED,YELLOW,VIOLET}
    private Color color;
    private int requiredKills;private float[] doorColor;
    public ColoredDoor(Color c,int k){color=c;requiredKills=k;
    }public Color getColor(){return color;}
    public int getRequiredKills(){return requiredKills;}
    public void setColor(float[] c){doorColor=c;}
    public float[] getDoorColor(){return doorColor;}}