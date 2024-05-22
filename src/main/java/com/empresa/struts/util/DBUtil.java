package com.empresa.struts.util;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtil {

    private static DataSource dataSource;

    static {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/DS");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
}