package com.boulderspace3d.doors;
import com.boulderspace3d.textures.TextureManager;
public class DoorGenerator{private final TextureManager tm;public DoorGenerator(TextureManager tm){this.tm=tm;}public ColoredDoor generate(ColoredDoor.Color color,int kills){ColoredDoor d=new ColoredDoor(color,kills);d.setColor(tm.getRandomColorForEntity("door"));return d;}}