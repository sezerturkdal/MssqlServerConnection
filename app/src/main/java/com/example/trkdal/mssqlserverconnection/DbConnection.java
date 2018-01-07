package com.example.trkdal.mssqlserverconnection;

import android.annotation.SuppressLint;
import android.database.SQLException;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Türkdal on 7.01.2018.
 */

public class DbConnection {
    @SuppressLint("NewApi")
    public Connection connectionclass()
    {
        String user="userName";     //Enter your database informations.
        String database="databaseName";
        String password="password!";
        String server="ip";

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        String ConnectionURL=null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL="jdbc:jtds:sqlserver://"+server+";"+"DatabaseName="+database+";user="+user+";password="+password+";"+"integratedSecurity=true;";
            connection= DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1:",se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2:",e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3:",e.getMessage());
        }
        return connection;

    }
}
