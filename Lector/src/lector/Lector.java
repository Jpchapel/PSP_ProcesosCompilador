package lector;

import java.io.File;

public class Lector {

    private void verArchivos(String extensión, String directorio) {
        File[] archivos;

        File f = new File(directorio);
        if (f.exists()) {
            archivos = f.listFiles();
            
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    verArchivos(extensión, archivo.getAbsolutePath());
                } else {
                    if (archivo.getPath().endsWith(extensión))
                        System.out.println(archivo.getPath());
                }
            }
        } else {
            System.out.println("El directorio no existe.");
        }
    }

    /**
     * 
     * @param args 
     * El primer parámetro es la extensión de los códigos fuente que se deben
     * compilar.
     * El segundo parámetro contiene el nombre del directorio donde se almacenan 
     * los códigos fuente.
     */
    private Lector(String[] args){
        if (args.length == 2) {
            verArchivos(args[0], args[1]);
        } else {
            System.out.println("Sintaxis: aplicación Extensión Directorio");
        }
    }
    
    public static void main(String[] args) {
        new Lector(args);
    }
}
