package com.example.stucher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PsDetails extends AppCompatActivity {
    private Button student, staff;
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps_details);
        student = (Button) findViewById(R.id.student);
        staff = (Button) findViewById(R.id.staff);
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

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStaffDetails();
            }
        });
    }

    public void goBack() {
        Intent i = new Intent(PsDetails.this, MainActivity.class);
        startActivity(i);
    }

    public void openStudDetails() {
        String url = "http://192.168.43.2:80/sti/Displaystud.php";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    public void openStaffDetails()
    {
        String url = "http://192.168.43.2:80/sti/Displaystaff.php";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
