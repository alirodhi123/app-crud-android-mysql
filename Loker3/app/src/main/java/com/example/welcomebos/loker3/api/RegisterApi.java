package com.example.welcomebos.loker3.api;

import com.example.welcomebos.loker3.model.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Welcome Bos on 7/8/2017.
 */

public interface RegisterApi {

    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> daftar (@Field("nim") String nim,
                       @Field("nama") String nama,
                       @Field("jurusan") String jurusan,
                       @Field("jk") String jk);

    @GET("view.php")
    Call<Value> view();
}
