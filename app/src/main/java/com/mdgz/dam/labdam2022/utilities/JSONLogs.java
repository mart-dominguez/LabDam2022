package com.mdgz.dam.labdam2022.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class JSONLogs {

    Context context;
    public JSONLogs(Context con) { this.context = con; }

    //Metodo para escribir el log en un archivo interno desde un json

    public void writeToFile(JSONObject log)
    {
        try
        {
            JSONArray datos;
            File file = new File(context.getFilesDir() + "/logs.json");

            String datosPrevios = this.readFromFile();
            if(datosPrevios.length() > 0) datos = (JSONArray) new JSONTokener(datosPrevios).nextValue();
            else datos = new JSONArray();
            datos.put(log);

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String jsonString = datos.toString();

            bufferedWriter.write(jsonString);
            bufferedWriter.close();

        } catch (IOException | JSONException e) { e.printStackTrace(); }
    }


    //Metodo para leer los logs de un archivo interno desde un json

    public String readFromFile()
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            FileInputStream fis = context.openFileInput("logs.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffRdr = new BufferedReader(isr);
            String line;
            while ((line = buffRdr.readLine()) != null) sb.append(line);
            buffRdr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



}
