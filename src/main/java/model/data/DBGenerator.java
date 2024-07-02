package model.data;

import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.impl.DSL;
import java.sql.Connection;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;
public class DBGenerator {
    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root","");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create,nombreBD);
        create = actualizarConexion(connection,nombreBD);
        crearTablaCarrera(create);
        crearTablaEstudiante(create);
        relacionarTabla(create,"Estudiante","codigo_carrera","Carrera");
        DBConnector.closeConnection();
    }
    public static DSLContext conectarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombreBD,"root","");
        DSLContext create = DSL.using(connection);
        return create;
    }
    private static void crearBaseDato(DSLContext create, String nombreBD){
        create.createDatabaseIfNotExists(nombreBD).execute();
    }
    //Actualiza la conexion inicial para conectar a la base de datos
    private static DSLContext actualizarConexion(Connection connection,String nombreBD){
        DBConnector.closeConnection();
        connection= DBConnector.connection(nombreBD,"root","");
        DSLContext create=DSL.using(connection);
        return create;
    }
    private static void crearTablaCarrera(DSLContext create){
        create.createTableIfNotExists("Carrera").column("nombre_carrera",VARCHAR(100))
                .column("codigo",VARCHAR(50))
                .column("cantidad_semestres",INTEGER)
                .constraint(primaryKey("codigo")).execute();
    }
    private static void crearTablaEstudiante(DSLContext create){
        create.createTableIfNotExists("Estudiante").column("rut",VARCHAR(50))
                .column("nombre",VARCHAR(100))
                .column("matricula",VARCHAR(50))
                .column("codigo_carrera",VARCHAR(50))
                .constraint(primaryKey("rut")).execute();
    }
    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String
            nombreTablaRelacion){
        create.alterTableIfExists(nombreTabla).alterConstraint(foreignKey(claveForanea).references(nombreTablaRelacion)).enforced();
    }
    private static void agregarColumnaTabla(DSLContext create, String nombreTabla, String columna, DataType
            tipoColumna){
        create.alterTableIfExists(nombreTabla).addColumn(columna,tipoColumna);
    }
}