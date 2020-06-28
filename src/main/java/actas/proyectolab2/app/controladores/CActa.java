package actas.proyectolab2.app.controladores;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import actas.proyectolab2.app.modelos.Acta;
import actas.proyectolab2.app.servicios.SActa;

@RestController
public class CActa {
		
		@Autowired
		SActa sActa;
		
		@GetMapping("/acta/ver/{id}")
		Acta verActa(@PathVariable Long id) throws RegistroNoEncontrado
		{
			return sActa.encontrarPorId(id);
		}
		
		@GetMapping("/acta/ver/todas")
		List <Acta> verActas()
		{
			List<Acta> actas = sActa.encontrarTodas();
			return actas;
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
		void eliminarActas(@PathVariable Long id) throws RegistroNoEncontrado
		{
			sActa.eliminarPorId(id);
		}
}

