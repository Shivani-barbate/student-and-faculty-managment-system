package com.example.stucher;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class StudentFragment extends Fragment implements View.OnClickListener {
    EditText sname, srno,sprn,smail;
    TextView sbranch,syr;
    Button submitid;
    String Sname, Sprn, Smail, Sbranch, Syr;
    int Srno;
    public StudentFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        sname = (EditText) view.findViewById(R.id.sname);
        srno = (EditText) view.findViewById(R.id.srno);
        sprn = (EditText) view.findViewById(R.id.sprn);
        smail = (EditText) view.findViewById(R.id.smail);
        sbranch = (TextView) view.findViewById(R.id.sbranch);
        syr = (TextView) view.findViewById(R.id.syr);
        submitid = (Button) view.findViewById(R.id.submitid);
        submitid.setOnClickListener(this);
        return view;
    }
    private  void studentSignup() {
        Sname = sname.getText().toString().trim();
        Srno = Integer.parseInt(srno.getText().toString());
        Sprn = sprn.getText().toString().trim();
        Smail = smail.getText().toString().trim();
        Sbranch = sbranch.getText().toString().trim();
        Syr = syr.getText().toString().trim();
        if (Sname.isEmpty() || Sprn.isEmpty() || Smail.isEmpty()) {
            Toast.makeText(getActivity(), "Please all the form fields!", Toast.LENGTH_LONG).show();
        } else if (Sprn.length() != 9) {
            Toast.makeText(getActivity(), "Please enter correct prn no", Toast.LENGTH_LONG).show();
        } else {
            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .Studentreg(Sname, Srno, Sprn, Smail, Sbranch, Syr);
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
        }
    }
    @Override
    public void onClick (View v){
        studentSignup();
        sname.getText().clear();
        srno.getText().clear();
        sprn.getText().clear();
        smail.getText().clear();
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }
}
