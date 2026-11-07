package calculadoraconsumoelectrico;

public class CalculadoraConsumo {

    // Calcula el consumo diario de un aparato
    public static double calcularConsumoDiario(AparatoElectrico aparato) {

        return (aparato.getPotenciaWatts() *
                aparato.getHorasUsoDiario()) / 1000;
    }

    // Calcula el consumo mensual estimado
    public static double calcularConsumoMensual(AparatoElectrico aparato) {

        return calcularConsumoDiario(aparato) * 30;
    }

    // Calcula el consumo total diario de varios aparatos
    public static double calcularConsumoTotal(AparatoElectrico[] aparatos) {

        double total = 0;

        for (AparatoElectrico aparato : aparatos) {
            total += calcularConsumoDiario(aparato);
        }

        return total;
      }
   }
