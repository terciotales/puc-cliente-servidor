package com.corpevents.main.dao;

import com.corpevents.main.connection.DBConnection;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RelatoriosDAO extends DBConnection {
    private PreparedStatement preparedStatement = null;

    public InputStream getReport(String reportName) {
        InputStream inputStream = null;
        try {
            String sql = "SELECT report FROM relatorios WHERE name = ?";
            this.preparedStatement = this.getConnection().prepareStatement(sql);
            this.preparedStatement.setString(1, reportName);
            ResultSet resultSet = this.preparedStatement.executeQuery();

            while (resultSet.next()) {
                inputStream = resultSet.getBinaryStream("report");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return inputStream;
    }
}
