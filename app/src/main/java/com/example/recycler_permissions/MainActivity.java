package com.example.recycler_permissions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.recycler_permissions.adapters.PermisosAdapter;
import com.example.recycler_permissions.models.Permisos;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PMListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List <Permisos> permisosList= new ArrayList<>();

        permisosList.add(new Permisos("Llamar a mamá", "#775447"));
        permisosList.add(new Permisos("Abrir cámara", "#607d8b"));

        RecyclerView rv= findViewById(R.id.rc);
        rv.setAdapter(new PermisosAdapter(permisosList, this));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
    }

    @Override
    public void onCheckedChange(Permisos p) {
        String permiso= p.getNombre();

        switch(permiso)
        {
            case "Llamar a mamá":
                if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED)
                {

                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1234);
                }
                else {
                    Llamar();
                }
                break;
            case "Abrir cámara":
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
                {

                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 0001);
                }
                else {
                    Open();
                }
                break;

        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==1234)
        {

            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Llamar();
            }
        }
        else if(requestCode==0001)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Open();
            }
        }
    }

    public void Llamar(){
        Intent call= new Intent(Intent.ACTION_CALL, Uri.parse("tel: 8712429580"));
        startActivity(call);
    }

    public void Open()
    {
        Intent cam= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(cam);
    }
}