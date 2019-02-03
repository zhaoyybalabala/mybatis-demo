package com.demo.mybatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * com.demo.mybatis.jdbc
 *
 * @author Zyy
 * @date 2019/2/3 17:20
 * jdbc工具类
 */
public class DBUtil {
    private static String driver;//连接数据库的驱动
    private static String url;
    private static String username;
    private static String password;

    static {
        driver="com.mysql.jdbc.Driver";//需要的数据库驱动
        url="jdbc:mysql://192.168.5.104:3306/mybatis";//数据库名路径
        username="root";
        password="root";
    }

    public static Connection open()
    {
        try {
            Class.forName(driver);
            return (Connection) DriverManager.getConnection(url,username, password);
        } catch (Exception e) {
            System.out.println("数据库连接失败！");
        }
        return null;
    }

    /*
     * 关闭数据库
     */
    public static void close(Connection conn)
    {
        if(conn!=null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("数据库关闭失败！");
            }
        }
    }
}
