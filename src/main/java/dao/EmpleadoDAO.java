package dao;

import conexion.Conexion;
import model.Empleado;
import model.Nomina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    /**
     * Muestra todos los empleados que se encuentren en la base de datos
     * @return
     * @throws SQLException
     */
    public List<Empleado> mostrarEmpleados() throws SQLException {
        List<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados";

        try (Connection connection = obtenerConexion(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado e = new Empleado(rs.getString(2), rs.getString(1), rs.getString(3).charAt(0), rs.getInt(4), rs.getInt(5));
                listaEmpleados.add(e);
            }
        }

        return listaEmpleados;
    }

    /**
     * Obtiene el empleado buscado por dni
     * @param dni
     * @return
     * @throws SQLException
     */
    public Empleado obtenerEmpleado(String dni) throws SQLException {
        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE dni = ?";

        try (Connection connection = obtenerConexion(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(rs.getString(2), rs.getString(1), rs.getString(3).charAt(0), rs.getInt(4), rs.getInt(5));
                }
            }
        }
        return null;
    }

    /**
     * Actualiza los datos del empleado y el sueldo si se editase la categoria o los años trabajados
     * @param e
     * @return
     * @throws SQLException
     */
    public boolean editar(Empleado e) throws SQLException {
        String sqlEmpleado = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
        String sqlNominaUpdate = "UPDATE nominas SET sueldo=? WHERE empleado=?";

        double sueldo = Nomina.sueldo(e);

        try (Connection connection = obtenerConexion()) {
            try {
                connection.setAutoCommit(false);

                int filasEmpleado;
                try (PreparedStatement ps = connection.prepareStatement(sqlEmpleado)) {
                    ps.setString(1, e.getNombre());
                    ps.setString(2, String.valueOf(e.getSexo()));
                    ps.setInt(3, e.getCategoria());
                    ps.setInt(4, e.getAnyos());
                    ps.setString(5, e.getDni());
                    filasEmpleado = ps.executeUpdate();
                }

                if (filasEmpleado == 0) {
                    connection.rollback();
                    return false;
                }

                int filasNomina;
                try (PreparedStatement ps = connection.prepareStatement(sqlNominaUpdate)) {
                    ps.setDouble(1, sueldo);
                    ps.setString(2, e.getDni());
                    filasNomina = ps.executeUpdate();
                }

                connection.commit();
                return true;
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    /**
     * Obtenemos la conexion con la base de datos
     * @return
     * @throws SQLException
     */
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }
}
