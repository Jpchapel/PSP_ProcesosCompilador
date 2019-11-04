package GestorProcesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

public class Proceso {

    private final Process proceso;
    private BufferedReader brProceso;
    private PrintStream psProceso;

    private void crearCanalesComunicación() {
        brProceso = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        psProceso = new PrintStream(proceso.getOutputStream());
    }

    public Proceso(String[] orden) throws IOException {
        proceso = Runtime.getRuntime().exec(orden);
        crearCanalesComunicación();
    }

    public Proceso(String orden) throws IOException {
        proceso = Runtime.getRuntime().exec(orden);
        crearCanalesComunicación();
    }

    public Proceso(List orden) throws IOException {
        proceso = new ProcessBuilder().command(orden).start();
        crearCanalesComunicación();
    }

    public void escribir(String datos) {
        psProceso.println(datos);
        psProceso.flush();
    }

    public String leer() throws IOException {
        return brProceso.readLine();
    }

    public void fin() throws IOException {
        brProceso.close();
        psProceso.close();
        proceso.destroy();
    }
}
