package actas.proyectolab2.app.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecursoNoEncontrado extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7581530740815622630L;

	String mensaje;
}
