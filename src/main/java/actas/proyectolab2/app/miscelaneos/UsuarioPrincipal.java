/*
package actas.proyectolab2.app.miscelaneos;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import actas.proyectolab2.app.modelos.Usuario;

public class UsuarioPrincipal extends User {


	private static final long serialVersionUID = 1L;
		private final Usuario usuario;

	    public UsuarioPrincipal(Usuario usuario, Collection<? extends GrantedAuthority> authorities) 
	    {
	        super(usuario.getCedula(), usuario.getContrasenna(), authorities);
	        this.usuario = usuario;
	    }

	    public UsuarioPrincipal(Usuario mUsuario, boolean enabled, boolean accountNonExpired,
	            boolean credentialsNonExpired,boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) 
	    {
	        super(mUsuario.getCedula(), mUsuario.getContrasenna(),
	                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	        this.usuario = mUsuario;
	    }
	    
	    public Usuario getUsuario() 
	    {
	        return this.usuario;
	    }
}
*/