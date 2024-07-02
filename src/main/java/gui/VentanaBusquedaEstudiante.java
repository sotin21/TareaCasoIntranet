package gui;

import controller.CarreraController;
import controller.EstudianteController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaBusquedaEstudiante extends Ventana{
    private JButton botonBuscar, botonRegresar;
    private JLabel textoEncabezado, textoCarrera, textoNombre;
    private JComboBox campoCarrera;
    private JTextField campoNombre;

    public VentanaBusquedaEstudiante() throws ClassNotFoundException {
        super("Búsqueda de Estudiante", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() throws ClassNotFoundException {
        generarCampoNombre();
        generarBotonBuscarEstudiante();
        generarBotonCancelar();
        generarListaCarrera();
    }

    private void generarCampoNombre(){
        String textoNombre= "Nombre estudiante:";
        super.generarJLabel(this.textoNombre,textoNombre,20,50,150,20);
        this.campoNombre= super.generarJTextField(200,50,250,20);
        this.add(this.campoNombre);
    }

    private void generarListaCarrera() throws ClassNotFoundException {
        super.generarJLabel(this.textoCarrera,"Carrera:",20,100,150,20);
        this.campoCarrera=super.generarListaDesplegable(CarreraController.getCodigoCarreras(),200,100, 250, 20);
        this.add(this.campoCarrera);
    }

    private void generarBotonBuscarEstudiante() {
        String textoBoton= "Buscar Estudiante";
        this.botonBuscar = super.generarBoton(textoBoton, 75, 400, 150, 20);
        this.add(this.botonBuscar);
        this.botonBuscar.addActionListener(this);
    }

    private void generarBotonCancelar() {
        String textoBotonRegresar = "Regresar";
        this.botonRegresar = super.generarBoton(textoBotonRegresar, 275, 400, 150, 20);
        this.add(this.botonRegresar);
        this.botonRegresar.addActionListener(this);
    }

    private String[][] exportarDatos() throws ClassNotFoundException {
        if(this.campoNombre.getText().length()==0 && this.campoCarrera.getSelectedItem().equals("Error no existen carreras") ){
            JOptionPane.showMessageDialog(this,"Ingrese datos validos");
            return new String[0][0];
        }
        else if(this.campoNombre.getText().length()==0){
            return EstudianteController.mostrarEstudiantesPorCarrera(this.campoCarrera.getSelectedItem().toString());
        }
        else{
            return EstudianteController.mostrarEstudiantesPorNombre(this.campoCarrera.getSelectedItem().toString(),this.campoNombre.getText());
        }


    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.botonBuscar){
            String[] nombreColumnas={"Nombre estudiante","N° de matricula","Carrera","CodigoCarrera"};
            try {
                VentanaTabla ventanaTabla= new VentanaTabla(exportarDatos(),nombreColumnas);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == this.botonRegresar){
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }

    }
}