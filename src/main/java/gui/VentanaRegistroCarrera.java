package gui;

import controller.CarreraController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VentanaRegistroCarrera extends Ventana {
    private JLabel textoEncabezado, textoCodigo, textoNombre, textoCantSemestres;
    private JTextField campoCodigoCarrera, campoNombre;
    private JFormattedTextField campoSemestres;
    private JButton botonRegistrar, botonCancelar;


    public VentanaRegistroCarrera(){
        super("Registro de Carrera", 500, 520);
        generarElementosVentana();
    }
    private void generarElementosVentana() {
        generarEncabezado();
        generarBotonCancelar();
        generarBotonRegistrar();
        generarCampoNombre();
        generarCampoCodigoCarrera();
        generarCampoCantSemestres();
    }
    private void generarEncabezado() {
        String textoCabecera = "Registro de Carrera universitaria";
        super.generarJLabelEncabezado(this.textoEncabezado, textoCabecera, 100, 10, 290, 50);

    }
    private void generarBotonRegistrar() {
        String textoBoton= "Registrar Carrera";
        this.botonRegistrar = super.generarBoton(textoBoton, 75, 400, 150, 20);
        this.add(this.botonRegistrar);
        this.botonRegistrar.addActionListener(this);
    }
    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        this.botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 150, 20);
        this.add(this.botonCancelar);
        this.botonCancelar.addActionListener(this);
    }
    private void generarCampoNombre(){
        String textoNombre= "Nombre carrera:";
        super.generarJLabel(this.textoNombre,textoNombre,20,50,150,20);
        this.campoNombre= super.generarJTextField(200,50,250,20);
        this.add(this.campoNombre);
    }
    private void generarCampoCodigoCarrera(){
        String textoCodigo= "Codigo carrera:";
        super.generarJLabel(this.textoCodigo,textoCodigo,20,100,150,20);
        this.campoCodigoCarrera= super.generarJTextField(200,100,250,20);
        this.add(this.campoCodigoCarrera);
    }
    private void generarCampoCantSemestres(){
        String textoSemestre= "Cantidad de semestres:";
        super.generarJLabel(this.textoCantSemestres,textoSemestre,20,150,150,20);
        this.campoSemestres = super.generarJFormattedTextField(super.generarFormato(0),200,150,250,20);
        this.add(this.campoSemestres);
    }

    private boolean registrarCarrera() throws ClassNotFoundException {
        if(this.campoCodigoCarrera.getText().length()==0 || this.campoNombre.getText().length()==0 || this.campoSemestres.getText().length()==0){
            return false;
        }
        else{
            return CarreraController.añadirCarrera(this.campoNombre.getText(),this.campoCodigoCarrera.getText(),Integer.parseInt(this.campoSemestres.getText()));

        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonRegistrar) {
            try {
                if(registrarCarrera()) {
                    JOptionPane.showMessageDialog(this,"Carrera registrada correctamente");
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Carrera ya ingresada o datos incorrectos");
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
