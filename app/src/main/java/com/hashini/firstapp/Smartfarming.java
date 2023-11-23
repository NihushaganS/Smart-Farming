package com.hashini.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Smartfarming extends AppCompatActivity {

    private Button login;
    private Button buttonsign;


    EditText regname, regpw ;
    Button relogin ; Button regSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smartfarming);

        regname = findViewById(R.id.regname);
        regpw = findViewById(R.id.regpw);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= regname.getText().toString();
                String password = regpw.getText().toString();

                //make function for validation and pass the parameters

                boolean check = validateinfo(name,password);

                if(check==true){
                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();

                    login = (Button) findViewById(R.id.login);
                    login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openhome();
                        }
                    });




                }
                else {
                    Toast.makeText(getApplicationContext(), "Sorry check information again", Toast.LENGTH_SHORT).show();
                }
                buttonsign= (Button) findViewById(R.id.buttonsign);
                buttonsign.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openSignup();
                    }
                });


            }
        });


    }

    private void openSignup() {
        Intent intent = new Intent( this, Signup.class );
        startActivity(intent);

    }


    private void openhome() {
        Intent intent = new Intent( this, Home.class );
        startActivity(intent);

    }

    private boolean validateinfo(String name, String password) {
        if(name.length()==0) {
            regname.requestFocus();
            regname.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(!name.matches("[avoid-zA-Z]+")){
            regname.requestFocus();
            regname.setError("ENTER ONLY ALPHABETICAL CHRACTER");
            return false;

        } else if (password.length()<=5) {
            regpw.requestFocus();
            regpw.setError("MINIMUM 6 CHARACTERS REQUIRED");
            return false;

        }else {
            return true;
        }
    }
}