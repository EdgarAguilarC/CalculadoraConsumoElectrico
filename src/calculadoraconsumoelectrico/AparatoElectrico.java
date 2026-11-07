package calculadoraconsumoelectrico;


public class AparatoElectrico {
      
    private String nombre;
    private double potenciaWatts;
    private double horasUsoDiario;
    
    
    public AparatoElectrico(String nombre, double potenciaWatts, double horasUsoDiario) {
      this.nombre = nombre;
      this.potenciaWatts = potenciaWatts;
      this.horasUsoDiario = horasUsoDiario;
      
   }

    
  public String getNombre() {
    return nombre;
  }

 public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPotenciaWatts() {
    return potenciaWatts;
  }

  public void setPotenciaWatts(double potenciaWatts) {
    this.potenciaWatts = potenciaWatts;
  }

  public double getHorasUsoDiario() {
    return horasUsoDiario;
  }

  public void setHorasUsoDiario(double horasUsoDiario) {
     this.horasUsoDiario = horasUsoDiario;
  }  
       
   }

