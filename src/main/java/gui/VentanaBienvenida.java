package gui;

import javax.swing.*;
import java.awt.event.*;


public class VentanaBienvenida extends Ventana{

    public static void main(String[] args) {
        VentanaBienvenida ventana = new VentanaBienvenida();
    }

    private JLabel textoMenu;
    private JButton botonRegistrarCarrera;
    private JButton botonRegistrarEstudiante;
    private JButton botonBuscarEstudiante;
    private JButton botonSalida;

    public VentanaBienvenida() {
        super("INTRANET 2.0", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana(){
        generarMensajeMenu();
        generarBotonRegistrarCarrera();
        generarBotonRegistrarEstudiante();
        generarBotonBuscarEstudiante();
        generarBotonSalida();

    }

    private void generarMensajeMenu(){
        String textoBienvenida = "BIENVENIDO A INTRANET 2.0";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 130, 10, 300, 50);
    }

    private void generarBotonRegistrarCarrera(){
        botonRegistrarCarrera = super.generarBoton("Registrar Carrera", 150, 100, 200, 50);
        botonRegistrarCarrera.addActionListener(this);
        super.add(botonRegistrarCarrera);
    }

    private void generarBotonRegistrarEstudiante(){
        botonRegistrarEstudiante = super.generarBoton("Registrar Estudiante", 150, 160, 200, 50);
        botonRegistrarEstudiante.addActionListener(this);
        super.add(botonRegistrarEstudiante);
    }

    private void generarBotonBuscarEstudiante(){
        botonBuscarEstudiante = super.generarBoton("Buscar Estudiante", 150, 220, 200, 50);
        botonBuscarEstudiante.addActionListener(this);
        super.add(botonBuscarEstudiante);
    }

    private void generarBotonSalida(){
        botonSalida = super.generarBoton("Salir", 150, 380, 200, 50);
        botonSalida.addActionListener(this);
        super.add(botonSalida);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == botonRegistrarCarrera){
            VentanaRegistroCarrera ventanaRegistroCarrera = new VentanaRegistroCarrera();
            this.dispose();
        }
        if(e.getSource() == this.botonRegistrarEstudiante){
            try {
                VentanaRegistroEstudiante ventanaRegistroEstudiante = new VentanaRegistroEstudiante();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        }

        if(e.getSource() == this.botonBuscarEstudiante){
            try {
                VentanaBusquedaEstudiante ventanaBusquedaEstudiante = new VentanaBusquedaEstudiante();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        }
        if(e.getSource() == botonSalida){
            System.exit(0);
        }
    }
}