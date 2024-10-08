package com.movi.loginobjetoutputstream.ui.registro;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.movi.loginobjetoutputstream.databinding.ActivityRegistroBinding;
import com.movi.loginobjetoutputstream.modelo.Usuario;

public class Registro extends AppCompatActivity {
    private RegistroViewModel vm;
    private ActivityRegistroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.tvDni.setText(usuario.getDni()+"");
                binding.tvApellido.setText(usuario.getApellido());
                binding.tvNombre.setText(usuario.getNombre());
                binding.tvEmail.setText(usuario.getEmail());
                binding.tvContrasena.setText(usuario.getContrasena());


            }
        });
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.guardarUsuarios(
                        binding.tvApellido.getText().toString(),
                        binding.tvNombre.getText().toString(),
                        binding.tvDni.getText().toString(),
                        binding.tvEmail.getText().toString(),
                        binding.tvContrasena.getText().toString());

            }
        });
        vm.leerDatos(getIntent());
    }


    }
