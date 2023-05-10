package com.example.adminbibliotecaapp.response;

import com.google.gson.annotations.SerializedName;

public class DataUsuario {

    @SerializedName("id_usuario")
    private String idUsuario;

    @SerializedName("nom_usuario")
    private String nomUsuario;

    @SerializedName("estado_usuario")
    private String estadoUsuario;

    @SerializedName("contrasena")
    private String contrasena;


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void resetData(){
        this.idUsuario = "";
        this.nomUsuario = "";
        this.estadoUsuario = "";
        this.contrasena = "";
    }

    @Override
    public String toString() {
        return this.idUsuario + " " + this.nomUsuario;
    }
}
