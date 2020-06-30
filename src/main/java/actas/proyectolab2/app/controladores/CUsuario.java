package actas.proyectolab2.app.controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import actas.proyectolab2.app.excepciones.RecursoNoEncontrado;
import actas.proyectolab2.app.modelos.Usuario;
import actas.proyectolab2.app.servicios.SUsuario;

@RestController
public class CUsuario {

	@Autowired
	SUsuario sUsuario;
	
	@GetMapping("/usuario/ver/{id}")
	Usuario verUsuario(@PathVariable String id) throws RecursoNoEncontrado
	{
		Usuario usuario = sUsuario.encontrarPorCedula(id);
		if(usuario != null)
			return usuario;
		else throw new RecursoNoEncontrado("No se pudiero encontrar Usuario solicitado");
	}
	
	@GetMapping("/usuario/ver/todos")
	List <Usuario> verUsuarios() throws RecursoNoEncontrado
	{
		List<Usuario> usuarios = sUsuario.encontrarTodos();
		if(usuarios != null)
			return usuarios;
		else throw new RecursoNoEncontrado("No se pudieron encontrar Decanatos registrados");
	}
	
	@PostMapping("/usuario/guardar")
	Usuario guardarUsuario(@RequestBody Usuario usuario)
	{
		return sUsuario.crearOActualizar(usuario);
	}
	
	@DeleteMapping("/usuario/eliminar/{id}")
	void eliminarUsuario(@PathVariable Long id)
	{
		sUsuario.eliminarPorId(id);
	}
	
}
