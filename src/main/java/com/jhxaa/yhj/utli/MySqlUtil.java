package com.jhxaa.yhj.utli;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

public class MySqlUtil {


    public static Connection conn = null;
    //https://mp.weixin.qq.com/s/sZT4RoY170nGr17nQmJkCA
    static String url = PropertiesUtil.getString("mysql.config.url");
    static String username = PropertiesUtil.getString("mysql.config.user");
    static String password = PropertiesUtil.getString("mysql.config.pwd");

    //2.获取与数据库的链接
    public synchronized static Connection getConnection() {
        try {
            if (conn == null) {
                Class.forName(PropertiesUtil.getString("mysql.config.className")); //返回一个类
                conn = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }


    //提交事务
    public static synchronized void commit(Connection conn) {
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //回滚事务
    public static synchronized void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //开始事务
    public static synchronized void beginTx(Connection conn) {
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static synchronized void closeConnection(PreparedStatement preStmt, Connection conn, ResultSet resultSet) {
        if (preStmt != null) {
            try {
                preStmt.close();//关闭链接
            } catch (SQLException e) {
                System.out.println("PreparedStatement close error");
            }
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();//关闭连接避免占用过多资源
            } catch (SQLException e) {
                System.out.println("PreparedStatement close error");
                e.printStackTrace();
            }
        }
    }


    /**
     * 执行SQL
     *
     * @param sqlStr
     * @param objects
     * @return
     */
    public static synchronized Integer executorSql(String sqlStr, Object... objects) {
        int executeUpdate = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            for (int i = 0; i < objects.length; i++) {
                preparedStatement.setObject(i + 1, objects[i]);
            }
            executeUpdate = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(preparedStatement, connection, null);
        }
        return executeUpdate;
    }


    /**
     * 批量执行
     *
     * @param sqlStr
     * @param objects
     * @return
     */
    public static synchronized int[] batchExecutorSql(String sqlStr, List<Object[]> objects) {
        int executeUpdate[] = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            for (int i = 0; i < objects.size(); i++) {
                Object[] objects1 = objects.get(i);
                for (int i1 = 0; i1 < objects1.length; i1++) {
                    preparedStatement.setObject(i1 + 1, objects1[i1]);
                }
                preparedStatement.addBatch();
            }
            executeUpdate = preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(preparedStatement, connection, null);
        }
        return executeUpdate;
    }


    public static synchronized <T> String entityConvertInsertSqlString(String sqlName, Class<T> classObject) {

        StringBuilder stringBuilder = new StringBuilder(String.format("insert into %s%s", sqlName, " "));
        stringBuilder.append("(");
        Field[] fields = classObject.getDeclaredFields();
        System.out.println(fields.length);
        int length = fields.length;
        int tempNum = length - 1;
        for (int i = 0; i < length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            stringBuilder.append(field.getName());
            if (!(tempNum == i)) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("\n");
        stringBuilder.append(") values (");
        for (int i = 0; i < length; i++) {
            stringBuilder.append("?");
            if (!(tempNum == i)) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String sql = PropertiesUtil.getString("mysql.insert.sqlString");
    }
}
