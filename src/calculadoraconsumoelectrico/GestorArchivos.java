package calculadoraconsumoelectrico;

  import java.io.*;
  import java.util.ArrayList;

  public class GestorArchivos {

    private static final String ARCHIVO = "aparatos.txt";

    // Guarda los aparatos en un archivo local
    public static void guardar(ArrayList<AparatoElectrico> aparatos) {

        try (BufferedWriter escritor =
                new BufferedWriter(new FileWriter(ARCHIVO))) {

            for (AparatoElectrico aparato : aparatos) {

                escritor.write(
                    aparato.getNombre() + ";" +
                    aparato.getPotenciaWatts() + ";" +
                    aparato.getHorasUsoDiario()
                );

                escritor.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // Recupera los aparatos guardados
    public static ArrayList<AparatoElectrico> cargar() {

        ArrayList<AparatoElectrico> aparatos = new ArrayList<>();

        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            return aparatos;
        }

        try (BufferedReader lector =
                new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split(";");

                if (datos.length == 3) {

                    String nombre = datos[0];
                    double potencia = Double.parseDouble(datos[1]);
                    double horas = Double.parseDouble(datos[2]);

                    aparatos.add(
                        new AparatoElectrico(nombre, potencia, horas)
                    );
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }

        return aparatos;
      }
    }
