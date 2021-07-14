package com.example.stucher;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Login extends AppCompatActivity {
    EditText usertext,passtext;
    Button lbutton;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usertext = (EditText) findViewById(R.id.usertext);
        passtext = (EditText) findViewById(R.id.passtext);
        lbutton = (Button) findViewById(R.id.lbutton);
        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccount();
                usertext.getText().clear();
                passtext.getText().clear();
            }
        });
    }
    public void openAccount() {
        //trim() : Remove whitespace from both sides of a string:
        //toString() : converts it to String format
        //getText() : used to get text from text field
        username = usertext.getText().toString().trim();
        password = passtext.getText().toString().trim();
        if(username.isEmpty() || password.isEmpty())  {
            //to display message
            Toast.makeText(Login.this, "Enter username and password", Toast.LENGTH_LONG).show();
        }
        else {
            //Call class is the starting point for every network request with Retrofit.
            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .Userlogin(username,password);
            //enqueue() : expects a typed implementation of Retrofit’s Callback class,
            // which expects the implementation of two methods (onResponse and onFailure)
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String s = response.body().string();
                        Toast.makeText(Login.this, s, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            opendialog();
        }
    }
    public void opentaccount()  {
        Intent intent = new Intent(this, TsDetails.class);
        startActivity(intent);
    }
    public void openpaccount()  {
        Intent intent = new Intent(this, PsDetails.class);
        startActivity(intent);
    }
    public void opendialog() {
        //AlertDialog pops up a dialog box by remaining in the same activity ,without changing the activity
        //AlertDialog.Builder is an inner class of AlertDialog
        //context : uses the default alert dialog theme.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you HOD?") //Set the message to display.
                //Set a listener to be invoked when the positive button of the dialog is pressed.
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        openVerify();
                    }
                }) //Set a listener to be invoked when the negative button of the dialog is pressed.
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        opentaccount();
                    }
                });
        builder.show(); //Creates an AlertDialog with the arguments supplied to this builder and immediately displays the dialog.
    }

    public void openVerify() {

        final EditText input = new EditText(Login.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT );
        input.setLayoutParams(lp);
        final String pass = "123456";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Authentication password")
                .setView(input)  //Set an EditText view to get user input
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        password = input.getText().toString();
                        if(password.equals(pass)) {
                                Toast.makeText(Login.this,"password matched",Toast.LENGTH_LONG).show();
                                openpaccount();
                            }
                            else {
                                Toast.makeText(Login.this,"password mismatched",Toast.LENGTH_LONG).show();
                            }
                        }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.show();
    }
}

