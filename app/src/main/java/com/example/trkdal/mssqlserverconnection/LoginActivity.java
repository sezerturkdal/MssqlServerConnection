package com.example.trkdal.mssqlserverconnection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    Button btn;
    EditText edtUser,edtPassword;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (Button) findViewById(R.id.btnLogin);
        edtUser=(EditText) findViewById(R.id.edtUserName);
        edtPassword=(EditText)findViewById(R.id.edtPassword);
    }
    public void run(View v)
    {
        CheckLogin checkLogin=new CheckLogin();
        checkLogin.execute("");
    }

    public  class CheckLogin extends AsyncTask<String,String,String>
    {
        String z="";
        Boolean IsSuccess=false;
        @Override
        protected String doInBackground(String... strings) {

            DbConnection connect=new DbConnection();
            String userName=edtUser.getText().toString();
            String password=edtPassword.getText().toString();
            if(userName.trim().equals("")||password.trim().equals(""))
            {
                z="Please enter username and password";
            }
            else
            {
                try
                {
                    conn=connect.connectionclass();
                    if(conn==null)
                    {
                        z="Please check internet connection";
                    }
                    else
                    {
                        String query="SELECT * FROM Admins WHERE UserName='"+userName.toString()+"' AND UserPassword='"+password.toString()+"';";
                        Statement stmt=conn.createStatement();
                        ResultSet rs=stmt.executeQuery(query);
                        if(rs.next())
                        {
                            z="Login Successful";
                            IsSuccess=true;
                            Intent i=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            z="Invalid Credientals";
                            IsSuccess=false;

                        }
                    }

                }
                catch (Exception ex)
                {
                    IsSuccess=false;
                    z=ex.getMessage();

                }
            }

            return null;
        }

    }
}