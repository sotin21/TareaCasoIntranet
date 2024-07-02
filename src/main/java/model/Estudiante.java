package model;

public class Estudiante {
    private String rut;

    private String nombre;

    private String matricula;


    private Carrera carrera;

    public Estudiante(String rut, String nombre, String matricula, Carrera carrera) {
        this.rut = rut;
        this.nombre = nombre;
        this.matricula = matricula;
        this.carrera = carrera;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public String toString(){
        return this.rut+","+this.nombre+","+this.matricula;
    }
}