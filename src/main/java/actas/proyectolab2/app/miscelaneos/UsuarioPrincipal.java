package actas.proyectolab2.app.miscelaneos;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import actas.proyectolab2.app.modelos.Usuario;

@SuppressWarnings("serial")
public class UsuarioPrincipal extends User {

		private final Usuario usuario;

	    public UsuarioPrincipal(Usuario usuario, Collection<? extends GrantedAuthority> authorities) 
	    {
	        super(usuario.getCedula(), usuario.getContrasenna(), authorities);
	        this.usuario = usuario;
	    }

	    public UsuarioPrincipal(Usuario usuario, boolean enabled, boolean accountNonExpired,
	            boolean credentialsNonExpired,boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) 
	    {
	        super(usuario.getCedula(), usuario.getContrasenna(),
	                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	        this.usuario = usuario;
	    }
	    
	    public Usuario getUsuario() 
	    {
	        return this.usuario;
	    }
	    
}