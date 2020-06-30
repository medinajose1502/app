package actas.proyectolab2.app;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import actas.proyectolab2.app.modelos.Acta;
import actas.proyectolab2.app.modelos.Decanato;
import actas.proyectolab2.app.modelos.Rol;
import actas.proyectolab2.app.modelos.Usuario;
import actas.proyectolab2.app.servicios.SActa;
import actas.proyectolab2.app.servicios.SDecanato;
import actas.proyectolab2.app.servicios.SRol;
import actas.proyectolab2.app.servicios.SUsuario;

@SpringBootTest
class AgregarUsuario {

	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	SActa sActa = new SActa();
	
	@Autowired
	SDecanato sDecanato = new SDecanato();
	
	@Autowired
	SUsuario sUsuario = new SUsuario();
	
	@Autowired
	SRol sRol = new SRol();

	@Test
	void crearUsuario() {
		
		
		Decanato decanato = new Decanato();
		Decanato decanato2 = new Decanato();
		Acta acta = new Acta();
		Acta acta2 = new Acta();
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
		
		decanato2.setNombre("DEHA");
		decanato2.setDescripcion("DEHA");
		decanato2.setUbicacion("CENTRO");
		sDecanato.crearOActualizar(decanato2);
		
		rol.setTipo("ROLA_ADMIN");
		Rol admin = sRol.crearOActualizar(rol);
		
		
		rol2.setTipo("ROLE_SECRETARIO");
		Rol secretario = sRol.crearOActualizar(rol2);
		
		
		usuario.setCedula("18998003");
		usuario.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario.setNombres("Daniel Adolfo");
		usuario.setApellidos("Majano Manzano");
		usuario.setDecanato(decanato);
		usuario.agregarRol(admin);
		sUsuario.crearOActualizar(usuario);
		
		
		usuario2.setCedula("25148316");
		usuario2.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario2.setNombres("José David");
		usuario2.setApellidos("Medina Escobar");
		usuario2.setDecanato(decanato);
		usuario2.agregarRol(admin);
		sUsuario.crearOActualizar(usuario2);
		
		usuario3.setCedula("25627020");
		usuario3.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario3.setNombres("Rafael Enrique");
		usuario3.setApellidos("Daza Torres");
		usuario3.setDecanato(decanato);
		usuario3.agregarRol(admin);
		sUsuario.crearOActualizar(usuario3);
		
		usuario4.setCedula("27290740");
		usuario4.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario4.setNombres("Nicolás Alberto");
		usuario4.setApellidos("Sira Delgado");
		usuario4.setDecanato(decanato2);
		usuario4.agregarRol(admin);
		sUsuario.crearOActualizar(usuario4);
		
		usuario5.setCedula("12345678");
		usuario5.setContrasenna(bCryptPasswordEncoder.encode("1234"));
		usuario5.setNombres("Dummy User");
		usuario5.setApellidos("McDummyface McUserface");
		usuario5.setDecanato(decanato2);
		usuario5.agregarRol(secretario);
		sUsuario.crearOActualizar(usuario5);
		
		acta.setDecanato(decanato);
		acta.setTipo('E');
		acta.setDescripcion("Acta de la sesión de prueba 1");
		acta.setFecha(LocalDateTime.now());
		acta.setUsuario(usuario2);
		sActa.crearOActualizar(acta);
		
		acta2.setDecanato(decanato2);
		acta2.setTipo('O');
		acta2.setDescripcion("Acta de la sesión de prueba 2");
		acta2.setFecha(LocalDateTime.now());
		acta2.setUsuario(usuario5);
		sActa.crearOActualizar(acta2);
	}

}
