package com.example.leaguechamps;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeagueChampsCRUD {

    //CRUD - CREATE
    public void create(LeagueChamps leagueChamps) throws SQLException {
        String sql = "INSERT INTO leaguechamps (campeao, maestria, cidade) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, leagueChamps.getCampeao());
            stmt.setInt(2, leagueChamps.getMaestria());
            stmt.setString(3, leagueChamps.getCidade());
            stmt.executeUpdate();
        }
    }
    // CRUD - READ
    public List<LeagueChamps> read() throws SQLException {
        String sql = "SELECT * FROM leaguechamps";
        List<LeagueChamps> champsList = new ArrayList<>();
        try(Connection conn = ConexaoBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                String campeao = rs.getString("campeao");
                int maestria = rs.getInt("maestria");
                String cidade = rs.getString("cidade");
                champsList.add(new LeagueChamps(campeao, maestria, cidade));
            }
        }
            return champsList;
    }

    //CRUD - UPDATE
    public void update(String oldCampeao, LeagueChamps leagueChamps) throws SQLException {
        String sql = "UPDATE leaguechamps SET campeao = ?, maestria = ?, cidade = ? WHERE campeao = ?";
        try (Connection conn = ConexaoBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, leagueChamps.getCampeao());
            stmt.setInt(2, leagueChamps.getMaestria());
            stmt.setString(3, leagueChamps.getCidade());
            stmt.setString(4, oldCampeao);
            stmt.executeUpdate();
        }
    }

    //CRUD - DELETE
    public void delete(String campeao, int maestria, String cidade) throws SQLException {
        String sql = "DELETE FROM leaguechamps WHERE campeao = ? AND maestria = ? AND cidade = ?";
        try (Connection conn = ConexaoBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, campeao);
            stmt.setInt(2, maestria);
            stmt.setString(3, cidade);
            stmt.executeUpdate();
        }
    }
}





