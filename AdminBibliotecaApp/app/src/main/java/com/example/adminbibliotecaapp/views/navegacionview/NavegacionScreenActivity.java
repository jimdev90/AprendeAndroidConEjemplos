package com.example.adminbibliotecaapp.views.navegacionview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.example.adminbibliotecaapp.R;
import com.example.adminbibliotecaapp.databinding.ActivityNavegacionScreenBinding;
import com.example.adminbibliotecaapp.response.DataAdminUsuario;
import com.example.adminbibliotecaapp.utils.Utils;

public class NavegacionScreenActivity extends AppCompatActivity {

    private ActivityNavegacionScreenBinding binding;
    DataAdminUsuario usuario;
    Utils utils = new Utils();
    String[] listaMenu;
    AdaptadorMenu adaptadorMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavegacionScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usuario = utils.leerSharedPreferences(NavegacionScreenActivity.this);
        binding.tvUsuario.setText(usuario.getNomUsuario());

        setupRecyclerView();

    }

    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.rvMenu.setLayoutManager(layoutManager);
        listaMenu = getResources().getStringArray(R.array.lista_menu);
        adaptadorMenu = new AdaptadorMenu(this, listaMenu);
        binding.rvMenu.setAdapter(adaptadorMenu);

    }
}