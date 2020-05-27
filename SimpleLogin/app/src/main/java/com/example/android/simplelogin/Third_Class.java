                                                            //Name: Kiran Gowda
                                                            //ID: 018761559

package com.example.android.simplelogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Third_Class extends AppCompatActivity {
    private TextView m_txtmessage;
    private Button m_btnreturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        m_txtmessage = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        String message = intent.getStringExtra("FirstMessage"); //inorder to send this intent to other activity,
                                                                     // and fetch the data, getStringExtra method is used;
        m_txtmessage.setText(message); //This message is set to display;

        m_btnreturn = (Button) findViewById(R.id.button2);
        m_btnreturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {   //This method gets executed when the Sign Up button is clicked in the Login Screen;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class); //Used to launch the activity;
                startActivity(intent);
            }
        });

    }
}

