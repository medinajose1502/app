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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "usuario")
@Getter
@Setter
public class Usuario {

	@JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "usuario_id")
	private Long id;
	
	@JsonProperty
	@Column (name = "cedula")
	@NotBlank(message = "La cédula no puede estar vacía.")
	@Size(min = 4, max = 20, message = "La cédula debe tener de 7 a 10 digitos.")
	private String cedula;
	
	@JsonProperty
	@Column (name = "contrasenna")
	@NotBlank
	@Size(min = 4, message = "La contraseña debe tener al menos cuatro caractéres.")
	private String contrasenna;
	
	@JsonProperty
	@Column (name = "nombres")
	@NotBlank(message="Por favor, ingrese los nombres(s) del usuario a registrar.")
	@Size(min = 1, max = 50, message="Nombre(s) del usuario a registrar debe(n) contener de 1 a 50 caractéres.")
	private String nombres;

	@JsonProperty
	@Column (name = "apellidos")
	@NotBlank(message="Por favor, ingrese los apellidos(s) del usuario a registrar.")
	@Size(min = 1, max = 50, message="Nombre(s) del usuario a registrar debe(n) contener de 1 a 50 caractéres.")
	private String apellidos;
	
	@JsonProperty
	@Column(name = "estado")
	private boolean estado;
	
	@JsonBackReference(value = "decanato-usuario")
	@ManyToOne
    @JoinColumn(name="iddecanato", nullable=false)
    private Decanato decanato;
	
	@JsonManagedReference(value = "usuario-acta")
	@JsonIgnoreProperties("archivo")
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL)
    private List<Acta> actas = new ArrayList<Acta>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="roles_usuarios",
	joinColumns=@JoinColumn(name="usuario_id"),
	inverseJoinColumns=@JoinColumn(name="rol_id"))
    private List<Rol> roles = new ArrayList<>();
	
	public void agregarRol(Rol rol) {
		this.roles.add(rol);
	}
}
