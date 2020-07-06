package actas.proyectolab2.app.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import actas.proyectolab2.app.modelos.Acta;
import actas.proyectolab2.app.modelos.Decanato;
import actas.proyectolab2.app.modelos.Usuario;
import actas.proyectolab2.app.repositorios.RActa;

@Service
public class SActa {

	@Autowired
    RActa rActa;
     
    public List<Acta> encontrarTodas()
    {
        List<Acta> result = (List<Acta>) rActa.findAll();
        if(result.size() > 0) 
            return result;
        else 
        	return null;
    }
    
    public List<Acta> encontrarPorUsuario(Usuario usuario)
    {
        List<Acta> result = (List<Acta>) rActa.findByUsuario(usuario);
        if(result.size() > 0) 
            return result;
        else 
        	return null;
    }
    
    public List<Acta> encontrarPorDecanato(Decanato decanato)
    {
        List<Acta> result = (List<Acta>) rActa.findByDecanato(decanato);
        if(result.size() > 0) 
            return result;
        else 
        	return null;
    }
     
    public Acta encontrarPorId(Long id)
    {
        Optional<Acta> actaEncontrada = rActa.findById(id);
         
        if(actaEncontrada.isPresent()) 
            return actaEncontrada.get();
        else return null;
        
    }
    
    public Acta crearOActualizar(Acta acta)
    {
        if(acta.getId() == null)
        {
        	acta.setEstado(true);
            acta = rActa.save(acta);
            return acta;
        }
        
        else
        {
            Optional<Acta> actaEncontrada = rActa.findById(acta.getId());
            if(actaEncontrada.isPresent())
            {
                Acta actaActualizada = actaEncontrada.get();
                actaActualizada.setDescripcion(acta.getDescripcion());
                actaActualizada.setFecha(acta.getFecha());
                actaActualizada.setArchivoacta(acta.getArchivoacta());
                actaActualizada = rActa.save(actaActualizada);
                return actaActualizada;
            } 
            
            else 
            {
            	acta.setEstado(true);
                acta = rActa.save(acta);
                return acta;
            }
        }
    }
     
    public void eliminarPorId(Long id)
    {
        Optional<Acta> actaEncontrada = rActa.findById(id);
        
        if(actaEncontrada.isPresent())
            rActa.deleteById(id);
    }
}