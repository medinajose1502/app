package actas.proyectolab2.app.controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import actas.proyectolab2.app.excepciones.RegistroNoEncontrado;
import actas.proyectolab2.app.modelos.Usuario;
import actas.proyectolab2.app.servicios.SUsuario;

@RestController
public class CUsuario {

	@Autowired
	SUsuario sUsuario;
	
	@GetMapping("/usuario/ver/{id}")
	Usuario verUsuario(@PathVariable String id) throws RegistroNoEncontrado
	{
		return sUsuario.encontrarPorCedula(id);
	}
	
	@GetMapping("/usuario/ver/todos")
	List <Usuario> verUsuarios()
	{
		List<Usuario> usuarios = sUsuario.encontrarTodos();
		return usuarios;
	}
	
	@PostMapping("/usuario/guardar")
	Usuario guardarUsuario(@RequestBody Usuario usuario)
	{
		return sUsuario.crearOActualizar(usuario);
	}
	
	@DeleteMapping("/usuario/eliminar/{id}")
	void eliminarUsuario(@PathVariable Long id) throws RegistroNoEncontrado
	{
		sUsuario.eliminarPorId(id);
	}
	
}
