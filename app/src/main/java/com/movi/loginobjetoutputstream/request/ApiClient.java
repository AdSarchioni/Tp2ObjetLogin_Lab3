package com.movi.loginobjetoutputstream.request;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.movi.loginobjetoutputstream.modelo.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {

    //private static File archivo = new File("usuario.dat");

    // Método para guardar el usuario en un archivo usando ObjectOutputStream
    public static void guardar(Context context, Usuario usuario) {

        File archivo = new File(context.getFilesDir(), "usuario.dat");  try {
            // Abrir el archivo en modo de escritura
            FileOutputStream fos = new FileOutputStream(archivo,true);
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            ObjectOutputStream oos=new ObjectOutputStream(bos);


            // Escribir el objeto Usuario en el archivo
            oos.writeObject(usuario);

            // Cerrar los flujos
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "error al guardar", Toast.LENGTH_SHORT).show();
            Log.d("salida",e.getMessage());
        }
    }

    // Método para leer el usuario del archivo usando ObjectInputStream
    public static Usuario leerDatos(Context context) {
        Usuario usuario = null;
        File archivo = new File(context.getFilesDir(), "usuario.dat");
        try {
            // Abrir el archivo en modo de lectura


            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis= new BufferedInputStream(fis);
            ObjectInputStream ois= new ObjectInputStream(bis);

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
