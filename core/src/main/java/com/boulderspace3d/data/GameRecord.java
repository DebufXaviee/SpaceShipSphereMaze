package com.boulderspace3d.data;
public class GameRecord {
    private final int id, level;
    private final double survivalTime, dodgeRatio, colorTargetScore;
    private final long timestamp;
    public GameRecord(int id,int level,double survivalTime,double dodgeRatio,double colorTargetScore,long timestamp){
        this.id=id; this.level=level; this.survivalTime=survivalTime;
        this.dodgeRatio=dodgeRatio; this.colorTargetScore=colorTargetScore;
        this.timestamp=timestamp;
    }
    public int getId(){return id;}
    public int getLevel(){return level;}
    public double getSurvivalTime(){return survivalTime;}
    public double getDodgeRatio(){return dodgeRatio;}
    public double getColorTargetScore(){return colorTargetScore;}
    public long getTimestamp(){return timestamp;}
}
