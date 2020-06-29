package actas.proyectolab2.app.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import actas.proyectolab2.app.excepciones.RegistroNoEncontrado;
import actas.proyectolab2.app.modelos.Acta;
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
            return new ArrayList<Acta>();
    }
     
    public Acta encontrarPorId(Long id) throws RegistroNoEncontrado
    {
        Optional<Acta> actaEncontrada = rActa.findById(id);
         
        if(actaEncontrada.isPresent()) 
            return actaEncontrada.get();
        else 
            throw new RegistroNoEncontrado("No actaEncontrada record exist for given id");
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
     
    public void eliminarPorId(Long id) throws RegistroNoEncontrado
    {
        Optional<Acta> actaEncontrada = rActa.findById(id);
         
        if(actaEncontrada.isPresent())
            rActa.deleteById(id);
        else 
            throw new RegistroNoEncontrado("No actaEncontrada record exist for given id");
    }
}