package patronDAO;

import patronDAO.entidades.Estudiante;
import patronDAO.entidades.EstudianteDAOH2;
import patronDAO.servicios.EstudianteService;

public class Main {
    public static void main(String[] args) {
        Estudiante estudiante = new Estudiante();

        estudiante.setId(1L);
        estudiante.setNombre("cosme");
        estudiante.setApellido("fulanito");

        EstudianteService estudianteService = new EstudianteService();
        //seteamos una estrategia de persistencia, es decir un DAO
        estudianteService.setEstudianteIDao(new EstudianteDAOH2());

        estudianteService.guardarEstudiante(estudiante);
    }
}
