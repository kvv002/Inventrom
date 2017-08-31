package com.example.kritika.inventrom_android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FormActivity extends AppCompatActivity {

    EditText name,age,address,gender;
    Button button;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        address=(EditText)findViewById(R.id.address);
        gender=(EditText)findViewById(R.id.gender);
        button=(Button)findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               WriteBtn(v);

            }
        });





    }


    // write text to file
    public void WriteBtn(View v) {
        // add-write text into file
        try {
            String filename=name.getText().toString()+".txt";
            FileOutputStream fileout=openFileOutput(filename, MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(name.getText().toString()+"\n");
            outputWriter.write(age.getText().toString()+"\n");
            outputWriter.write(address.getText().toString()+"\n");
            outputWriter.write(gender.getText().toString()+"\n");
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

         //  ReadBtn(v,filename);
            Intent i=new Intent(getApplicationContext(),ColorGraphActivity.class);
            startActivity(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Read text from file
  /* public void ReadBtn(View v,String filename) {
        //reading text from file
        try {

            FileInputStream fileIn=openFileInput(filename);
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            name.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
