package com.example.ex2;

import java.util.List;

import kotlin.ParameterName;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("user/register")
    Call<List<User>> getPosts(@Body User user);

    @GET("user/login")
    Call<Token> getLogin(@Query("email") String email, @Query("password") String password);
}
