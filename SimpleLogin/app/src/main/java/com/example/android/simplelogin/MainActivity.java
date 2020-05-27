                                                //Name: Kiran Gowda
                                                //ID: 018761559

package com.example.android.simplelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.android.simplelogin.HMap.hm;

public class MainActivity extends AppCompatActivity {
    Second_Class obj1 = new Second_Class();
    private Button m_btnSignup;
    private Button m_btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   //initialises an activity;
        setContentView(R.layout.activity_main); //creates an user interface window;

        m_btnSignup = (Button) findViewById(R.id.btnSignUp);   //casting the widgets to button type;
        m_btnLogin = (Button) findViewById(R.id.btnLogin);
        m_btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This method gets executed when the Sign Up button is clicked in the Login Screen;
                Intent intent = new Intent(getApplicationContext(), Second_Class.class); //Used to launch the activity;
                startActivity(intent);
            }
        });

        m_btnLogin.setOnClickListener(new View.OnClickListener() {  //This method gets executed when the Login button is clicked in the Login Screen;
            @Override
            public void onClick(View v) {

                EditText a = (EditText)findViewById(R.id.txtUserName);
                String str = a.getText().toString();               //converting user name data to string;
                EditText b = (EditText)findViewById(R.id.txtPassword);
                String pass = b.getText().toString();             //converting user password data to string;



                if(!HMap.hm.containsKey(str)){    //Here we check whether the entered user name
                                                  // in the login screen matches the user name in the hashmap;
                    Toast.makeText(getApplicationContext(),"Username doesn't exist",Toast.LENGTH_LONG).show();
                }

                else if(HMap.hm.get(str).equals(pass))   //Here we check whether the entered password
                {                                        // in the login screen matches the password in the hashmap based on key-value pair;
                    Intent intent = new Intent(getApplicationContext(), Third_Class.class);
                    Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_LONG).show();
                    intent.putExtra("FirstMessage", "Welcome "+ str + "!"); //If Login is successful, a welcome screen is displayed;
                    startActivity(intent);
                }

                else             // if neither user name nor password matches the data, a toast message is popped;
                {
                    Toast pass1 = Toast.makeText(MainActivity.this, "Credentials don't match!", Toast.LENGTH_LONG);
                    pass1.show();

                }

            }
        });

    }
}
