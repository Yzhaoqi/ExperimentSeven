package com.yzq.android.experimentseven;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    private TextInputLayout text_new_password, text_confirm_password, text_password;
    private EditText edit_new_password, edit_confirm_password, edit_password;
    private Button ok, clear;
    private SharedPreferences sharedPreferences;
    private boolean is_password_store;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        text_new_password = (TextInputLayout)findViewById(R.id.text_input_new_password);
        text_confirm_password = (TextInputLayout)findViewById(R.id.text_input_confirm_password);
        text_password = (TextInputLayout)findViewById(R.id.text_input_password);
        edit_new_password = (EditText)findViewById(R.id.edit_new_password);
        edit_confirm_password = (EditText)findViewById(R.id.edit_confirm_password);
        edit_password = (EditText)findViewById(R.id.edit_password);
        ok = (Button)findViewById(R.id.pass_ok);
        clear = (Button)findViewById(R.id.pass_clear);

        sharedPreferences = getSharedPreferences("password", MODE_PRIVATE);
        password = sharedPreferences.getString("KEY_PASSWORD", null);
        if (password == null) {
            text_password.setVisibility(View.GONE);
            is_password_store = false;
        } else {
            text_new_password.setVisibility(View.GONE);
            text_confirm_password.setVisibility(View.GONE);
            is_password_store = true;
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check(is_password_store)) {
                    if (!is_password_store) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("KEY_PASSWORD", edit_new_password.getText().toString());
                        editor.commit();
                    }
                    Intent intent = new Intent(SignInActivity.this, TextActivity.class);
                    startActivity(intent);
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_password_store) {
                    edit_password.setText("");
                } else {
                    edit_new_password.setText("");
                    edit_confirm_password.setText("");
                }
            }
        });
    }

    private boolean check(boolean b) {
        if (!b) {
            if (edit_new_password.getText().toString().equals("")) {
                Toast.makeText(SignInActivity.this, "Password cannot be empty.", Toast.LENGTH_SHORT).show();
            } else if (!edit_new_password.getText().toString().equals(edit_confirm_password.getText().toString())) {
                Toast.makeText(SignInActivity.this, "Password Mismatch.", Toast.LENGTH_SHORT).show();
            } else {
                return true;
            }
        } else {
            if (!edit_password.getText().toString().equals(password)) {
                Toast.makeText(SignInActivity.this, "Invalid Password.", Toast.LENGTH_SHORT).show();
            } else {
                return true;
            }
        }
        return false;
    }
}
