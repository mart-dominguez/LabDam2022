package com.mdgz.dam.labdam2022.utilities;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class ManejoLogs {

    Context ctx;
    public ManejoLogs(Context con){ this.ctx = con; }

    //Metodo para escribir el log en un archivo interno desde un json
    public void escribirEnArchivo(JSONObject log){
        try{
            JSONArray datos;
            File archivo = new File(ctx.getFilesDir() + "/logs.json");

            if (archivo.exists()) datos = (JSONArray) new JSONTokener(this.leerDeArchivo()).nextValue();
            else datos = new JSONArray();

            datos.put(log);
            FileOutputStream fos = ctx.openFileOutput("logs.json", Context.MODE_PRIVATE);
            fos.write(datos.toString().getBytes());
            fos.close();

        }catch (FileNotFoundException e) { e.printStackTrace(); }
         catch (IOException e) { e.printStackTrace();} catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //Metodo para leer los logs de un archivo interno desde un json
    public String leerDeArchivo(){
        StringBuilder sb = new StringBuilder();
        try{
            FileInputStream fis = ctx.openFileInput("logs.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffRdr = new BufferedReader(isr);
            String line;
            while ((line = buffRdr.readLine()) != null) { sb.append(line); }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



}
