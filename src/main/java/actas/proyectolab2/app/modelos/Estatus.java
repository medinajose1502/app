package actas.proyectolab2.app.modelos;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estatus")
@Setter
@Getter
public class Estatus {
	
	@JsonProperty
	@Id
	@Column(name = "idestatus")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty
	@Column (name = "estado")
	private String estado;	
	
	@JsonManagedReference(value = "estatus-acta")
	@OneToMany(mappedBy="decanato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Acta> actas = new ArrayList<Acta>();
}