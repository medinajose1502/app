package actas.proyectolab2.app.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import actas.proyectolab2.app.modelos.Rol;
import actas.proyectolab2.app.repositorios.RRol;
 
@Service
public class SRol 
{
     
    @Autowired
    RRol rRol;
     
    public List<Rol> encontrarTodas()
    {
        List<Rol> result = (List<Rol>) rRol.findAll();
         
        if(result.size() > 0)
            return result;
        else
            return null;
    }
     
    public Rol encontrarPorId(Long id)
    {
        Optional<Rol> rolEncontrado = rRol.findById(id);
         
        if(rolEncontrado.isPresent()) 
            return rolEncontrado.get();
        else
        	return null;
    }
     
    public Rol crearOActualizar(Rol rol)
    {
        if(rol.getId() == null)
        {
            rol = rRol.save(rol);
            return rol;
        }
        else
        {
            Optional<Rol> rolEncontrado = rRol.findById(rol.getId());
             
            if(rolEncontrado.isPresent())
            {
                Rol rolActualizado = rolEncontrado.get();
                rolActualizado.setId(rol.getId());
                rolActualizado.setTipo(rol.getTipo());
                rolActualizado = rRol.save(rolActualizado);
                 
                return rolActualizado;
            } 
            
            else 
            {
                rol = rRol.save(rol);
                 
                return rol;
            }
        }
    }
     
    public void eliminarPorId(Long id)
    {
        Optional<Rol> rolEncontrado = rRol.findById(id);
         
        if(rolEncontrado.isPresent())
            rRol.deleteById(id);
    }
}