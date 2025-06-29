package com.boulderspace3d.data;
import java.sql.*;import java.util.*;
public class DatabaseHelper {
    private static final String URL="jdbc:sqlite:game_records.db";
    static {
        try(Connection c=DriverManager.getConnection(URL);
            Statement s=c.createStatement()){
            s.execute("""
                CREATE TABLE IF NOT EXISTS records (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  username TEXT,
                  level INTEGER,
                  survivalTime REAL,
                  dodgeRatio REAL,
                  colorTargetScore REAL,
                  isBigBoss INTEGER DEFAULT 0,
                  timestamp INTEGER
                )
            """);
            s.execute("""
                CREATE TABLE IF NOT EXISTS player_stats (
                  username TEXT PRIMARY KEY,
                  elo INTEGER DEFAULT 1,
                  bigBossKills INTEGER DEFAULT 0
                )
            """);
            s.execute("""
                CREATE TRIGGER IF NOT EXISTS trg_after_record_insert
                AFTER INSERT ON records
                BEGIN
                  INSERT OR IGNORE INTO player_stats(username) VALUES (NEW.username);
                  UPDATE player_stats
                    SET elo = MAX(elo, NEW.level * 100)
                  WHERE username = NEW.username;
                  UPDATE player_stats
                    SET bigBossKills = bigBossKills + NEW.isBigBoss
                  WHERE username = NEW.username;
                END;
            """);
        } catch(SQLException e){e.printStackTrace();}
    }
    public static void insertRecord(String username,int level,double survivalTime,double dodgeRatio,double colorTargetScore,boolean isBigBoss){
        String sql="INSERT INTO records(username,level,survivalTime,dodgeRatio,colorTargetScore,isBigBoss,timestamp) VALUES(?,?,?,?,?,?,?)";
        try(Connection c=DriverManager.getConnection(URL);
            PreparedStatement p=c.prepareStatement(sql)){
            p.setString(1,username);
            p.setInt(2,level);
            p.setDouble(3,survivalTime);
            p.setDouble(4,dodgeRatio);
            p.setDouble(5,colorTargetScore);
            p.setInt(6,isBigBoss?1:0);
            p.setLong(7,System.currentTimeMillis());
            p.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }
    public static Optional<PlayerStats> fetchPlayerStats(String username){
        try(Connection c=DriverManager.getConnection(URL);
            PreparedStatement p=c.prepareStatement("SELECT username,elo,bigBossKills FROM player_stats WHERE username=?")){
            p.setString(1,username);
            try(ResultSet rs=p.executeQuery()){
                if(rs.next()) return Optional.of(new PlayerStats(
                        rs.getString("username"),rs.getInt("elo"),rs.getInt("bigBossKills")
                ));
            }
        }catch(SQLException e){e.printStackTrace();}
        return Optional.empty();
    }
    public static class PlayerStats{
        public final String username; public final int elo,bigBossKills;
        public PlayerStats(String u,int e,int k){username=u;elo=e;bigBossKills=k;}
    }
}

