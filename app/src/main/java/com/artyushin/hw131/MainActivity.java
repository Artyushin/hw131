package com.artyushin.hw131;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private String fileLogin = "login.txt";
    private String filePassword = "password.txt";
    private String login = "";
    private String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        EditText etLogin = findViewById(R.id.login);
        EditText etPassword = findViewById(R.id.password);
        Button bLogin = findViewById(R.id.bLogin);
        Button bReg = findViewById(R.id.bReg);

        bLogin.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                login = etLogin.getText ().toString ();
                password = etPassword.getText ().toString ();
                String readLogin = readLogin();
                String readPassword = readPassword();

                if (!login.equals (readLogin)){
                    Toast.makeText(MainActivity.this, "Invalid login!", Toast.LENGTH_LONG).show();
                }
                else if (!password.equals (readPassword)){
                    Toast.makeText(MainActivity.this, "Invalid password!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,
                            "Successfully username and password match the data!", Toast.LENGTH_LONG).show();
                }
            }
        });

        bReg.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                login = etLogin.getText ().toString ();
                password = etPassword.getText ().toString ();
                saveLogin();
                savePassword();
                Toast.makeText(MainActivity.this, "Registered!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveLogin() {

        if (login.equals("")) {
            Toast.makeText(MainActivity.this, "No login entered!", Toast.LENGTH_LONG).show();
        } else {
            String fileLoginContents = login;
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput(fileLogin, MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace ( );
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
            try {
                bw.write(fileLoginContents);
            } catch (IOException e) {
                e.printStackTrace ( );
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace ( );
            }
        }
    }

    private void savePassword() {

        if (password.equals("")) {
            Toast.makeText(MainActivity.this, "No password entered!", Toast.LENGTH_LONG).show();
        } else {
            String filePasswordContents = password;
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = openFileOutput(filePassword, MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace ( );
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bwPassword = new BufferedWriter(outputStreamWriter);
            try {
                bwPassword.write(filePasswordContents);
            } catch (IOException e) {
                e.printStackTrace ( );
            }
            try {
                bwPassword.close();
            } catch (IOException e) {
                e.printStackTrace ( );
            }
        }
    }

    private String readPassword() {
        FileInputStream fileInputStream = null;
        String password = null;
        try {
            fileInputStream = openFileInput(filePassword);
        } catch (FileNotFoundException e) {
            e.printStackTrace ( );
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        try {
            password = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return password;
    }

    private String readLogin() {
        FileInputStream fileInputStream = null;
        String login = null;
        try {
            fileInputStream = openFileInput(fileLogin);
        } catch (FileNotFoundException e) {
            e.printStackTrace ( );
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        try {
            login = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return login;
    }
}