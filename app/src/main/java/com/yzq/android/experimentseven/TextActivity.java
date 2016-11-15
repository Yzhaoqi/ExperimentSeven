package com.yzq.android.experimentseven;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TextActivity extends AppCompatActivity {

    private EditText input_text;
    private Button save, load, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        input_text = (EditText)findViewById(R.id.input_text);
        save = (Button)findViewById(R.id.text_save);
        load = (Button)findViewById(R.id.text_load);
        clear = (Button)findViewById(R.id.text_clear);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    OutputStream os = openFileOutput("text_input", MODE_PRIVATE);
                    byte[] data = input_text.getText().toString().getBytes();
                    os.write(data);
                    os.close();
                    Toast.makeText(TextActivity.this, "Save Successfully.", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputStream is = openFileInput("text_input");
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    String str = new String(data, "UTF-8");
                    input_text.setText(str);
                    is.close();
                    Toast.makeText(TextActivity.this, "Load Successfully.", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(TextActivity.this, "Fail to read file.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_text.setText("");
            }
        });
    }
}
