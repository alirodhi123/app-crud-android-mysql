package com.example.welcomebos.loker3;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.welcomebos.loker3.api.RegisterApi;
import com.example.welcomebos.loker3.model.Value;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String ROOT_URL = "http://192.168.43.100:8080/db_lokerandroid/crud/";

    private RadioButton radioJKButton;
    private ProgressDialog progress;
    String nim, nama, jurusan;


    @BindView(R.id.radioJK) RadioGroup radioGroup;
    @BindView(R.id.editTextNIM) EditText editTextNIM;
    @BindView(R.id.editTextNama) EditText editTextNama;
    @BindView(R.id.editTextJurusan) EditText editTextJurusan;

    //ketika klik button daftar
    @OnClick(R.id.buttonDaftar) void daftar(){

        //Untuk menampilkan loading
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading");
        progress.show();

        //Untuk Mendapatkan data yang diinputkan dari android
        nim = editTextNIM.getText().toString();
        nama = editTextNama.getText().toString();
        jurusan = editTextJurusan.getText().toString();

         int selectedId = radioGroup.getCheckedRadioButtonId();
         radioJKButton = (RadioButton) findViewById(selectedId);
         String jk = radioJKButton.getText().toString();

        //Syntax untuk Retrovit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterApi api = retrofit.create(RegisterApi.class);
        Call<Value> call = api.daftar(nim, nama, jurusan, jk);

        call.enqueue(new Callback<Value>() {
            @Override
            //Method untuk apabila server merespon maka akan memanggil funsgi ini
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue(); //untuk mengirim data ke database
                String message = response.body().getMessage();

                //Progress bar
                progress.dismiss();

                //Kondisi jika berhasil mengirim data
                if (value.equals("1")){
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }

                //Jika gagal mengirim data
                else {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            //Method untuk apabila server gagal merespon akan memanggil fungsi ini
            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(MainActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
