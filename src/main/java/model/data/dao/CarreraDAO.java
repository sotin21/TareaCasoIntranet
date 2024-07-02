package model.data.dao;

import model.Carrera;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

public class CarreraDAO {
    public static void agregarCarrera(DSLContext query, Carrera carrera){
        Table tablaCarrera= table(name("Carrera"));
        Field[] columnas = tablaCarrera.fields("nombre_carrera","codigo","cantidad_semestres");
        query.insertInto(tablaCarrera, columnas[0], columnas[1],columnas[2])
                .values(carrera.getNombreCarrera(),carrera.getCodigoCarrera(),carrera.getCantidadSemestres())
                .execute();
    }
    public static boolean validarExistenciaCarrera(DSLContext query,String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Carrera")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        if(resultados.size()>=1){
            return true;
        }
        else{
            return false;
        }
    }
    public static Carrera buscarCarrera(DSLContext query, Object dato){
        Result resultados= (Result) buscarCarrera(query,"codigo",dato);
        String codigoCarrera = (String) resultados.getValue(0,"codigo");
        String nombreCarrera = (String) resultados.getValue(0,"nombre_carrera");
        int cantidadSemestres = (int) resultados.getValue(0,"cantidad_semestres");
        return new Carrera(nombreCarrera,codigoCarrera,cantidadSemestres);
    }

    public static List buscarCarrera(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Carrera")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return resultados;
    }
    public static Object[] getCodigoCarreras(DSLContext query){
        Table carrera= DSL.table("Carrera");
        Result resultados = query.select(carrera.field("codigo")).from(carrera).fetch();
        if(resultados.size()==0){
            return new String[]{"Error no existen carreras"};
        }
        else {
            return resultados.getValues("codigo").toArray();
        }
    }

}