package com.boulderspace3d.data;

/**
 * Représente un enregistrement de partie.
 */
public class GameRecord {
    private final int id;
    private final int level;
    private final double survivalTime;
    private final double dodgeRatio;
    private final double colorTargetScore;
    private final long timestamp;

    public GameRecord(int id, int level,
                      double survivalTime,
                      double dodgeRatio,
                      double colorTargetScore,
                      long timestamp) {
        this.id               = id;
        this.level            = level;
        this.survivalTime     = survivalTime;
        this.dodgeRatio       = dodgeRatio;
        this.colorTargetScore = colorTargetScore;
        this.timestamp        = timestamp;
    }

    public int getId()                 { return id; }
    public int getLevel()              { return level; }
    public double getSurvivalTime()    { return survivalTime; }
    public double getDodgeRatio()      { return dodgeRatio; }
    public double getColorTargetScore(){ return colorTargetScore; }
    public long getTimestamp()         { return timestamp; }
}