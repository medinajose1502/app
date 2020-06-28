package actas.proyectolab2.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import actas.proyectolab2.app.modelos.Decanato;

@Repository
public interface RDecanato extends JpaRepository<Decanato, Long>{

}



