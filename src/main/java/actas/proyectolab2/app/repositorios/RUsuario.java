package actas.proyectolab2.app.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import actas.proyectolab2.app.modelos.Usuario;

@Repository
public interface RUsuario extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByCedula(String cedula);
}