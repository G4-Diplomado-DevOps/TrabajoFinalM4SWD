package com.devops.dxc.devops.model;


import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;


public class Util {

    private static String readAll(Reader rd) {
        StringBuilder sb = new StringBuilder();
        int cp;
        try {
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) {
        try {
            InputStream is = new URL(url).openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                JSONObject json = new JSONObject(jsonText);
                return json;
            } finally {
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para cacular el 10% del ahorro en la AFP.  Las reglas de negocio se pueden conocer en 
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @param sueldo
     * @return
     */
    public static int getDxc(int ahorro, int sueldo){
        if(((ahorro*0.1)/getUf()) > 150 ){
            return (int) (150*getUf()) ;
        } else if((ahorro*0.1)<=1000000 && ahorro >=1000000){
            return (int) 1000000;
        } else if( ahorro <=1000000){
            return (int) ahorro;
        } else {
            return (int) (ahorro*0.1);
        }
    }

    /**
     * Método que retorna el valor de la UF.  Este método debe ser refactorizado por una integración a un servicio
     * que retorne la UF en tiempo real.  Por ejemplo mindicador.cl
     * @return
     */
    public static Double getUf(){


        String url = "https://mindicador.cl/api/";

        JSONObject json = readJsonFromUrl(url);

        JSONObject objUf = json.getJSONObject("uf"); // Con esto obtienes el objeto uf

        System.out.println(objUf.getString("fecha"));
        System.out.println(objUf.getDouble("valor"));

        return objUf.getDouble("valor");
    }


    public static int getSaldoDxc(int ahorro, int sueldo) {

        return (ahorro-getDxc(ahorro, sueldo));

    }

    public static int getImpuesto(int sueldo) {

        /* segun la table del SII, impuesto de segunda categoria y condición > 30 UTA (1.530.000 aprox)
        Tramo de remuneración: $0 - $500.000 | Retiro promedio de la persona afiliada: $989.413 | Impuesto a cancelar: $0
        TR: $500.000 - $1.000.000 | RP $1.490.241 | IMP. $0
        TR: $1.000.000 - $1.500.000 | RP: $1.989.013 | IMP: $0
        TR: $1.500.000 - $2.500.000 | RP: $2.247.661 | IMP: $174.531
        TR: $2.500.000 - 3.000.000 | RP: $3.815.984 | IMP: $364.086
        TR: $3.000.000 - $4.000.000 | RP: $3.815.984 | IMP: $612.396
        TR: $4.000.000 - $5.000.000 | RP: $3.815.984 | IMP: 980.357
        TR: $5.000.000 - $6.000.000 | RP: $3.815.984 | IMP: 1.294.052
        TR: $6.000.000 y más | RP: $3.815.984 | IMP: $1.491.042
         */
        if (sueldo < 1500000) {
            return 0;
        } else if (sueldo > 1500000 && sueldo <= 2500000) {
            return 174531;
        } else if (sueldo > 2500000 && sueldo <= 3000000) {
            return 364086;
        } else if (sueldo > 3000000 && sueldo <= 4000000) {
            return 612396;
        } else if (sueldo > 4000000 && sueldo <= 5000000) {
            return 980357;
        } else if (sueldo > 5000000 && sueldo <= 6000000) {
            return 1294052;
        } else if (sueldo > 6000000) {
            return 1491042;
        } else {
            return -1;
        }

    }
    
}
