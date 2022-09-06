package patronDAO.servicios;

import patronDAO.daos.IDao;
import patronDAO.entidades.Estudiante;

import java.util.List;

public class EstudianteService {

    private IDao<Estudiante> estudianteIDao;

    public IDao<Estudiante> getEstudianteIDao() {
        return estudianteIDao;
    }

    public void setEstudianteIDao(IDao<Estudiante> estudianteIDao) {
        this.estudianteIDao = estudianteIDao;
    }

    public Estudiante guardarEstudiante(Estudiante e){
        //delega las responsabilidades al dao (estudianteIDao)
        return estudianteIDao.guardar(e);
    }

    public void eliminarEstudiante(Long id){
        //delega las responsabilidades al dao (estudianteIDao)
        estudianteIDao.eliminar(id);
    }

    public Estudiante buscarEstudiante (Long id){
        //delega las responsabilidades al dao (estudianteIDao)
        return estudianteIDao.buscar(id);
    }

    public List<Estudiante> buscarTodos(){
        //delega las responsabilidades al dao (estudianteIDao)
        return estudianteIDao.buscarTodos();
    }


}


