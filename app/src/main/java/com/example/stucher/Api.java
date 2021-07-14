package com.example.stucher;

//Retrofit is a REST Client for Java and Android.
//Retrofit uses the OkHttp library for HTTP requests.
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
//creating an interface for api calls(http request)
public interface Api {
    //API : Application Programming Interface. Used to establish comm between two applications.
    //Api helps to perform operations between two.
    //types : Rest Api & soap.
    //REST (REpresentational State Transfer) - nothing but a code in our server executed using http request.
    @FormUrlEncoded  //used more generally to send text data to the server to send a http request for create operation
    @POST("Principalreg.php")
    Call<ResponseBody> Principalreg (
            @Field("name") String name,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
            //@Field("branch") String branch
    );
    @FormUrlEncoded
    @POST("Staffreg.php") //endpoint(which differs)
    Call<ResponseBody> Staffreg (
            @Field("name") String name,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password,
            //@Field("mobile_no.") double mobile_no,
            @Field("staff") String staff,
            @Field("department") String department
    );
    @FormUrlEncoded
    @POST("Studentreg.php")
    Call<ResponseBody> Studentreg (
            @Field("name") String name,
            @Field("roll_no") int roll_no,
            @Field("prn") String prn,
            @Field("email") String email,
            @Field("branch") String branch,
            @Field("year") String year
    );
    @FormUrlEncoded
    @POST("Userlogin.php")
    Call<ResponseBody> Userlogin (
            @Field("username") String username,
            @Field("password") String password
    );
}
