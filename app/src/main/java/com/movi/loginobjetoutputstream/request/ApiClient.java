package com.movi.loginobjetoutputstream.request;

import android.content.Context;

import com.movi.loginobjetoutputstream.modelo.Usuario;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {

    private static final String FILE_NAME = "usuario.dat";

    // Método para guardar el usuario en un archivo usando ObjectOutputStream
    public static void guardar(Context context, Usuario usuario) {
        try {
            // Abrir el archivo en modo de escritura
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Escribir el objeto Usuario en el archivo
            oos.writeObject(usuario);

            // Cerrar los flujos
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para leer el usuario del archivo usando ObjectInputStream
    public static Usuario leerDatos(Context context) {
        Usuario usuario = null;

        try {
            // Abrir el archivo en modo de lectura
            FileInputStream fis = context.openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            // Leer el objeto Usuario del archivo
            usuario = (Usuario) ois.readObject();

            // Cerrar los flujos
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    // Método de login para verificar email y contraseña
    public static Usuario login(Context context, String email, String contrasena) {
        // Leer el usuario guardado
        Usuario usuario = leerDatos(context);

        // Verificar que no sea null y que las credenciales coincidan
        if (usuario != null && usuario.getEmail().equals(email) && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }

        return null;
    }
}

