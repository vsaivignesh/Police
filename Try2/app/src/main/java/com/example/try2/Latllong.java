package com.example.try2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Latllong {
    @GET("/api/crimes-at-location")

Call<List<Crime>> getCrimes(@Query("date") String date, @Query("lat") String lat, @Query("lng") String lng);
}
