package com.example.adminbibliotecaapp.views.loginscreenview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.adminbibliotecaapp.R;
import com.example.adminbibliotecaapp.databinding.ActivityLoginScreenBinding;
import com.example.adminbibliotecaapp.response.AdminUsuarioResponse;
import com.example.adminbibliotecaapp.response.DataAdminUsuario;
import com.example.adminbibliotecaapp.retrofit.RetrofitClient;
import com.example.adminbibliotecaapp.utils.Utils;
import com.example.adminbibliotecaapp.webservices.WebService;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginScreenActivity extends AppCompatActivity {

    private ActivityLoginScreenBinding binding;
    Utils utils = new Utils();
    DataAdminUsuario usuario = new DataAdminUsuario();
    Retrofit retrofit = new RetrofitClient().getRetrofit();
    WebService webService = retrofit.create(WebService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener( view -> {
            boolean isValido = utils.validarCamposLogin(
                    binding.etUsuario.getText().toString().trim(),
                    binding.etcontrasena.getText().toString().trim()
            );

            if (!isValido){
                Toasty.error(LoginScreenActivity.this, "Verifica que no existan campos vacios", Toasty.LENGTH_SHORT, true).show();
                return;
            }

            login();

        });

    }

    private void login(){
        usuario.setIdUsuario(binding.etUsuario.getText().toString().trim());
        usuario.setContrasena(binding.etcontrasena.getText().toString().trim());

        Call<AdminUsuarioResponse> call = webService.login(usuario);
        call.enqueue(new Callback<AdminUsuarioResponse>() {
            @Override
            public void onResponse(Call<AdminUsuarioResponse> call, Response<AdminUsuarioResponse> response) {
                if (response.body().getCode().equals("200")){
                    utils.guardarSharedPreferences(LoginScreenActivity.this, response.body().getData().get(0));
                    Toasty.success(LoginScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }else {
                    Toasty.error(LoginScreenActivity.this, response.body().getMensaje(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<AdminUsuarioResponse> call, Throwable t) {

            }
        });


    }
}