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
		Usuario usuario2 = new Usuario();
		Usuario usuario3 = new Usuario();
		Usuario usuario4 = new Usuario();
		Usuario usuario5 = new Usuario();
		Rol rol = new Rol();
		Rol rol2 = new Rol();
		
		decanato.setNombre("DCYT");
		decanato.setDescripcion("DCYT");
		decanato.setUbicacion("Oeste");
		sDecanato.crearOActualizar(decanato);
		
		rol.setTipo("admin");
		Rol admin = sRol.crearOActualizar(rol);
		
		
		rol2.setTipo("secretario");
		Rol secretario = sRol.crearOActualizar(rol2);
		
		/*
		usuario.setCedula("XXXXXXXX");
		usuario.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario.setNombres("Daniel ?");
		usuario.setApellidos("Majano ?");
		usuario.setDecanato(decanato);
		sUsuario.crearOActualizar(usuario);
		*/
		
		usuario2.setCedula(null);
		usuario2.setCedula("25148316");
		usuario2.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario2.setNombres("José David");
		usuario2.setApellidos("Medina Escobar");
		usuario2.setDecanato(decanato);
		usuario2.agregarRol(admin);
		sUsuario.crearOActualizar(usuario2);
		
		usuario3.setCedula(null);
		usuario3.setCedula("25627020");
		usuario3.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario3.setNombres("Rafael Enrique");
		usuario3.setApellidos("Daza Torres");
		usuario3.setDecanato(decanato);
		usuario3.agregarRol(admin);
		sUsuario.crearOActualizar(usuario3);
		
		usuario4.setCedula(null);
		usuario4.setCedula("27290740");
		usuario4.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario4.setNombres("Nicolás Alberto");
		usuario4.setApellidos("Sira Delgado");
		usuario4.setDecanato(decanato);
		usuario4.agregarRol(admin);
		sUsuario.crearOActualizar(usuario4);
		
		usuario5.setCedula(null);
		usuario5.setCedula("12345678");
		usuario5.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario5.setNombres("Dummy User");
		usuario5.setApellidos("McDummyface McUserface");
		usuario5.setDecanato(decanato);
		usuario5.agregarRol(secretario);
		sUsuario.crearOActualizar(usuario5);
	}

}
