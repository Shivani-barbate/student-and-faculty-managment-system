package com.example.stucher;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * A simple {@link Fragment} subclass.
 */
public class StaffFragment extends Fragment implements View.OnClickListener{
//    Spinner gbranch;
    //String[] staff = {"HOD","Prof.","Assistance Prof.","Lab Assistant","Librarian","Office Staff","Peon"};
//    String[] department = {"Comp","IT","E&TC","Library","Office"};
    EditText gname, guser, gmail, gpass, gcpass,gsem,gbranch;
    //TextView gbranch;
    Button submitid;
    String Gname, Guser, Gmail, Gpass, Gcpass;
    String Gsem, Gbranch;
    String mText= "";
    AlertDialog.Builder builder;

    public StaffFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_staff, container, false);
        gname = (EditText) view.findViewById(R.id.gname);
        guser = (EditText) view.findViewById(R.id.guser);
        gmail = (EditText) view.findViewById(R.id.gmail);
        gpass = (EditText) view.findViewById(R.id.gpass);
        gcpass = (EditText) view.findViewById(R.id.gcpass);
        gbranch = (EditText) view.findViewById(R.id.gbranch);
        gsem = (EditText) view.findViewById(R.id.gsem);


        submitid = (Button) view.findViewById(R.id.submitid);
        submitid.setOnClickListener(this);

////        ArrayAdapter<CharSequence>mSortAdapter = new ArrayAdapter<CharSequence>(getActivity(),android.R.layout.simple_spinner_item, staff);
//////        mSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//////        gsem.setAdapter(mSortAdapter);
//        ArrayAdapter<CharSequence>mSortAdapter1 = new ArrayAdapter<CharSequence>(getActivity(),android.R.layout.simple_spinner_item, department );
//        mSortAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        gbranch.setAdapter(mSortAdapter1);
        return view;
    }
    private  void gfmSignup() {
        Gname = gname.getText().toString().trim();
        Gmail = gmail.getText().toString().trim();
        Guser = guser.getText().toString().trim();
        Gpass = gpass.getText().toString().trim();
        Gcpass = gcpass.getText().toString().trim();
        Gbranch = gbranch.getText().toString().trim();
        Gsem = gsem.getText().toString().trim();
        if (Gname.isEmpty() || Gmail.isEmpty() || Guser.isEmpty() || Gpass.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill all the form fields!", Toast.LENGTH_LONG).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(Gmail).matches()) {
            Toast.makeText(getActivity(), "Enter a valid email-address!", Toast.LENGTH_LONG).show();
        }
        else if (Gpass.length() < 4) {
            Toast.makeText(getActivity(), "Password should be atleast 4 characters long", Toast.LENGTH_LONG).show();
        }
        else if (Gpass.length() > 10) {
            Toast.makeText(getActivity(), "Password shouldn't exceed 10 characters", Toast.LENGTH_LONG).show();
        }
        else {
            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .Staffreg(Gname, Gmail, Guser, Gpass,Gsem, Gbranch);
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
            opengaccount();
        }
    }
    public void opengaccount() {
        Intent intent = new Intent(getActivity(), TsDetails.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        gfmSignup();
        gname.getText().clear();
        gmail.getText().clear();
        guser.getText().clear();
        gpass.getText().clear();
        gcpass.getText().clear();
        gsem.getText().clear();
        gbranch.getText().clear();
    }
}
