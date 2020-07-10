package actas.proyectolab2.app.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import actas.proyectolab2.app.modelos.Estatus;
import actas.proyectolab2.app.repositorios.REstatus;
 
@Service
public class SEstatus 
{
     
    @Autowired
    REstatus rEstatus;
     
    public List<Estatus> encontrarTodos()
    {
        List<Estatus> result = (List<Estatus>) rEstatus.findAll();
         
        if(result.size() > 0)
            return result;
        else
            return null;
    }
     
    public Estatus encontrarPorId(Long id)
    {
        Optional<Estatus> estatusEncontrado = rEstatus.findById(id);
         
        if(estatusEncontrado.isPresent()) 
            return estatusEncontrado.get();
        else
        	return null;
    }
     
    public Estatus crearOActualizar(Estatus estatus)
    {
        if(estatus.getId() == null)
        {
            estatus = rEstatus.save(estatus);
            return estatus;
        }
        else
        {
            Optional<Estatus> estatusEncontrado = rEstatus.findById(estatus.getId());
             
            if(estatusEncontrado.isPresent())
            {
                Estatus estatusActualizado = estatusEncontrado.get();
                estatusActualizado.setId(estatus.getId());
                estatusActualizado.setEstado(estatus.getEstado());
                estatusActualizado = rEstatus.save(estatusActualizado);
                 
                return estatusActualizado;
            } 
            
            else 
            {
                estatus = rEstatus.save(estatus);
                 
                return estatus;
            }
        }
    }
     
    public void eliminarPorId(Long id)
    {
        Optional<Estatus> estatusEncontrado = rEstatus.findById(id);
         
        if(estatusEncontrado.isPresent())
            rEstatus.deleteById(id);
    }
    
}
