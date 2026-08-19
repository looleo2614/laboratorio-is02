import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public abstract class Sensor{
    private String id;
    private String ubicacion;
    private boolean activo;
    public Sensor(String id, String ubicacion, boolean activo){
        this.id=id;
        this.ubicacion=ubicacion;
        this.activo=activo;
    }
    public abstract double tomarLectura();
    public abstract String evaluarEstado();
}

class SensorHumedadSuelo extends Sensor{
    private double humedadPct;
    private Random datoH=new Random();

    public SensorHumedadSuelo(String id, String ubicacion, boolean activo){
        super(id,ubicacion,activo);
    }
    @Override public double tomarLectura(){
        humedadPct=datoH.nextDouble()*100;
        return humedadPct;
    }
    @Override public String evaluarEstado(){
        if (humedadPct<20){
            return "estado critico";
        }
        return "normal";
    }
}

class SensorTemperatura extends Sensor{
    private double celsius;
    private Random datoT=new Random();

    public SensorTemperatura(String id, String ubicacion, boolean activo){
        super(id,ubicacion,activo);
    }

    @Override public double tomarLectura(){
        celsius=15+datoT.nextDouble()*30;
        return celsius;
    }
    @Override public String evaluarEstado(){
        if (celsius>38){
            return "estado critico";
        }
        return "normal";
    }
}

class EstacionMonitoreo extends Sensor{
 private List<Sensor> sensores;
 public EstacionMonitoreo(){
    sensores=new ArrayList<>();
 }
 public void sumarSensor(Sensor sensor){
    sensores.add(sensor);
 }
 public void procesarLecturas(){
    for(Sensor sensor:sensores){
        if (sensor.activo()){
            double lectura = sensor.tomarLectura();
            System.out.println("Sensor: "+sensor.id
                +" ubicacion: "+sensor.ubicacion
                +" lectura: "+String.format("%.2f",lectura)
                +" estado: "+sensor.evaluarEstado()
            );
        }
    }
 }
}