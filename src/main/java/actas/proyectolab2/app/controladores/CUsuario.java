package actas.proyectolab2.app.controladores;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import actas.proyectolab2.app.excepciones.MensajeErrorDeCampo;
import actas.proyectolab2.app.excepciones.RecursoNoEncontrado;
import actas.proyectolab2.app.miscelaneos.UsuarioPrincipal;
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
		else throw new RecursoNoEncontrado("No se pudo encontrar el usuario solicitado.");
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/usuario/ver/todos")
	List <Usuario> verUsuarios() throws RecursoNoEncontrado
	{
		List<Usuario> usuarios = sUsuario.encontrarTodos();
		if(usuarios != null)
			return usuarios;
		else throw new RecursoNoEncontrado("No hay registros de usuarios.");
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/usuario/guardar")
	Usuario guardarUsuario(@Valid @RequestBody Usuario usuario)
	{
		return sUsuario.crearOActualizar(usuario);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	List<MensajeErrorDeCampo> manejadorDeExcepcion(MethodArgumentNotValidException e){
		List<FieldError> erroresDeCampo = e.getBindingResult().getFieldErrors();
		List<MensajeErrorDeCampo> mensajesErrorDeCampo = erroresDeCampo.stream().map(errorDeCampo -> new MensajeErrorDeCampo(errorDeCampo.getField(), errorDeCampo.getDefaultMessage())).collect(Collectors.toList());
		return mensajesErrorDeCampo;
	}
	
	@GetMapping("/usuario/sesion")
	Usuario getUsuarioSesion(Authentication auth) throws RecursoNoEncontrado
	{
		Usuario usuario = sUsuario.encontrarPorCedula(((UsuarioPrincipal)auth.getPrincipal()).getUsuario().getCedula());
		if(usuario != null)
			return usuario;
		else throw new RecursoNoEncontrado("No se pudo encontrar el usuario solicitado.");
	}
	
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/usuario/eliminar/{id}")
	void eliminarUsuario(@PathVariable Long id)
	{
		sUsuario.eliminarPorId(id);
	}
	
}
