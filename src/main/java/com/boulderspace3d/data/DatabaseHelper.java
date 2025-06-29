package com.boulderspace3d.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestion de la base SQLite pour GameRecord.
 */
public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:game_records.db";

    static {
        try (Connection c = DriverManager.getConnection(URL);
             Statement s = c.createStatement()) {
            s.execute("""
                CREATE TABLE IF NOT EXISTS records (
                  id INTEGER PRIMARY KEY AUTOINCREMENT,
                  level INTEGER,
                  survivalTime REAL,
                  dodgeRatio REAL,
                  colorTargetScore REAL,
                  timestamp INTEGER
                )
                """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertRecord(int level, double survivalTime,
                                    double dodgeRatio, double colorTargetScore) {
        String sql = "INSERT INTO records(level,survivalTime,dodgeRatio,colorTargetScore,timestamp) VALUES(?,?,?,?,?)";
        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, level);
            p.setDouble(2, survivalTime);
            p.setDouble(3, dodgeRatio);
            p.setDouble(4, colorTargetScore);
            p.setLong(5, System.currentTimeMillis());
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<GameRecord> fetchAll() {
        List<GameRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM records";
        try (Connection c = DriverManager.getConnection(URL);
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new GameRecord(
                    rs.getInt("id"),
                    rs.getInt("level"),
                    rs.getDouble("survivalTime"),
                    rs.getDouble("dodgeRatio"),
                    rs.getDouble("colorTargetScore"),
                    rs.getLong("timestamp")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}