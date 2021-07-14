package com.example.stucher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TsDetails extends AppCompatActivity {
    private Button student;
    private TextView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ts_details);

        student = (Button) findViewById(R.id.student);
        logout = (TextView) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStudDetails();
            }
        });

    }
    public void goBack()
    {
        Intent i = new Intent(TsDetails.this,MainActivity.class);
        startActivity(i);
    }
    public void openStudDetails()
    {
        String url = "http://192.168.43.2:80/sti/Displaystud.php";
        //allow application components to request functionality from other Android components
        //Intents allow you to interact with components from the same applications as well as with components of other applications
        //Implicit intents specify the action which should be performed
        // If an implicit intent is sent to the Android system, it searches for all components which are registered for the specific action and the fitting data type
        //implicit intent declare a action to perform.The action specifies the thing you want to do, such as view, edit, send, or get something
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //Uniform Resource Identifier (URI) is a string of characters used to identify a resource.
        //A URI identifies a resource either by location, or a name, or both.
        // Such identification enables interaction with representations of the resource over a network
        //The method Uri.parse creates a new Uri object from a properly formated String
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
