package com.example.android.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    private EditText PasswordEditText;
    String savedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PasswordEditText = (EditText) findViewById(R.id.password_editText);

        Bundle extras = getIntent().getExtras();
        savedPassword = extras.getString("password");

    }


    public void submitPassword(View view) {
        String writtenPassword = PasswordEditText.getText().toString();

        if (writtenPassword != null) {


            if (savedPassword.equals(writtenPassword)) {
                finish();
            } else {
                Toast toast = new Toast(this);
                toast.makeText(this, "Invalid Password", Toast.LENGTH_LONG).show();
            }
        }
    }
}
