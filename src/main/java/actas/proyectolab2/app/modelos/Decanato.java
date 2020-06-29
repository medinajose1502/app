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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "decanato")
@Getter
@Setter
public class Decanato {

	@JsonProperty
	@Id
	@Column (name = "id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonProperty
	@Column (name = "nombre")
	@NotBlank(message="Por favor, debe ingresar un nombre para el decanato.")
	@Size(min = 1, max = 60, message="El nombre del decanato debe contener de 1 a 50 caractéres.")
	private String nombre;
	
	@JsonProperty
	@Column (name = "descripcion")
	@NotBlank(message="Por favor, debe ingresar una descripcion para el decanato.")
	@Size(min = 1, max = 300, message="La descripción del decanato debe contener de 1 a 300 caractéres.")
	private String descripcion;
	
	@JsonProperty
	@Column (name = "ubicacion")
	@NotBlank(message="Por favor, debe ingresar la ubicaci�n del decanato.")
	@Size(min = 1, max = 60, message="La ubicación del decanato debe contener de 1 a 100 caractéres.")
	private String ubicacion;
	
	@JsonProperty
	@Column (name = "estado")
	private boolean estado;
	
	@JsonManagedReference
	@OneToMany(mappedBy="decanato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@JsonManagedReference
	@OneToMany(mappedBy="decanato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Acta> actas = new ArrayList<Acta>();
}
