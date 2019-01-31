package com.example.jogjas.retrofit;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("result")
    public Boolean result;

    public boolean isSuccessfull(){
        return result == true;
    }
}
