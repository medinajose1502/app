package actas.proyectolab2.app.controladores;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import actas.proyectolab2.app.excepciones.RegistroNoEncontrado;
import actas.proyectolab2.app.modelos.Decanato;
import actas.proyectolab2.app.servicios.SDecanato;

@RestController
public class CDecanato {
	
	@Autowired
	SDecanato sDecanato;
	
	@GetMapping("/decanato/ver/{id}")
	Decanato verDecanatos(@PathVariable Long id) throws RegistroNoEncontrado 
	{
		return sDecanato.encontrarPorId(id);
	}
	
	@GetMapping("/decanato/ver/todos")
	List <Decanato> verDecanato()
	{
		List<Decanato> decanatos = sDecanato.encontrarTodos();
		return decanatos;
	}
	
	@PostMapping("/decanato/guardar")
	Decanato guardarDecanato(@RequestBody Decanato decanato)
	{
		return sDecanato.crearOActualizar(decanato);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	List<MensajeErrorDeCampo> manejadorDeExcepcion(MethodArgumentNotValidException e){
		List<FieldError> erroresDeCampo = e.getBindingResult().getFieldErrors();
		List<MensajeErrorDeCampo> mensajesErrorDeCampo = erroresDeCampo.stream().map(errorDeCampo -> new MensajeErrorDeCampo(errorDeCampo.getField(), errorDeCampo.getDefaultMessage())).collect(Collectors.toList());
		return mensajesErrorDeCampo;
	}
	
	@DeleteMapping("/decanato/eliminar/{id}")
	void eliminarDecanato(@PathVariable Long id) throws RegistroNoEncontrado
	{
		sDecanato.eliminarPorId(id);
	}

}
