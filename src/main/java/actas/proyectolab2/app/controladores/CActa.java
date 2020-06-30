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
import actas.proyectolab2.app.modelos.Acta;
import actas.proyectolab2.app.modelos.Decanato;
import actas.proyectolab2.app.modelos.Usuario;
import actas.proyectolab2.app.servicios.SActa;
import actas.proyectolab2.app.servicios.SDecanato;
import actas.proyectolab2.app.servicios.SUsuario;

@RestController
public class CActa {
		
		@Autowired
		SActa sActa;
		
		@Autowired
		SUsuario sUsuario;
		
		@Autowired
		SDecanato sDecanato;
		
		@GetMapping("/acta/ver/{id}")
		Acta verActa(@PathVariable Long id, Authentication auth) throws RecursoNoEncontrado
		{
			Usuario usuario = sUsuario.encontrarPorCedula(((UsuarioPrincipal)auth.getPrincipal()).getUsuario().getCedula());
			Decanato decanato = sDecanato.encontrarPorId(((UsuarioPrincipal)auth.getPrincipal()).getUsuario().getDecanato().getId());
			Acta acta = sActa.encontrarPorId(id);
			
			if(acta != null)
				if (usuario.getRoles().get(0).getTipo().toString() == "a")
					return acta;
				else if(decanato == acta.getDecanato())
					return acta;
				else throw new RecursoNoEncontrado("No se pudo encontrar la acta solicitada");
			else throw new RecursoNoEncontrado("No se pudo encontrar la acta solicitada");
		}
		
		@Secured("ROLE_ADMIN")
		@GetMapping("/acta/ver/todas")
		List <Acta> verActas() throws RecursoNoEncontrado
		{
			List<Acta> actas = sActa.encontrarTodas();
			if(actas != null)
				return actas;
			else throw new RecursoNoEncontrado("No se pudieron encontrar actas registradas");
		}
		
		@GetMapping("/acta/ver/decanato")
		List <Acta> verActasPorDecanato(Authentication auth) throws RecursoNoEncontrado
		{
			Decanato decanato = ((UsuarioPrincipal)auth.getPrincipal()).getUsuario().getDecanato();
			List<Acta> actas = sActa.encontrarPorDecanato(decanato);
			if(actas != null)
				return actas;
			else throw new RecursoNoEncontrado("No se pudieron encontrar actas registradas para el decanato");
		}
		
		@GetMapping("/acta/ver/usuario")
		List <Acta> verActasPorUsuario(Authentication auth) throws RecursoNoEncontrado
		{
			Usuario usuario = ((UsuarioPrincipal)auth.getPrincipal()).getUsuario();
			List<Acta> actas = sActa.encontrarPorUsuario(usuario);
			if(actas != null)
				return actas;
			else throw new RecursoNoEncontrado("No se pudieron encontrar actas registradas para el usuario");
		}
		
		@PostMapping("/acta/guardar")
		Acta guardarActa(@Valid @RequestBody Acta acta)
		{
			return sActa.crearOActualizar(acta);
		}
		
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		List<MensajeErrorDeCampo> manejadorDeExcepcion(MethodArgumentNotValidException e){
			List<FieldError> erroresDeCampo = e.getBindingResult().getFieldErrors();
			List<MensajeErrorDeCampo> mensajesErrorDeCampo = erroresDeCampo.stream().map(errorDeCampo -> new MensajeErrorDeCampo(errorDeCampo.getField(), errorDeCampo.getDefaultMessage())).collect(Collectors.toList());
			return mensajesErrorDeCampo;
		}
		
		@DeleteMapping("/acta/eliminar/{id}")
		void eliminarActas(@PathVariable Long id)
		{
			sActa.eliminarPorId(id);
		}
}

