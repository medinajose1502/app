package actas.proyectolab2.app.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@AllArgsConstructor
@Getter
@Setter
public class RegistroNoEncontrado extends Exception {
	String mensaje;
}
