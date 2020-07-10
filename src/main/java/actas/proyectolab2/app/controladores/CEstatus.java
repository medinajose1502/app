package actas.proyectolab2.app.controladores;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import actas.proyectolab2.app.excepciones.RecursoNoEncontrado;
import actas.proyectolab2.app.modelos.Estatus;
import actas.proyectolab2.app.servicios.SEstatus;

@RestController
public class CEstatus {
	
	@Autowired
	SEstatus sEstatus;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/estatus/ver/{id}")
	Estatus verDecanato(@PathVariable Long id) throws RecursoNoEncontrado
	{
		Estatus estatus = sEstatus.encontrarPorId(id);
		if (estatus != null)
			return estatus;
		else throw new RecursoNoEncontrado("No se pudo encontrar el estatus solicitado.");
					
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/estatus/ver/todos")
	List <Estatus> verDecanatos() throws RecursoNoEncontrado
	{
		List<Estatus> estatuses = sEstatus.encontrarTodos();
		if(estatuses != null)
			return estatuses;
		else throw new RecursoNoEncontrado("No hay registros de estatuses.");
	}
		
	@Secured("ROLE_ADMIN")
	@PostMapping("/estatus/guardar")
	Estatus guardarDecanato(@Valid @RequestBody Estatus estatus)
	{
		return sEstatus.crearOActualizar(estatus);
	}
		
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/estatus/eliminar/{id}")
	void eliminarEstatus(@PathVariable Long id)
	{
		sEstatus.eliminarPorId(id);
	}

}
