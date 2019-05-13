package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AnekdotAPI {

    @GET("/api/get")
    Call<List<AnekdotModel>> getData(@Query("name") String resourceName, @Query("num") int count);

    // http://umorili.herokuapp.com/api/get?site=anekdot.ru&num=10&name=new%20anekdot
}
