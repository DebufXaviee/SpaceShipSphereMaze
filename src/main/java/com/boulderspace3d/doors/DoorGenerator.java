package com.boulderspace3d.doors;

import com.boulderspace3d.textures.TextureManager;

/** Génère des portes avec couleur aléatoire. */
public class DoorGenerator {
    private final TextureManager tm;

    public DoorGenerator(TextureManager tm) {
        this.tm = tm;
    }

    public ColoredDoor generate(ColoredDoor.Color color, int kills) {
        ColoredDoor door = new ColoredDoor(color, kills);
        door.setColor(tm.getRandomColorForEntity("door"));
        return door;
    }
}