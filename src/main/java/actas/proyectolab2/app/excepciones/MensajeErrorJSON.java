package actas.proyectolab2.app.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MensajeErrorJSON{
	String codigo;
	String mensaje;
}
