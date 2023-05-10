package com.example.adminbibliotecaapp.views.autoresview;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminbibliotecaapp.R;
import com.example.adminbibliotecaapp.response.DataAutor;

import java.util.List;

public class AdaptadorAutor extends RecyclerView.Adapter<AdaptadorAutor.ViewHolder> {

    Context context;
    List<DataAutor> listaAutores;
    OnItemCliked onClick;

    public AdaptadorAutor(Context context, List<DataAutor> listaAutores, OnItemCliked onClick) {
        this.context = context;
        this.listaAutores = listaAutores;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public AdaptadorAutor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_autores, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAutor.ViewHolder holder, int position) {
        DataAutor autor = listaAutores.get(position);
        Log.d(TAG, "onBindViewHolder: " + autor);

        holder.tvAutor.setText(autor.getNomAutor().toUpperCase());


        holder.ibtnEditar.setOnClickListener( view -> {
            onClick.editarAutor(autor);
        });

        holder.ibtnBorrar.setOnClickListener( view -> {
            onClick.borrarAutor(autor);
        });

    }

    @Override
    public int getItemCount() {
        return listaAutores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAutor;
        ImageButton ibtnEditar, ibtnBorrar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAutor = itemView.findViewById(R.id.tvAutor);
            ibtnEditar = itemView.findViewById(R.id.ibtnEditar);
            ibtnBorrar = itemView.findViewById(R.id.ibtnEliminar);
        }
    }

    public interface OnItemCliked {
        void editarAutor(DataAutor autor);
        void borrarAutor(DataAutor autor);
    }
}
