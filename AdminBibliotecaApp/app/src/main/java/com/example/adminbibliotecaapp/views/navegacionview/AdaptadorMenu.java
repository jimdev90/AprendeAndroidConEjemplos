package com.example.adminbibliotecaapp.views.navegacionview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminbibliotecaapp.R;

public class AdaptadorMenu extends RecyclerView.Adapter<AdaptadorMenu.ViewHolder> {

    Context context;
    String[] listaMenu;

    public AdaptadorMenu(Context context, String[] listaMenu){
        this.context = context;
        this.listaMenu = listaMenu;
    }

    @NonNull
    @Override
    public AdaptadorMenu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_menu, null, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorMenu.ViewHolder holder, int position) {

        String menu = listaMenu[position];
        Glide.with(context)
                .load(context.getResources().toString(R.string.url_icon_menu) + menu.toLowerCase() + ".png" )
                .centerInside()
                .placeholder(context.getResources().getDrawable(R.drawable.icon_falta_foto))
                .into(holder.ivOpcion);

        holder.tvOpcion.setText(menu.toUpperCase());
        holder.cvOpcionMenu.setOnClickListener( view -> {
            Intent intent;
            switch (menu.toLowerCase()){
                case "usuarios":
                    intent = new Intent(context, UsuariosScreenActivity.class);
                    context.startActivity(intent);
                    break;

            }
        });

    }

    @Override
    public int getItemCount() {
        return listaMenu.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        CardView cvOpcionMenu;
        ImageView ivOpcion;
        TextView tvOpcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvOpcionMenu = itemView.findViewById(R.id.cvOpcionMenu);
            ivOpcion = itemView.findViewById(R.id.ivOpcion);
            tvOpcion = itemView.findViewById(R.id.tvOpcion);
        }
    }
}
