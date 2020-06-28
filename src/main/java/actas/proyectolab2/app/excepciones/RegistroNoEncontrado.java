package actas.proyectolab2.app.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegistroNoEncontrado extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String mensaje;
}
