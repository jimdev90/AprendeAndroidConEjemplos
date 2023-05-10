package com.example.adminbibliotecaapp.response;

import com.google.gson.annotations.SerializedName;

public class DataAutor {

    @SerializedName("id_autor")
    private String idAutor;

    @SerializedName("nom_autor")
    private String nomAutor;

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getNomAutor() {
        return nomAutor;
    }

    public void setNomAutor(String nomAutor) {
        this.nomAutor = nomAutor;
    }

    public void resetData(){
        this.idAutor = "";
        this.nomAutor = "";
    }

    @Override
    public String toString() {
        return this.nomAutor;
    }
}
