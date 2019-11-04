package compilador;

import java.io.IOException;
import java.util.ArrayList;
import GestorProcesos.Proceso;
import java.io.File;

public class Compilador {

    private String extraerNombre(String archivo) {
        return archivo.substring(0, archivo.lastIndexOf('.'));
    }

    private String getEjecutable(File fichero) {
        String absolutePath = fichero.getAbsolutePath();
        return absolutePath.substring(0, absolutePath.lastIndexOf('.'))
                + ".exe";
    }

    private void compila(String archivo) throws IOException {
        Proceso compiladorCSharp = null;

        try {
            ArrayList<String> values = new ArrayList<>();
            values.add("C:\\Windows\\Microsoft.NET\\Framework64\\v4.0.30319\\csc.exe");
            values.add("-out:" + getEjecutable(new File(archivo)));
            values.add(archivo);

            compiladorCSharp = new Proceso(values);
            System.out.println("compilando " + values);
            String salida;
            while ((salida = compiladorCSharp.leer()) != null) {
                System.out.println(salida);
            }
        } finally {
            compiladorCSharp.fin();
        }
    }

    private void go(String directorio) throws IOException {
        Proceso lector = null;
        try {
            ArrayList<String> values = new ArrayList<>();
            values.add("java");
            values.add("-cp");
            values.add("C:\\Users\\Alberto\\Desktop\\Solucion\\Lector\\build\\classes");
            values.add("lector.Lector");
            values.add("cs");
            values.add(directorio);
            lector = new Proceso(values);
            String línea;
            while ((línea = lector.leer()) != null) {
                System.out.println("Procesando " + línea);
                compila(línea);
            }
        } finally {
            lector.fin();
        }
    }

    /**
     *
     * @param args Directorio con los fuentes que se deben compilar
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Compilador app = new Compilador();
        app.go(args[0]);
    }
}
