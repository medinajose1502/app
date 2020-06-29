package actas.proyectolab2.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import actas.proyectolab2.app.modelos.Decanato;
import actas.proyectolab2.app.modelos.Rol;
import actas.proyectolab2.app.modelos.Usuario;
import actas.proyectolab2.app.servicios.SDecanato;
import actas.proyectolab2.app.servicios.SRol;
import actas.proyectolab2.app.servicios.SUsuario;

@SpringBootTest
class AgregarUsuario {

	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	SDecanato sDecanato = new SDecanato();
	
	@Autowired
	SUsuario sUsuario = new SUsuario();
	
	@Autowired
	SRol sRol = new SRol();

	@Test
	void crearUsuario() {
		
		
		Decanato decanato = new Decanato();
		
		
		Usuario usuario = new Usuario();
		
		
		Rol rol = new Rol();
		
		decanato.setNombre("DCYT");
		decanato.setDescripcion("DCYT");
		decanato.setUbicacion("Oeste");
		sDecanato.crearOActualizar(decanato);
		
		rol.setTipo("admin");
		usuario.setRol(rol);
		
		usuario.setCedula("25148316");
		usuario.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario.setNombres("José David");
		usuario.setApellidos("Medina Escobar");
		usuario.setDecanato(decanato);
		
		Usuario uReg = sUsuario.crearOActualizar(usuario);
		
		//rol = uReg.getRol();
		//sRol.crearOActualizar(rol);
	}

}
