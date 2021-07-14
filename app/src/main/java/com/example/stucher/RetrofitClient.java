package com.example.stucher;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.43.2/sti/";
    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;
    private RetrofitClient()  {
        retrofit = new Retrofit.Builder()  //Build a new Retrofit.
                .baseUrl(BASE_URL) //Set the base URL of API.
                .addConverterFactory(GsonConverterFactory.create()) //Add converter factory for serialization and deserialization of objects.
                .build(); //Create the Retrofit instance using the configured values.
    }
    //Synchronization prevents a block of code to be executed by more than one thread at the same time.
    public static synchronized RetrofitClient getInstance() { //Only one thread can own a monitor at a given time.
        if(retrofitClient == null)  {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }
    public  Api getApi()  {

        //When api instance is created using Retrofit.Builder.create()  the instance returned from retrofit is actually a proxy class, a dynamic implementation of interface at runtime.
        return  retrofit.create(Api.class);
    }
}

