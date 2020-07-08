package actas.proyectolab2.app.modelos;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "acta")
@Setter
@Getter
public class Acta {
	
	@JsonProperty
	@Id
	@Column(name = "codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty
	@Column (name = "tipo")
	private char tipo;	
	
	@JsonProperty
	@Column (name = "descripcion")
	@NotBlank(message="Por favor, debe ingresar una descripcion.")
	@Size(min = 1, max = 10000, message="La descripci�n debe contener de 1 a 10000 caract�res.")
	private String descripcion;
	
	@JsonProperty
	@Column (name = "fecha")
	private LocalDate fecha;
	
	@JsonProperty
	@Column (name = "estado")
	private boolean estado;

	@JsonProperty
	@JsonBackReference(value = "decanato-acta")
	@ManyToOne
    @JoinColumn(name="iddecanato", nullable=false)
    private Decanato decanato;
	
	@JsonProperty
	@JsonBackReference(value = "usuario-acta")
	@ManyToOne
    @JoinColumn(name="idusuario", nullable=false)
    private Usuario usuario;
}
