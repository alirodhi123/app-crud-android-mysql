package com.example.welcomebos.loker3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.welcomebos.loker3.adapter.RecyclerViewAdapter;
import com.example.welcomebos.loker3.api.RegisterApi;
import com.example.welcomebos.loker3.model.Mahasiswa;
import com.example.welcomebos.loker3.model.Value;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewActivity extends AppCompatActivity {

    public static final String ROOT_URL = "http://192.168.43.100:8080/db_lokerandroid/crud/";

    private List<Mahasiswa> mahasiswa = new ArrayList<>();
    private RecyclerViewAdapter viewAdapter;

    @BindView(R.id.recyclerView) RecyclerView recycleView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        ButterKnife.bind(this);

        viewAdapter = new RecyclerViewAdapter(this, mahasiswa);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(viewAdapter);


    }

    private void loadDataMahasiswa(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterApi api = retrofit.create(RegisterApi.class);
        Call<Value> call = api.view();

        call.enqueue(new Callback<Value>() {
            @Override
            //Method untuk apabila server merespon maka akan memanggil funsgi ini
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue(); //untuk mengirim data ke database

                //Progress bar
                progressBar.setVisibility(View.GONE);

                //Kondisi jika berhasil mamnggil data
                if (value.equals("1")){
                    mahasiswa = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter(ViewActivity.this, mahasiswa);
                    recycleView.setAdapter(viewAdapter);
                }

            }

            //Method untuk apabila server gagal merespon akan memanggil fungsi ini
            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }





}

