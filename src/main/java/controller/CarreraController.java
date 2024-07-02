package controller;

import model.Carrera;
import model.data.dao.CarreraDAO;
import model.data.DBConnector;
import model.data.DBGenerator;
import org.jooq.DSLContext;

public class CarreraController {

    private static String nombreDataBase = "Intranet";

    public static boolean añadirCarrera(String nombreCarrera, String codigoCarrera, int cantSemestres) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        if(!CarreraDAO.validarExistenciaCarrera(query,"codigo",codigoCarrera)){
            Carrera carrera = new Carrera(nombreCarrera,codigoCarrera,cantSemestres);
            CarreraDAO.agregarCarrera(query,carrera);
            DBConnector.closeConnection();
            return true;
        }
        else{
            return false;
        }
    }
    public static Object[] getCodigoCarreras() throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        Object[] carreras = CarreraDAO.getCodigoCarreras(query);
        DBConnector.closeConnection();
        return carreras;
    }
}

