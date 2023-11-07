package com.example.recycler_permissions.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler_permissions.PMListener;
import com.example.recycler_permissions.R;
import com.example.recycler_permissions.models.Permisos;

import java.util.List;

public class PermisosAdapter extends RecyclerView.Adapter<PermisosAdapter.ViewHolder> {

    private List<Permisos> pmLista;
    private PMListener listener;

    public PermisosAdapter(List<Permisos> permisosList, PMListener listener)
    {
        this.pmLista=permisosList;
        this.listener=listener;
    }


    @NonNull
    @Override
    public PermisosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater ly=LayoutInflater.from(parent.getContext());
        View v=ly.inflate(R.layout.permisos, parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PermisosAdapter.ViewHolder holder, int position) {
        Permisos p = pmLista.get(holder.getAdapterPosition());
        holder.setdata(p);

        holder.sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onCheckedChange(pmLista.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pmLista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView namePM;
        Switch sw;
        ImageView icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namePM=itemView.findViewById(R.id.namePM);
            icon=itemView.findViewById(R.id.icon);
            sw=itemView.findViewById(R.id.sw);
        }

        public void setdata(Permisos p) {
            Permisos permisos=p;
            namePM.setText(p.getNombre());
            icon.setColorFilter(Color.parseColor(p.getColor()), PorterDuff.Mode.SRC_IN);

        }
    }
}

