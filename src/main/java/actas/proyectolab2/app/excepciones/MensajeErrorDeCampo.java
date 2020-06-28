package actas.proyectolab2.app.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MensajeErrorDeCampo {
	
	private String campo;
	private String mensaje;
}
