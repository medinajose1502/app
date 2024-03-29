package actas.proyectolab2.app.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import actas.proyectolab2.app.modelos.Decanato;
import actas.proyectolab2.app.repositorios.RDecanato;

@Service
public class SDecanato {

	@Autowired
    RDecanato rDecanato;
     
    public List<Decanato> encontrarTodos()
    {
        List<Decanato> result = (List<Decanato>) rDecanato.findAll();
        if(result.size() > 0) 
            return result;
        else 
            return null;
    }
    
    public List<Decanato> encontrarTodosActivos()
    {
        List<Decanato> result = (List<Decanato>) rDecanato.findByEstado(true);
        if(result.size() > 0) 
            return result;
        else 
            return null;
    }
     
    public Decanato encontrarPorId(Long id)
    {
        Optional<Decanato> decanatoEncontrado = rDecanato.findById(id);
         
        if(decanatoEncontrado.isPresent()) 
            return decanatoEncontrado.get();
        else 
            return null;
    }
    
    public Decanato crearOActualizar(Decanato decanato)
    {
        if(decanato.getId() == null)
        {
        	decanato.setEstado(true);
            decanato = rDecanato.save(decanato);
            return decanato;
        }
        
        else
        {
            Optional<Decanato> decanatoEncontrado = rDecanato.findById(decanato.getId());
             
            if(decanatoEncontrado.isPresent())
            {
                Decanato decanatoActualizado = decanatoEncontrado.get();
                decanatoActualizado.setNombre(decanato.getNombre());
                decanatoActualizado.setDescripcion(decanato.getDescripcion());
                decanatoActualizado.setUbicacion(decanato.getUbicacion());
                decanatoActualizado.setEstado(decanato.isEstado());
                decanatoActualizado = rDecanato.save(decanatoActualizado);
                return decanatoActualizado;
            } 
            
            else 
            {
            	decanato.setEstado(true);
                decanato = rDecanato.save(decanato);
                return decanato;
            }
        }
    }
     
    public void eliminarPorId(Long id)
    {
        Optional<Decanato> decanatoEncontrado = rDecanato.findById(id);
         
        if(decanatoEncontrado.isPresent())    
        	rDecanato.delete(decanatoEncontrado.get());
    }
	
}
