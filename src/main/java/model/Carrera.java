package model;

public class Carrera {
    private String nombreCarrera;

    private String codigoCarrera;

    private int cantidadSemestres;

    public Carrera(String nombreCarrera, String codigoCarrera, int cantidadSemestres) {
        this.nombreCarrera = nombreCarrera;
        this.codigoCarrera = codigoCarrera;
        this.cantidadSemestres = cantidadSemestres;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public String getCodigoCarrera() {
        return codigoCarrera;
    }

    public int getCantidadSemestres() {
        return cantidadSemestres;
    }
}