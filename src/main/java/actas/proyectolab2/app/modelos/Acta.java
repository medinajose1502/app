package actas.proyectolab2.app.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonProperty
	@Column (name = "tipo")
	@NotBlank
	@Size(min = 1, max = 1)
	private char tipo;	
	
	@JsonProperty
	@Column (name = "descripcion")
	@NotBlank(message="Por favor, debe ingresar una descripcion.")
	@Size(min = 1, max = 10000, message="La descripci�n debe contener de 1 a 10000 caract�res.")
	private String descripcion;
	
	@JsonProperty
	@Column (name = "fecha")
	@NotBlank(message="Por favor, debe ingresar la fecha de la sesi�n.")
	private Date fecha;
	
	@JsonProperty
	@Column (name = "estado")
	@NotBlank
	private boolean estado;

	@ManyToOne
    @JoinColumn(name="iddecanato", nullable=false)
    private Decanato decanato;
	
	@ManyToOne
    @JoinColumn(name="idusuario", nullable=false)
    private Usuario usuario;
}
