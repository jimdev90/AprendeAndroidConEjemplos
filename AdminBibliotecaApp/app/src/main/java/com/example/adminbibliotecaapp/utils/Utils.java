package com.example.adminbibliotecaapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.adminbibliotecaapp.response.DataAdminUsuario;

public class Utils {

    private static final int DURACION_SPLASH_SCREEN = 4000;


    public int getDuracionSplashScreen() {
        return DURACION_SPLASH_SCREEN;
    }

    public boolean validarCamposLogin(String idUsuario, String contrasena){
        if (idUsuario.isEmpty() || contrasena.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public void guardarSharedPreferences(Context context, DataAdminUsuario usuario) {
        SharedPreferences preferences = context.getSharedPreferences("data_usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("idUsuario", usuario.getIdUsuario());
        editor.putString("nomUsuario", usuario.getNomUsuario());
        editor.apply();
    }

}