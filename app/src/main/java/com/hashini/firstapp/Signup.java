package com.hashini.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    EditText regname, regemail, regmobile, regaddress, regpw ;
    Button regbtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        regname = findViewById(R.id.regname);
        regemail = findViewById(R.id.regemail);
        regmobile = findViewById(R.id.regmobile);
        regaddress = findViewById(R.id.regaddress);
        regpw = findViewById(R.id.regpw);
        regbtn = findViewById(R.id.regbtn);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= regname.getText().toString();
                String email = regemail.getText().toString();
                String mobile = regmobile.getText().toString();
                String address = regaddress.getText().toString();
                String password = regpw.getText().toString();

                //make function for validation and pass the parameters

                boolean check = validateinfo(name, email, mobile, address, password);

                if(check==true){
                    Toast.makeText(getApplicationContext(), "SignUp Successfully", Toast.LENGTH_SHORT).show();

                    regbtn= (Button) findViewById(R.id.regbtn);
                    regbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openSmartfarming();
                        }
                    });
                }
                
                else {
                    Toast.makeText(getApplicationContext(), "Sorry check information again", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void openSmartfarming() {
        Intent intent = new Intent( this, Smartfarming.class );
        startActivity(intent);

    }

    private boolean validateinfo(String name, String email, String mobile, String address, String password) {
        if(name.length()==0) {
            regname.requestFocus();
            regname.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if(!name.matches("[avoid-zA-Z]+")){
            regname.requestFocus();
            regname.setError("ENTER ONLY ALPHABETICAL CHRACTER");
            return false;

        }else if(email.length()==0){
            regemail.requestFocus();
            regemail.setError("FIELD CANNOT BE EMPTY");
            return false;

        } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            regemail.requestFocus();
            regemail.setError("ENTER VALID EMAIL");
            return false;
        } else if (mobile.length()==0) {
            regmobile.requestFocus();
            regmobile.setError("FIELD CANNOT BE EMPTY");
            return false;

        } else if (!mobile.matches("^[+][0-9]{10,13}$")) {
            regmobile.requestFocus();
            regmobile.setError("Correct Format : +92xxxxxxxxx");
            return false;

        } else if (address.length()==0) {
            regaddress.requestFocus();
            regaddress.setError("FIELD CANNOT BE EMPTY");
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