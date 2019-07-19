package com.example.try2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DetailsInterface {
    @GET("/api/forces/{id}")
    Call<getDetails>callDetails(
            @Path("id") String id
    );
}
