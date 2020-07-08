package actas.proyectolab2.app.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import actas.proyectolab2.app.modelos.Usuario;
import actas.proyectolab2.app.repositorios.RRol;
import actas.proyectolab2.app.repositorios.RUsuario;

@Service
public class SUsuario {

	@Autowired
    RUsuario rUsuario;
	
	@Autowired
	RRol rRol;
     
    public List<Usuario> encontrarTodos()
    {
        List<Usuario> result = (List<Usuario>) rUsuario.findAll();
         
        if(result.size() > 0) 
            return result;
        else
        	return null;
    }
     
    public Usuario encontrarPorCedula(String id)
    {
        Optional<Usuario> usuarioEncontrado = rUsuario.findByCedula(id);
         
        if(usuarioEncontrado.isPresent()) 
            return usuarioEncontrado.get();     
        else     
        	return null;
    }
    
    public Usuario encontrarPorId(Long id)
    {
        Optional<Usuario> usuarioEncontrado = rUsuario.findById(id);
         
        if(usuarioEncontrado.isPresent()) 
            return usuarioEncontrado.get();     
        else     
        	return null;
    }
    
    public Usuario crearOActualizar(Usuario usuario)
    {
        if(usuario.getCedula() == null)
        {
        	usuario.setEstado(true);
            usuario = rUsuario.save(usuario);
            return usuario;
        }
        
        else
        {
            Optional<Usuario> usuarioEncontrado = rUsuario.findByCedula(usuario.getCedula());
             
            if(usuarioEncontrado.isPresent())
            {
                Usuario usuarioActualizado = usuarioEncontrado.get();
                usuarioActualizado.setCedula(usuario.getCedula());
                usuarioActualizado.setContrasenna(usuario.getContrasenna());
                usuarioActualizado.setNombres(usuario.getNombres());
                usuarioActualizado.setApellidos(usuario.getApellidos());
                usuarioActualizado.setEstado(usuario.isEstado());
                usuarioActualizado.setDecanato(usuario.getDecanato());
                usuarioActualizado.setActas(usuarioEncontrado.get().getActas());
                usuarioActualizado.setEstado(true);
                usuarioActualizado = rUsuario.save(usuarioActualizado);
                return usuarioActualizado;
            } 
            
            else 
            {
            	usuario.setEstado(true);
                usuario = rUsuario.save(usuario);
                return usuario;
            }
        }
    }
     
    public void eliminarPorId(Long id)
    {
        Optional<Usuario> usuarioEncontrado = rUsuario.findById(id);
         
        if(usuarioEncontrado.isPresent())
            rUsuario.deleteById(id);
    }
	
}
