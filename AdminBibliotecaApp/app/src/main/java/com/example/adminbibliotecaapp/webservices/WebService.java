package com.example.adminbibliotecaapp.webservices;

import com.example.adminbibliotecaapp.response.AdminUsuarioResponse;
import com.example.adminbibliotecaapp.response.DataAdminUsuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebService {

    @POST("/adminlogin")
    Call<AdminUsuarioResponse> login(
            @Body DataAdminUsuario usuario
    );

}
