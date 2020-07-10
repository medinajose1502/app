package actas.proyectolab2.app.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import actas.proyectolab2.app.modelos.Estatus;

public interface REstatus extends JpaRepository<Estatus, Long>{
}
