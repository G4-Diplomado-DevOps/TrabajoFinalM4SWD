package com.devops.dxc.devops.model;

public class Util {

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
    public static int getUf(){
        return 29000;
    }


    public static int getSaldoDxc(int ahorro, int sueldo) {

        int dxc = getDxc(ahorro, sueldo);

        return (ahorro-dxc);

    }

    public static int getImpuesto(int sueldo) {

        /* 0 impuesto para TODOS */
        return 0;
    }
    
}
