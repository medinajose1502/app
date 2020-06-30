package actas.proyectolab2.app.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ManejadorDeErrores404 {

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler
	MensajeErrorJSON manejadorDeExcepcion(RecursoNoEncontrado r)
	{
		return new MensajeErrorJSON("404", r.getMensaje());
	}
	
	
}
