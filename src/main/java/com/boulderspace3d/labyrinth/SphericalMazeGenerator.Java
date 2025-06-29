package com.boulderspace3d.labyrinth;

import java.util.*;

/**
 * Générateur de labyrinthe dans une sphère.
 */
public class SphericalMazeGenerator {
    private int size;
    private double radius;
    private Cell3D[][][] grid;
    private Random rand = new Random();

    public SphericalMazeGenerator(int size, double radius) {
        this.size   = size;
        this.radius = radius;
        grid = new Cell3D[size][size][size];
        initGrid();
    }

    private void initGrid() {
        int m = size/2;
        for(int i=0;i<size;i++) for(int j=0;j<size;j++) for(int k=0;k<size;k++){
            double dx=i-m, dy=j-m, dz=k-m;
            if(Math.sqrt(dx*dx+dy*dy+dz*dz) <= radius) {
                grid[i][j][k] = new Cell3D(i,j,k);
            }
        }
    }

    public void generate() {
        carve(grid[size/2][size/2][0]);
    }

    private void carve(Cell3D c) {
        c.visited = true;
        for (Cell3D n : neighbors(c)) {
            if (!n.visited) {
                removeWall(c,n);
                carve(n);
            }
        }
    }

    private List<Cell3D> neighbors(Cell3D c) {
        List<Cell3D> list = new ArrayList<>();
        int[][] dirs = {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
        for(int[] d:dirs){
            int nx=c.x+d[0], ny=c.y+d[1], nz=c.z+d[2];
            if(nx>=0&&ny>=0&&nz>=0&&nx<size&&ny<size&&nz<size&&grid[nx][ny][nz]!=null) {
                list.add(grid[nx][ny][nz]);
            }
        }
        Collections.shuffle(list, rand);
        return list;
    }

    private void removeWall(Cell3D a, Cell3D b) {
        int dx=b.x-a.x, dy=b.y-a.y, dz=b.z-a.z;
        if (dx==1) { a.walls[0]=false; b.walls[1]=false; }
        else if (dx==-1) { a.walls[1]=false; b.walls[0]=false; }
        else if (dy==1) { a.walls[2]=false; b.walls[3]=false; }
        else if (dy==-1) { a.walls[3]=false; b.walls[2]=false; }
        else if (dz==1) { a.walls[4]=false; b.walls[5]=false; }
        else if (dz==-1) { a.walls[5]=false; b.walls[4]=false; }
    }
}