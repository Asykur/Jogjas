package com.example.jogjas.retrofit;

import com.example.jogjas.pojo.DataLocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {

    @GET("jsonBootcamp.php")
    Call<ApiResponse<List<DataLocation>>> getData();
}
