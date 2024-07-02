package gui;
import controller.CarreraController;
import controller.EstudianteController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistroEstudiante extends Ventana {
    private JLabel textoEncabezado, textoRut, textoNombre, textoMatricula, textoCarrera;
    private JTextField campoRut, campoNombre, campoMatricula;
    private JComboBox campoCarrera;
    private JButton botonRegistrar, botonCancelar;


    public VentanaRegistroEstudiante() throws ClassNotFoundException {
        super("Registro de Estudiante", 500, 520);
        generarElementosVentana();
    }
    private void generarElementosVentana() throws ClassNotFoundException {
        generarEncabezado();
        generarBotonCancelar();
        generarBotonRegistrar();
        generarCampoMatricula();
        generarCampoNombre();
        generarCampoRut();
        generarListaCarrera();
    }
    private void generarEncabezado() {
        String textoCabecera = "Registro de Estudiante";
        super.generarJLabelEncabezado(this.textoEncabezado, textoCabecera, 190, 10, 200, 50);

    }
    private void generarBotonRegistrar() {
        String textoBoton= "Registrar Estudiante";
        this.botonRegistrar = super.generarBoton(textoBoton, 55, 400, 170, 20);
        this.add(this.botonRegistrar);
        this.botonRegistrar.addActionListener(this);
    }
    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        this.botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 170, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
    }
    private void generarCampoNombre(){
        String textoNombre= "Nombre:";
        super.generarJLabel(this.textoNombre,textoNombre,20,50,150,20);
        this.campoNombre= super.generarJTextField(200,50,250,20);
        this.add(this.campoNombre);
    }
    private void generarCampoRut(){
        String textoRut= "Rut:";
        super.generarJLabel(this.textoRut,textoRut,20,100,150,20);
        this.campoRut= super.generarJTextField(200,100,250,20);
        this.add(this.campoRut);
    }
    private void generarCampoMatricula(){
        String textoMatricula= "N° de matricula:";
        super.generarJLabel(this.textoMatricula,textoMatricula,20,150,150,20);
        this.campoMatricula= super.generarJTextField(200,150,250,20);
        this.add(this.campoMatricula);
    }
    private void generarListaCarrera() throws ClassNotFoundException {
        super.generarJLabel(this.textoCarrera,"Carrera:",20,200,100,20);
        this.campoCarrera=super.generarListaDesplegable(CarreraController.getCodigoCarreras(),200,200, 250, 20);
        this.add(this.campoCarrera);
    }
    private boolean registrarEstudiante() throws ClassNotFoundException {
        if(this.campoMatricula.getText().length()==0 || this.campoNombre.getText().length()==0 || this.campoRut.getText().length()==0 || this.campoCarrera.getSelectedItem().equals("Error no existen carreras")){
            return false;
        }
        else{
            return EstudianteController.registrarEstudiante(this.campoNombre.getText(),this.campoRut.getText(), this.campoMatricula.getText(),this.campoCarrera.getSelectedItem().toString());
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonRegistrar) {
            try {
                if(registrarEstudiante()) {
                    JOptionPane.showMessageDialog(this,"Estudiante registrado correctamente");
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Ingrese datos validos");
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }
        if (e.getSource() == this.botonCancelar){
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }

    }

}
