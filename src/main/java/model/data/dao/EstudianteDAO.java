package model.data.dao;

import model.Estudiante;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class EstudianteDAO {
    public static void agregarEstudiante(DSLContext query, Estudiante estudiante){
        Table tablaEstudiante= table(name("Estudiante"));
        Field[] columnas = tablaEstudiante.fields("rut","nombre","matricula","codigo_carrera");
        query.insertInto(tablaEstudiante, columnas[0], columnas[1],columnas[2],columnas[3])
                .values(estudiante.getRut(),estudiante.getNombre(),estudiante.getMatricula(),estudiante.getCarrera().getCodigoCarrera())
                .execute();
    }

    public void modificarEstudiante(DSLContext query, String rut, String columnaTabla, Object dato){
        query.update(DSL.table("Estudiante")).set(DSL.field(columnaTabla),dato).
                where(DSL.field("rut").eq(rut)).execute();
    }

    public List obtenerEstudiante(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Estudiante")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaEstudiantes(resultados);
    }
    public void eliminarEstudiante(DSLContext query, String rut){
        Table tablaEstudiante= table(name("Estudiante"));
        query.delete(DSL.table("Estudiante")).where(DSL.field("rut").eq(rut)).execute();
    }
    public List obtenerCursosEstudiante(DSLContext query){
        Table tablaEstudiante= table(name("Estudiante"));
        Result resultados= query.select().from(DSL.table("Curso"))
                .fetch();
        return obtenerListaEstudiantes(resultados);
    }
    private List obtenerListaEstudiantes(Result resultados){
        List<Estudiante> estudiantes= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            String rut = (String) resultados.getValue(fila,"rut");
            String nombre = (String) resultados.getValue(fila,"nombre");
            String matricula = (String) resultados.getValue(fila,"matricula");
            estudiantes.add(new Estudiante(rut,nombre,matricula,null));
        }
        return estudiantes;
    }
    public static String[][] obtenerEstudiantesCursoCodigo(DSLContext query, String codigo){
        Table estudiante = DSL.table("Estudiante");
        Table carrera = DSL.table("Carrera");
        Result resultados = query.select().from(estudiante).join(carrera).on(DSL.field("codigo").eq(DSL.field("codigo_carrera")))
                .where(DSL.field("codigo_carrera").eq(codigo)).fetch();
        return exportardatos(resultados);
    }
    public static String[][] obtenerEstudiantesCursoNombre(DSLContext query, String nombre, String codigo){
        Table estudiante = DSL.table("Estudiante");
        Table carrera = DSL.table("Carrera");
        Result resultados = query.select().from(estudiante).join(carrera).on(DSL.field("codigo").eq(DSL.field("codigo_carrera")))
                .where(DSL.field("nombre").eq(nombre)).and(DSL.field("codigo_carrera").eq(codigo)).fetch();
        return exportardatos(resultados);
    }

    public static String[][] obtenerEstudiantesCursoRut(DSLContext query, String rut){
        Table estudiante = DSL.table("Estudiante");
        Table carrera = DSL.table("Carrera");
        Result resultados = query.select().from(estudiante).join(carrera).on(DSL.field("codigo").eq(DSL.field("codigo_carrera")))
                .where(DSL.field("rut").eq(rut)).fetch();
        return exportardatos(resultados);
    }

    private static String[][] exportardatos(Result resultados){
        String[][] datosResultado=new String[resultados.size()][4];
        for(int registro = 0; registro < resultados.size(); registro ++){
            datosResultado[registro][0] = (String) resultados.getValue(registro,"nombre");
            datosResultado[registro][1] = (String) resultados.getValue(registro,"matricula");
            datosResultado[registro][2] = (String) resultados.getValue(registro,"nombre_carrera");
            datosResultado[registro][3] = (String) resultados.getValue(registro,"codigo");

        }
        return datosResultado;
    }
    public static boolean validarExistenciaEstudiante(DSLContext query,String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Estudiante")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        if(resultados.size()>=1){
            return true;
        }
        else{
            return false;
        }
    }


}