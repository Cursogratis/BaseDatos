package com.laboratorio.alfonso.basedatos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3, et4;
    Cursor fila;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.txt_ced);
        et2 = (EditText) findViewById(R.id.txt_nombres);
        et3 = (EditText) findViewById(R.id.txt_apellidos);
        et4 = (EditText) findViewById(R.id.txt_edad);


    }

    public void guardarn(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cedula = et1.getText().toString();
        String nombre = et2.getText().toString();
        String apellido = et3.getText().toString();
        String edad = et4.getText().toString();
        ContentValues registro = new ContentValues();  //es una clase para guardar datos
        registro.put("u_ced", cedula);
        registro.put("u_nom", nombre);
        registro.put("u_ape", apellido);
        registro.put("u_eda", edad);
        bd.insert("usuario", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show();

    }

    public void eliminar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        String cedula = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select u_nom,u_ape,u_eda  from usuario where u_ced='" + cedula + "'", null);
        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una persona con dicha cedula" , Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void consulta(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        String cedula = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select u_nom,u_ape,u_eda  from usuario where u_ced='" + cedula + "'", null);
        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una persona con dicha cedula" , Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void modificar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cedula = et1.getText().toString();
        String nombre = et2.getText().toString();
        String apellido = et3.getText().toString();
        String edad = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("u_nom", nombre);
        registro.put("u_ape", apellido);
        registro.put("u_eda", edad);
        int cant = bd.update("usuario", registro, "u_ced='" + cedula + "'", null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "no existe una persona con dicho documento", Toast.LENGTH_SHORT).show();

    }

    public void inicio(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        fila = bd.rawQuery("select * from usuario order by u_ced asc ", null);

        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
            et3.setText(fila.getString(2));
            et4.setText(fila.getString(3));
        } else
            Toast.makeText(this, "No hay registrados" , Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void anterior(View view) {
        try {

            if (!fila.isFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
                fila.moveToPrevious();
                et1.setText(fila.getString(0));
                et2.setText(fila.getString(1));
                et3.setText(fila.getString(2));
                et4.setText(fila.getString(3));
            } else
                Toast.makeText(this, "Llego al principio de la tabla", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void siguiente(View view) {
        try {

            if (!fila.isLast()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
                fila.moveToNext();
                et1.setText(fila.getString(0));
                et2.setText(fila.getString(1));
                et3.setText(fila.getString(2));
                et4.setText(fila.getString(3));
            } else
                Toast.makeText(this, "Llego al final de la tabla", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void fin(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select * from usuario order by u_ced asc", null);

        if (fila.moveToLast()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
            et3.setText(fila.getString(2));
            et4.setText(fila.getString(3));
        } else
            Toast.makeText(this, "No hay registrados" , Toast.LENGTH_SHORT).show();
        bd.close();

    }


    public void onReset(View view) {
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");

    }
}
