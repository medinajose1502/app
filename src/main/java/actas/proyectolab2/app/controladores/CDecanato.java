package actas.proyectolab2.app.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import actas.proyectolab2.app.excepciones.MensajeErrorDeCampo;
import actas.proyectolab2.app.excepciones.RecursoNoEncontrado;
import actas.proyectolab2.app.modelos.Acta;
import actas.proyectolab2.app.modelos.Decanato;
import actas.proyectolab2.app.modelos.ReporteDecanato;
import actas.proyectolab2.app.servicios.SDecanato;

@RestController
public class CDecanato {
	
	@Autowired
	SDecanato sDecanato;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/decanato/ver/{id}")
	Decanato verDecanato(@PathVariable Long id) throws RecursoNoEncontrado
	{
		Decanato decanato = sDecanato.encontrarPorId(id);
		if (decanato != null)
			return decanato;
		else throw new RecursoNoEncontrado("No se pudo encontrar el decanato solicitado.");
					
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/decanato/ver/todos")
	List <Decanato> verDecanatos() throws RecursoNoEncontrado
	{
		List<Decanato> decanatos = sDecanato.encontrarTodos();
		if(decanatos != null)
			return decanatos;
		else throw new RecursoNoEncontrado("No hay registros de decanatos.");
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/decanato/guardar")
	Decanato guardarDecanato(@Valid @RequestBody Decanato decanato)
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
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/decanato/eliminar/{id}")
	void eliminarDecanato(@PathVariable Long id)
	{
		sDecanato.eliminarPorId(id);
	}

	
	@GetMapping
	List<ReporteDecanato> reporteDecanato(@RequestParam int mes) {
		List<ReporteDecanato> Lrd = new ArrayList<>();
		int cuenta = 0;
		
		List<Decanato> decanatos = sDecanato.encontrarTodos();
		for (Decanato decanato : decanatos) {
			for (Acta acta : decanato.getActas()) {
				if(acta.getFecha().getMonthValue() == mes && acta.isEstado())
					++cuenta;
			}
			ReporteDecanato rd = new ReporteDecanato(decanato.getNombre(),cuenta);
			Lrd.add(rd);
			cuenta = 0;
		}
		return Lrd;
	}


}
