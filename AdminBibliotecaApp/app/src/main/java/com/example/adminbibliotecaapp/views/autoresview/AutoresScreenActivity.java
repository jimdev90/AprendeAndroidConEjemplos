package com.example.adminbibliotecaapp.views.autoresview;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adminbibliotecaapp.R;
import com.example.adminbibliotecaapp.databinding.ActivityAutoresScreenBinding;
import com.example.adminbibliotecaapp.response.AutoresResponse;
import com.example.adminbibliotecaapp.response.DataAutor;
import com.example.adminbibliotecaapp.retrofit.RetrofitClient;
import com.example.adminbibliotecaapp.utils.Utils;
import com.example.adminbibliotecaapp.webservices.WebService;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AutoresScreenActivity extends AppCompatActivity implements AdaptadorAutor.OnItemCliked {

    private ActivityAutoresScreenBinding binding;
    AdaptadorAutor adaptadorAutor;
    List<DataAutor> listaAutores = new ArrayList<>();

    Retrofit retrofit = new RetrofitClient().getRetrofit();
    WebService webService = retrofit.create(WebService.class);
    Utils utils = new Utils();
    DataAutor autor = new DataAutor();
    Boolean isEditando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAutoresScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        obtenerAutores();

        binding.ibtnAutorAdd.setOnClickListener( view -> {
            alertDialogAddUpdate();
        });
    }

    private void setupRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvAutores.setLayoutManager(layoutManager);

        adaptadorAutor = new AdaptadorAutor(this, listaAutores, this);
        binding.rvAutores.setAdapter(adaptadorAutor);
    }

    private void alertDialogAddUpdate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View vista = layoutInflater.inflate(R.layout.alert_dialog_add_update_autor, null);

        TextView tvTituloAlert = vista.findViewById(R.id.tvTituloAlert);
        EditText etIdAutor = vista.findViewById(R.id.etIdAutor);
        EditText etNomAutor = vista.findViewById(R.id.etNomAutor);

        if (isEditando){
            tvTituloAlert.setText("ACTUALIZAR AUTOR");
            etIdAutor.setText(autor.getIdAutor());
            etIdAutor.setEnabled(false);
            etNomAutor.setText(autor.getNomAutor());
        }

        builder.setView(vista);
        builder.create();

        builder.setPositiveButton("Aceptar", (dialog, which) -> {

            boolean isValidate = utils.validarCamposAddUpdateAutor(
                    etIdAutor.getText().toString().trim(),
                    etNomAutor.getText().toString().trim()
            );

            if (isValidate){

                autor.setIdAutor(etIdAutor.getText().toString().trim());
                autor.setNomAutor(etNomAutor.getText().toString().trim());

                if (isEditando){
                    actualizarAutor(autor);

                }else {
                    agregarAutor(autor);

                }

                isEditando = false;
                obtenerAutores();
                autor.resetData();


            }else {
                Toasty.error(AutoresScreenActivity.this, "Se deben llenar los campos obligatorios", Toasty.LENGTH_SHORT, true).show();
            }

        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            isEditando = false;
            autor.resetData();
        });

        builder.setCancelable(false);
        builder.show();

    }

    private void obtenerAutores(){
        Call<AutoresResponse> call = webService.obtenerAutores();
        call.enqueue(new Callback<AutoresResponse>() {
            @Override
            public void onResponse(Call<AutoresResponse> call, Response<AutoresResponse> response) {
                if (response.body().getCode().equals("200")){
                    Log.d(TAG, "onResponse: " + response.body().getData());
                    listaAutores = response.body().getData();
                    setupRecyclerView();
                }else{
                    Toasty.error(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<AutoresResponse> call, Throwable t) {

            }
        });
    }

    private void agregarAutor(DataAutor autor){
        Call<AutoresResponse> call = webService.agregarAutor(autor);
        call.enqueue(new Callback<AutoresResponse>() {
            @Override
            public void onResponse(Call<AutoresResponse> call, Response<AutoresResponse> response) {
                if (response.body().getCode().equals("200")){
                    Toasty.success(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }else {
                    Toasty.error(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<AutoresResponse> call, Throwable t) {

            }
        });
    }

    private void actualizarAutor(DataAutor autor){
        Call<AutoresResponse> call = webService.actualizarAutor(autor);
        call.enqueue(new Callback<AutoresResponse>() {
            @Override
            public void onResponse(Call<AutoresResponse> call, Response<AutoresResponse> response) {
                if (response.body().getCode().equals("200")){
                    Toasty.success(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }else {
                    Toasty.error(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<AutoresResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void editarAutor(DataAutor autor) {
        isEditando =true;
        this.autor = autor;
        alertDialogAddUpdate();
    }

    @Override
    public void borrarAutor(DataAutor autor) {
        Call<AutoresResponse> call = webService.borrarAutor(autor);
        call.enqueue(new Callback<AutoresResponse>() {
            @Override
            public void onResponse(Call<AutoresResponse> call, Response<AutoresResponse> response) {
                if (response.body().getCode().equals("200")){
                    Toasty.success(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                    obtenerAutores();
                }else {
                    Toasty.error(AutoresScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<AutoresResponse> call, Throwable t) {

            }
        });
    }
}