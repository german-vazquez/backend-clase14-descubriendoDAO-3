package patronDAO.entidades;

import patronDAO.daos.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOH2 implements IDao<Estudiante> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/db_estudiantes";
    private final static String DB_USER = "sa";
    private final static String DB_PASS = "";

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        // pasos para guardar un elemento(estudiante) en nuestra db
        // 1 - crear una conexión
        Connection connection; // Connection connection = null -> es redundante...

        try {
            // 2 - levantar la conexión con los drivers de nuestra db
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // 3 - Crear una sentencia
            PreparedStatement preparedStatement; // ...= null; es redundante ...
            preparedStatement = connection.prepareStatement("insert into estudiantes values (?,?,?)");
            preparedStatement.setLong(1, estudiante.getId());
            preparedStatement.setString(2, estudiante.getNombre());
            preparedStatement.setString(3, estudiante.getApellido());

            // 4 - ejecutar la sentencia
            preparedStatement.executeUpdate();
            preparedStatement.close();

            // ante la posibilidad que no se pueda llevar a cabo la conexion, hay que declarar toda la conexion dentro de un try y añadir las posibles excepciones.
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return estudiante;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection; // Connection connection = null -> es redundante...

        try {
            // 2 - levantar la conexión con los drivers de nuestra db
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // 3 - Crear una sentencia
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("delete from estudiantes where id=?");
            preparedStatement.setLong(1, id);


            // 4 - ejecutar la sentencia
            preparedStatement.executeUpdate();
            preparedStatement.close();

            // ante la posibilidad que no se pueda llevar a cabo la conexion, hay que declarar toda la conexion dentro de un try y añadir las posibles excepciones.
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Estudiante buscar(Long id) {
        // pasos para guardar un elemento(estudiante) en nuestra db
        // 1 - crear una conexión
        Connection connection; // Connection connection = null -> es redundante...
        Estudiante estudiante = null; // damos espacio memoria al objeto a buscar...

        try {
            // 2 - levantar la conexión con los drivers de nuestra db
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // 3 - Crear una sentencia
            PreparedStatement preparedStatement; // ...= null; es redundante ...
            preparedStatement = connection.prepareStatement("select * from estudiantes where id = ?");
            preparedStatement.setLong(1, id);

            // 4 - ejecutar la sentencia, (como en este caso la sentencia es una consulta de información, el comando a utilizar es otro)
            ResultSet result = preparedStatement.executeQuery();

            // 5 - evaluar los resultados
            while (result.next()){
                // los resultados de la consulta son datos SQL--> debo expresarlos como variables java para poderlos trabajar.
                Long idEstudiante = result.getLong("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");

                // ahora debo crear el objeto y asignarle los datos obtenidos.
                estudiante = new Estudiante();
                estudiante.setId(idEstudiante);
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
            }
            preparedStatement.close();

            // ante la posibilidad que no se pueda llevar a cabo la conexion, hay que declarar toda la conexion dentro de un try y añadir las posibles excepciones.
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return estudiante;

    }

    @Override
    public List<Estudiante> buscarTodos() {
        // pasos para guardar un elemento(estudiante) en nuestra db
        // 1 - crear una conexión
        Connection connection; // Connection connection = null -> es redundante...
        List estudiantes = new ArrayList<>(); // damos espacio memoria al objeto a buscar...

        try {
            // 2 - levantar la conexión con los drivers de nuestra db
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // 3 - Crear una sentencia
            PreparedStatement preparedStatement; // ...= null; es redundante ...
            preparedStatement = connection.prepareStatement("select * from estudiantes");

            // 4 - ejecutar la sentencia, (como en este caso la sentencia es una consulta de información, el comando a utilizar es otro)
            ResultSet result = preparedStatement.executeQuery();

            // 5 - evaluar los resultados
            while (result.next()){
                // los resultados de la consulta son datos SQL--> debo expresarlos como variables java para poderlos trabajar.
                Long idEstudiante = result.getLong("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");

                // ahora debo crear el objeto estudiante y asignarle los datos obtenidos.
                Estudiante estudiante = new Estudiante();
                estudiante.setId(idEstudiante);
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);

                estudiantes.add(estudiante);
            }
            preparedStatement.close();

            // ante la posibilidad que no se pueda llevar a cabo la conexion, hay que declarar toda la conexion dentro de un try y añadir las posibles excepciones.
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return estudiantes;

    }
}
