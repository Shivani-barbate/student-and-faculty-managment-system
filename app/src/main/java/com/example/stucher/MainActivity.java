package com.example.stucher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//when you extend AppComapatActivity class, you create a new kind of Android activty.
// the word extend AppComapatActivity tells Java that a MainActivity is, in fact, an example of an Android AppCompatActivity.
public class MainActivity extends AppCompatActivity {


    private Button regButton;
    private Button logButton;

    //the word @Override indicates that the listing’s MainActivity doesn’t use the AppCompatActivity class’s prewritten onCreate method.
    // Instead, the MainActivity contains a declaration for its own onCreate method.
    @Override
    //When an Activity first call or launched then onCreate(Bundle savedInstanceState) method is responsible to create the activity.
    //When ever orientation(i.e. from horizontal to vertical or vertical to horizontal) of activity gets changed or when an Activity gets forcefully terminated by any
    // Operating System then savedInstanceState i.e. object of Bundle Class will save the state of an Activity.
    //After Orientation changed then onCreate(Bundle savedInstanceState) will call and recreate the activity and load all data from savedInstanceState.
    //Basically Bundle class is used to stored the data of activity whenever above condition occur in app.
    protected void onCreate(Bundle savedInstanceState) {
        //super keyword in Java is a reference variable which is used to refer immediate parent class object.
        super.onCreate(savedInstanceState);
        //displays the material described in layout.activity_main
        setContentView(R.layout.activity_main);
        //findViewById : Finds a view that was identified by the id attribute from the XML that was processed in OnCreate(Bundle).
        regButton = (Button) findViewById(R.id.regButton);
        //This method takes the reference to the Listener and registers a callback to be invoked when the View is clicked.
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
        logButton = (Button) findViewById(R.id.logButton);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }

    //opens Registration activity
    public void openRegister() {
        //Explicit intents explicitly define the component which should be called by the Android system
        Intent i = new Intent(this, Register.class);
        //invokes the acitivity
        startActivity(i);
    }

    //opens Login Activity
    public void openLogin() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}
