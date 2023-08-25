package com.demo.examen4.Adapters;
// imports necesarios para el adapter view en recycler
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.demo.examen4.R;
import java.util.List;
// fin de imports
import com.demo.examen4.entity.Gato;


public class GatoAdapter extends RecyclerView.Adapter<GatoAdapter.GatoViewHolder> {

    private List<Gato> listaGatos;
    //
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        //void onItemClick(Gato gato);
        void onItemClick(Gato gato, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    //

    public GatoAdapter(List<Gato> listaGatos) {
        this.listaGatos = listaGatos;
    }

    @NonNull
    @Override
    public GatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personalizada, parent, false);
        return new GatoViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull GatoViewHolder holder, int position) {
//        Gato gato = listaGatos.get(position);
//
//        // Configurar las vistas con los datos del gato
//        holder.txtNombre.setText(gato.getNombre());
//        holder.txtRaza.setText(gato.getRaza());
//
//        // Cargar la imagen usando Picasso en el ImageView
//        Picasso.get().load(gato.getImagen()).into(holder.ivImagen);
//    }

    @Override
    public void onBindViewHolder(@NonNull GatoViewHolder holder, int position) {
        Gato gato = listaGatos.get(position);

        // Configurar las vistas con los datos del gato
        holder.txtNombre.setText(gato.getNombre());
        holder.txtRaza.setText(gato.getRaza());
        Picasso.get().load(gato.getImagen()).into(holder.ivImagen);
        // ...

//        // Agregar el click listener al itemView (elemento de la lista)
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Llamar a la función para abrir DetallesActivity y pasar los datos
//                if (listener != null) {
//                    listener.onItemClick(gato);
//                }
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(gato, position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaGatos.size();
    }

    public class GatoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView txtNombre, txtRaza;

        public GatoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.iv_lista);
            txtNombre = itemView.findViewById(R.id.txt_Nombre);
            txtRaza = itemView.findViewById(R.id.txt_Raza);
        }
    }
}
