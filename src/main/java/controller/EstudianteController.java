package controller;

import model.Carrera;
import model.Estudiante;
import model.data.dao.CarreraDAO;
import model.data.DBConnector;
import model.data.DBGenerator;
import model.data.dao.EstudianteDAO;
import org.jooq.DSLContext;

public class EstudianteController {

    private static String nombreDataBase = "Intranet";
    public static boolean registrarEstudiante(String nombre, String rut, String matricula, String codigoCarrera) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        if(!EstudianteDAO.validarExistenciaEstudiante(query,"rut",rut)){
            Carrera carrera =CarreraDAO.buscarCarrera(query,codigoCarrera);
            Estudiante estudiante= new Estudiante(rut,nombre,matricula,carrera);
            EstudianteDAO.agregarEstudiante(query,estudiante);
            DBConnector.closeConnection();
            return true;
        }
        else{
            DBConnector.closeConnection();
            return false;
        }
    }
    public static String[][] mostrarEstudiantesPorCarrera(String codigo) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosEstudiantes= EstudianteDAO.obtenerEstudiantesCursoCodigo(query,codigo);
        DBConnector.closeConnection();
        return datosEstudiantes;
    }
    public static String[][] mostrarEstudiantesPorNombre(String codigo, String nombre) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosEstudiantes = EstudianteDAO.obtenerEstudiantesCursoNombre(query,nombre,codigo);
        DBConnector.closeConnection();
        return datosEstudiantes;
    }

    // mostrarEstudiantesPorRut
    public static String[][] mostrarEstudiantesPorRut(String rut) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD(nombreDataBase);
        String[][] datosEstudiantes = EstudianteDAO.obtenerEstudiantesCursoRut(query,rut);
        DBConnector.closeConnection();
        return datosEstudiantes;
    }
}
