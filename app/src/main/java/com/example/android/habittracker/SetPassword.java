package com.example.android.habittracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SetPassword extends AppCompatActivity {

    private EditText setPasswordEditText;
    private EditText confirmPasswordEditText;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        setPasswordEditText = (EditText) findViewById(R.id.set_password_edit_text);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirm_password_edit_text);

        pref = getApplicationContext().getSharedPreferences("Account", MODE_PRIVATE);


        if (pref.contains("password")) {
            finish();
        }


    }

    public void setPassword(View view) {

        String enteredPassword = setPasswordEditText.getText().toString();
        String confirmedPassword = confirmPasswordEditText.getText().toString();
        if (enteredPassword.equals(confirmedPassword)) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("password", enteredPassword);
            editor.apply();
            Toast toast = new Toast(this);
            toast.makeText(this, "Password set",
                    Toast.LENGTH_LONG).show();

        } else {
            Toast toast = new Toast(this);
            toast.makeText(this, "Error matching passwords! Please re-enter the password",
                    Toast.LENGTH_LONG).show();
            setPasswordEditText.getText().clear();
            confirmPasswordEditText.getText().clear();
        }
    }
}
//    SharedPreferences.Editor editor = pref.edit();
//        editor.putString("password", "pass");
//                editor.apply();
