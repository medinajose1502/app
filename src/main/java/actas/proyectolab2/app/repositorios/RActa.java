package actas.proyectolab2.app.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import actas.proyectolab2.app.modelos.Acta;
import actas.proyectolab2.app.modelos.Decanato;
import actas.proyectolab2.app.modelos.Usuario;

@Repository
public interface RActa extends JpaRepository<Acta, Long>{

	List <Acta> findByUsuario(Usuario usuario);
	List <Acta> findByDecanato(Decanato decanato);
}
