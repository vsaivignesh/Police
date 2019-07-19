package com.example.try2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONInterface {
    @GET("/api/forces")
    Call<List<Example>> getdata();
}
