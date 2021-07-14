package com.example.stucher;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Patterns;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//fragement : piece if activity that can be placed inside activity
//Interaction with fragments is done through FragmentManager
public class PrincipalFragment extends Fragment implements View.OnClickListener {
    EditText tname, tuser, tmail, tpass, tcpass;
    TextView tbranch;
    Button submitid;
    String Tname, Tuser, Tmail, Tpass, Tcpass;

    public PrincipalFragment() {
        // Required empty public constructor
    }

    //creates and returns the view hierarchy associated with the fragment.
    //LayoutInflater : Instantiates a layout XML file into its corresponding View
    //ViewGroup : special view that can contain other views (called children.)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_principal, container, false);
        tname = (EditText) view.findViewById(R.id.tname);
        tuser = (EditText) view.findViewById(R.id.tuser);
        tmail = (EditText) view.findViewById(R.id.tmail);
        tpass = (EditText) view.findViewById(R.id.tpass);
        tcpass = (EditText) view.findViewById(R.id.tcpass);
       // tbranch = (TextView) view.findViewById(R.id.tbranch);
        submitid = (Button) view.findViewById(R.id.submitid);
        submitid.setOnClickListener(this);
        return view;
    }
    private  void teacherSignup() {
        Tname = tname.getText().toString().trim();
        Tmail = tmail.getText().toString().trim();
        Tuser = tuser.getText().toString().trim();
        Tpass = tpass.getText().toString().trim();
        Tcpass = tcpass.getText().toString().trim();
        //Tbranch = tbranch.getText().toString().trim();

        if (Tname.isEmpty() || Tmail.isEmpty() || Tuser.isEmpty() || Tpass.isEmpty() || Tcpass.isEmpty()) {
            Toast.makeText(getActivity(), "Please all the form fields!", Toast.LENGTH_LONG).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(Tmail).matches()) {
            Toast.makeText(getActivity(), "Enter a valid email-address!", Toast.LENGTH_LONG).show();
        }
        else if (Tpass.length() < 4) {
            Toast.makeText(getActivity(), "Password should be atleast 4 characters long", Toast.LENGTH_LONG).show();
        }
        else if (Tpass.length() > 10) {
            Toast.makeText(getActivity(), "Password shouldn't exceed 10 characters", Toast.LENGTH_LONG).show();
        }
        else {
            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .Principalreg(Tname, Tmail, Tuser, Tpass);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String s = response.body().string();
                        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            opentaccount();
        }
    }
    public void opentaccount() {
        //The getActivity() method gives the context of the Activity
        Intent intent = new Intent(getActivity(), PsDetails.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        teacherSignup();
        tname.getText().clear();
        tmail.getText().clear();
        tuser.getText().clear();
        tpass.getText().clear();
        tcpass.getText().clear();
    }
}