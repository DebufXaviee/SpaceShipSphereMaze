package com.boulderspace3d.labyrinth;

/** Cellule 3D pour le labyrinthe sphérique. */
public class Cell3D {
    public int x,y,z;
    public boolean visited;
    public boolean[] walls = new boolean[6];

    public Cell3D(int x,int y,int z) {
        this.x = x; this.y = y; this.z = z;
        visited = false;
        for(int i=0;i<6;i++) walls[i] = true;
    }
}