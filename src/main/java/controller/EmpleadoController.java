package controller;

import dao.EmpleadoDAO;
import model.Empleado;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Nomina;

@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = {"/empresa"})
public class EmpleadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Obtiene informacion en funcion de la opcion seleccionada
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("opcion");
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        //listar
        if ("listar".equals(opcion)) {
            try {
                List<Empleado> lista = empleadoDAO.mostrarEmpleados();
                request.setAttribute("empleados", lista);
                request.getRequestDispatcher("/views/listar.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                mostrarError(request, response, "Error al listar los empleados", null);
            }

            //ir al formulario salario
        } else if ("salario".equals(opcion)) {
            request.getRequestDispatcher("/views/formularioSalario.jsp").forward(request, response);

            //mostrar salario
        } else if ("buscarSalario".equals(opcion)) {
            String dni = request.getParameter("dni");
            try {
                Empleado empleado = empleadoDAO.obtenerEmpleado(dni.trim());
                if (empleado != null) {
                    request.setAttribute("empleado", empleado);
                    request.setAttribute("sueldo", Nomina.sueldo(empleado));
                } else {
                    mostrarError(request, response, "No existe ningún empleado con ese DNI", "empresa?opcion=salario");
                }
                request.getRequestDispatcher("/views/salario.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                mostrarError(request, response, "Error al consultar el salario", null);
            }
        } else if ("editar".equals(opcion)) {
            request.getRequestDispatcher("/views/formularioEditar.jsp").forward(request, response);

        } else if ("buscarEditar".equals(opcion)) {
            String dni = request.getParameter("dni");
            try {
                Empleado empleado = empleadoDAO.obtenerEmpleado(dni.trim());
                if (empleado != null) {
                    request.setAttribute("empleado", empleado);
                } else {
                    mostrarError(request, response, "No existe ningún empleado con ese DNI", "empresa?opcion=editar");
                }
                request.getRequestDispatcher("/views/editar.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                mostrarError(request, response, "Error al buscar el empleado", null);
            }
        }
    }

    //editar
    /**
     * Edita el empleado con los datos obtenidos de EmpleadoDAO
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");

        if ("editar".equals(opcion)) {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            Empleado empleado;

            try {
                String dni = request.getParameter("dni").trim();
                String nombre = request.getParameter("nombre").trim();
                String sexo = request.getParameter("sexo").trim();
                int categoria = Integer.parseInt(request.getParameter("categoria"));
                int anyos = Integer.parseInt(request.getParameter("anyos"));

                if (dni.isEmpty() || nombre.isEmpty() || sexo.isEmpty()
                        || categoria < 1 || categoria > 10 || anyos < 0) {
                    throw new IllegalArgumentException("Datos no correctos");
                }
                empleado = new Empleado(nombre, dni, sexo.charAt(0), categoria, anyos);

            } catch (IllegalArgumentException | NullPointerException ex) {
                mostrarError(request, response,
                        "Datos no correctos: la categoría debe estar entre 1 y 10 y los años no pueden ser negativos",
                        "empresa?opcion=editar");
                return;
            }

            try {
                if (empleadoDAO.editar(empleado)) {
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                } else {
                    mostrarError(request, response, "Empleado no encontrado", "empresa?opcion=editar");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                mostrarError(request, response, "Error al guardar los cambios en la base de datos", null);
            }
        } else {
            mostrarError(request, response, "Operación no válida", null);
        }
    }

    /**
     * Muestra el error en funcion a los parametros que se le establezcan
     * @param request
     * @param response
     * @param mensaje
     * @param volver
     * @throws ServletException
     * @throws IOException
     */
    private void mostrarError(HttpServletRequest request, HttpServletResponse response,
                              String mensaje, String volver) throws ServletException, IOException {
        request.setAttribute("error", mensaje);
        request.setAttribute("volver", volver);
        request.getRequestDispatcher("/views/error.jsp").forward(request, response);
    }
}
