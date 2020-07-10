package actas.proyectolab2.app;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import actas.proyectolab2.app.modelos.Acta;
import actas.proyectolab2.app.modelos.Decanato;
import actas.proyectolab2.app.modelos.Estatus;
import actas.proyectolab2.app.modelos.Rol;
import actas.proyectolab2.app.modelos.Usuario;
import actas.proyectolab2.app.servicios.SActa;
import actas.proyectolab2.app.servicios.SDecanato;
import actas.proyectolab2.app.servicios.SEstatus;
import actas.proyectolab2.app.servicios.SRol;
import actas.proyectolab2.app.servicios.SUsuario;

@SpringBootTest
class AgregarUsuario {
	
	@Autowired
	SActa sActa = new SActa();
	
	@Autowired
	SDecanato sDecanato = new SDecanato();
	
	@Autowired
	SUsuario sUsuario = new SUsuario();
	
	@Autowired
	SRol sRol = new SRol();
	
	@Autowired
	SEstatus sEstatus = new SEstatus();

	@Test
	void crearUsuario() {
		
		
		Decanato decanato = new Decanato();
		Decanato decanato2 = new Decanato();
		Decanato decanato3 = new Decanato();
		Acta acta = new Acta();
		Acta acta2 = new Acta();
		Acta acta3 = new Acta();
		Acta acta4 = new Acta();
		Acta acta5 = new Acta();
		Acta acta6 = new Acta();
		Acta acta7 = new Acta();
		Usuario usuario = new Usuario();
		Usuario usuario2 = new Usuario();
		Usuario usuario3 = new Usuario();
		Usuario usuario4 = new Usuario();
		Usuario usuario5 = new Usuario();
		Usuario usuario6 = new Usuario();
		Usuario usuario7 = new Usuario();
		Rol rol = new Rol();
		Rol rol2 = new Rol();
		Estatus estatus = new Estatus();
		Estatus estatus2 = new Estatus();
		Estatus estatus3 = new Estatus();
		Estatus estatus4 = new Estatus();
		
		estatus.setEstado("Creada");
		sEstatus.crearOActualizar(estatus);
		estatus2.setEstado("En Revisión");
		sEstatus.crearOActualizar(estatus2);
		estatus3.setEstado("Aprobada");
		sEstatus.crearOActualizar(estatus3);
		estatus4.setEstado("Rechazada");
		sEstatus.crearOActualizar(estatus4);
		
		decanato.setNombre("Decanato de Ciencias y Tecnología");
		decanato.setDescripcion("En este decanatos están las carreras relacionadas a la ciencia y la tecnología.");
		decanato.setUbicacion("Oeste, Barquisimeto");
		sDecanato.crearOActualizar(decanato);
		
		decanato2.setNombre("Decanato de Ciencias Veterinarias");
		decanato2.setDescripcion("En este decanato están las carreras relacionadas a la veterinaria.");
		decanato2.setUbicacion("Agua viva, Cabudare");
		sDecanato.crearOActualizar(decanato2);
		
		decanato3.setNombre("Decanato de Ingeniería Civil");
		decanato3.setDescripcion("En este decanato están las carreras relacionadas a la ingeniería civil.");
		decanato3.setUbicacion("Oeste, Barquisimeto");
		sDecanato.crearOActualizar(decanato3);
		
		rol.setTipo("ROLE_ADMIN");
		Rol admin = sRol.crearOActualizar(rol);
		
		
		rol2.setTipo("ROLE_SECRETARIO");
		Rol secretario = sRol.crearOActualizar(rol2);
		
		
		usuario.setCedula("18998003");
		usuario.setContrasenna("1234");
		usuario.setNombres("Daniel Adolfo");
		usuario.setApellidos("Majano Manzano");
		usuario.setDecanato(decanato);
		usuario.agregarRol(admin);
		sUsuario.crearOActualizar(usuario);
		
		
		usuario2.setCedula("25148316");
		usuario2.setContrasenna("1234");
		usuario2.setNombres("José David");
		usuario2.setApellidos("Medina Escobar");
		usuario2.setDecanato(decanato);
		usuario2.agregarRol(admin);
		sUsuario.crearOActualizar(usuario2);
		
		usuario3.setCedula("25627020");
		usuario3.setContrasenna("1234");
		usuario3.setNombres("Rafael Enrique");
		usuario3.setApellidos("Daza Torres");
		usuario3.setDecanato(decanato);
		usuario3.agregarRol(admin);
		sUsuario.crearOActualizar(usuario3);
		
		usuario4.setCedula("27290740");
		usuario4.setContrasenna("1234");
		usuario4.setNombres("Nicolás Alberto");
		usuario4.setApellidos("Sira Delgado");
		usuario4.setDecanato(decanato2);
		usuario4.agregarRol(admin);
		sUsuario.crearOActualizar(usuario4);
		
		usuario5.setCedula("12345678");
		usuario5.setContrasenna("1234");
		usuario5.setNombres("Secretario DCYT");
		usuario5.setApellidos("McDummyface McUserface");
		usuario5.setDecanato(decanato);
		usuario5.agregarRol(secretario);
		sUsuario.crearOActualizar(usuario5);
		
		usuario6.setCedula("11111111");
		usuario6.setContrasenna("1234");
		usuario6.setNombres("Secretario DCV");
		usuario6.setApellidos("McDummyface McUserface");
		usuario6.setDecanato(decanato2);
		usuario6.agregarRol(secretario);
		sUsuario.crearOActualizar(usuario6);
		
		usuario7.setCedula("22222222");
		usuario7.setContrasenna("1234");
		usuario7.setNombres("Secretario DIC");
		usuario7.setApellidos("McDummyface McUserface");
		usuario7.setDecanato(decanato3);
		usuario7.agregarRol(secretario);
		sUsuario.crearOActualizar(usuario7);
		
		acta.setDecanato(decanato);
		acta.setTipo('E');
		acta.setDescripcion("Acta de la sesión de prueba 1");
		acta.setFecha(LocalDate.now());
		acta.setUsuario(usuario5);
		acta.setEstatus(estatus);
		sActa.crearOActualizar(acta);
		
		acta2.setDecanato(decanato2);
		acta2.setTipo('O');
		acta2.setDescripcion("Acta de la sesión de prueba 2");
		acta2.setFecha(LocalDate.now());
		acta2.setUsuario(usuario6);
		acta2.setEstatus(estatus);
		sActa.crearOActualizar(acta2);
		
		acta3.setDecanato(decanato3);
		acta3.setTipo('O');
		acta3.setDescripcion("Acta de la sesión de prueba 3");
		acta3.setFecha(LocalDate.now());
		acta3.setUsuario(usuario7);
		acta3.setEstatus(estatus2);
		sActa.crearOActualizar(acta3);
		
		acta4.setDecanato(decanato2);
		acta4.setTipo('O');
		acta4.setDescripcion("Acta de la sesión de prueba 4");
		acta4.setFecha(LocalDate.now());
		acta4.setUsuario(usuario6);
		acta4.setEstatus(estatus3);
		sActa.crearOActualizar(acta4);
		
		acta5.setDecanato(decanato);
		acta5.setTipo('O');
		acta5.setDescripcion("Acta de la sesión de prueba 5");
		acta5.setFecha(LocalDate.now());
		acta5.setUsuario(usuario5);
		acta5.setEstatus(estatus4);
		sActa.crearOActualizar(acta5);
		
		acta6.setDecanato(decanato);
		acta6.setTipo('O');
		acta6.setDescripcion("Acta DCYT Junio");
		acta6.setFecha(LocalDate.parse("2020-06-12"));
		acta6.setUsuario(usuario5);
		acta6.setEstatus(estatus2);
		sActa.crearOActualizar(acta6);
		
		acta7.setDecanato(decanato2);
		acta7.setTipo('O');
		acta7.setDescripcion("Acta DCV Junio");
		acta7.setFecha(LocalDate.parse("2020-06-12"));
		acta7.setUsuario(usuario6);
		acta7.setEstatus(estatus);
		sActa.crearOActualizar(acta7);
	}

}
