package com.example.adminbibliotecaapp.webservices;

import com.example.adminbibliotecaapp.response.AdminUsuarioResponse;
import com.example.adminbibliotecaapp.response.AutoresResponse;
import com.example.adminbibliotecaapp.response.DataAdminUsuario;
import com.example.adminbibliotecaapp.response.DataAutor;
import com.example.adminbibliotecaapp.response.DataUsuario;
import com.example.adminbibliotecaapp.response.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebService {

    //ENDPOINTS PARA USUARIOS
    @POST("/adminlogin")
    Call<AdminUsuarioResponse> login(
            @Body DataAdminUsuario usuario
    );

    @GET("/usuarios")
    Call<UsuarioResponse> obtenerUsuarios();

    @POST("/usuarios/add")
    Call<UsuarioResponse> agregarUsuario(
            @Body DataUsuario usuario
    );

    @POST("/usuarios/update")
    Call<UsuarioResponse> actualizarusuario(
            @Body DataUsuario usuario
    );

    @POST("/usuarios/delete")
    Call<UsuarioResponse> borrarUsuario(
            @Body DataUsuario usuario
    );

    //ENDPOINTS PARA AUTORES
    @GET("/autores")
    Call<AutoresResponse> obtenerAutores();

    @POST("/autores/add")
    Call<AutoresResponse> agregarAutor(
            @Body DataAutor autor
    );

    @POST("/autores/update")
    Call<AutoresResponse> actualizarAutor(
            @Body DataAutor autor
    );

    @POST("/autores/delete")
    Call<AutoresResponse> borrarAutor(
            @Body DataAutor autor
    );

}
