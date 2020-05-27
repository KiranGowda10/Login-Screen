                                                            //Name: Kiran Gowda
                                                            //ID: 018761559

package com.example.android.simplelogin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Objects;

import static com.example.android.simplelogin.HMap.hm;


public class Second_Class extends AppCompatActivity {


    private Button m_btnSignup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        m_btnSignup2 = (Button) findViewById(R.id.btnSignup2);
        m_btnSignup2.setOnClickListener(new View.OnClickListener() { //This method gets executed when the Sign Me Up! button is clicked in the Sign Up Screen;
            @Override
            public void onClick(View v) {

                EditText UserName2 = (EditText) findViewById(R.id.txtUserName2);    //casting the data fields to edit text;
                EditText Password2 = (EditText) findViewById(R.id.txtPassword2);
                EditText RePassword2 = (EditText) findViewById(R.id.txtRe_Password);
                EditText Email = (EditText) findViewById(R.id.txtEmail);
                EditText Phone = (EditText) findViewById(R.id.numPhone);

                String Uname2 = UserName2.getText().toString();             // converting the data fields to string;
                String Pass2 = Password2.getText().toString();
                String RePass2 = RePassword2.getText().toString();
                String Email2 = Email.getText().toString();
                String Phone2 = Phone.getText().toString();



                if (Uname2.isEmpty())  //checks if the user name field is empty, if true, a toast message is passed;
                {

                    Toast.makeText(getApplicationContext(), "User Name field must be filled", Toast.LENGTH_LONG).show();
//                    Intent intent1 = new Intent(getApplicationContext(), Second_Class.class);
//                    startActivity(intent1);
                }

                else if(Pass2.isEmpty())  //checks if the entered password field is empty or not, based on hashmap key-value pair;
                {
                    Toast.makeText(getApplicationContext(), "Password field must be filled", Toast.LENGTH_LONG).show();
//                   Intent intent1 = new Intent(getApplicationContext(), Second_Class.class);
//                   startActivity(intent1);
                }

                else if(RePass2.isEmpty())     // checks if the retype-password is empty, if true, an error message is passed;
                {
                    Toast.makeText(getApplicationContext(), "Retype-Password must be filled", Toast.LENGTH_LONG).show();
//                    Intent intent1 = new Intent(getApplicationContext(), Second_Class.class);
//                    startActivity(intent1);
                }

                else if(!Pass2.equals(RePass2))  // checks if the retype-password and user password data matches, if false, a toast message is passed;
                {
                    Toast.makeText(getApplicationContext(), "Password doesn't match with Retype-Password", Toast.LENGTH_LONG).show();
//                    Intent intent1 = new Intent(getApplicationContext(), Second_Class.class);
//                    startActivity(intent1);
                }

                else if (Pass2.length() < 8) {   //checks if the entered password in the sign up window is more than 8 characters, if false, a toast message is passed;
                    Toast.makeText(getApplicationContext(), "Password minimum 8 characters", Toast.LENGTH_LONG).show();
//                    Intent intent1 = new Intent(getApplicationContext(), Second_Class.class);
//                    startActivity(intent1);
                }

                else if(Email2.isEmpty())  //checks if email id feild is empty, if true, a toast message is passed;
                {
                    Email.setError("Email field must be filled");
//                    Intent intent1 = new Intent(getApplicationContext(), Second_Class.class);
//                    startActivity(intent1);

                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(Email2).matches()) //checks if the email id is in the correct format or not, if not toast message is passed;;
                {
                    Toast.makeText(getApplicationContext(), "Invalid Email Id format", Toast.LENGTH_LONG).show();
//                    Intent intent1 = new Intent(getApplicationContext(), Second_Class.class);
//                    startActivity(intent1);

                }

                else if(Phone2.isEmpty()) //checks if the phone number feild is empty or not;
                {
                    Toast.makeText(getApplicationContext(), "Phone number field must be filled", Toast.LENGTH_LONG).show();
//                    Intent intent1 = new Intent(getApplicationContext(), Second_Class.class);
//                    startActivity(intent1);
                }
                else if(Phone2.length()!=10)   //checks if the phone number has 10 characters, else, a toast message is passed;
                {
                    Toast.makeText(getApplicationContext(), "Invalid Phone number format", Toast.LENGTH_LONG).show();
//                    Intent intent1 = new Intent(getApplicationContext(), Second_Class.class);
//                    startActivity(intent1);
                }

                else if(HMap.hm.containsKey(Uname2)){
                    Toast.makeText(getApplicationContext(), "User Name already exists", Toast.LENGTH_LONG).show();
//                    Intent intent1 = new Intent(getApplicationContext(), Second_Class.class);
//                    startActivity(intent1);
                }
                else                               //if the given username is not in the hashmap,
                {                                 // then we add that user name and corresponding password to the hashmap;
                    hm.put(Uname2, Pass2);
                    Toast.makeText(getApplicationContext(),"Sign Up Successful!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Second_Class.this,MainActivity.class);
                    startActivity(intent);
                }

            }

         });
        }

    }



