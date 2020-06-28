/*package actas.proyectolab2.app.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import actas.proyectolab2.app.miscelaneos.UsuarioPrincipal;
import actas.proyectolab2.app.modelos.Usuario;
import actas.proyectolab2.app.repositorios.RUsuario;

@Service
public class SUserDetailsService implements UserDetailsService {

    @Autowired
    private RUsuario rUsuario;
	
    @Override
     public UsuarioPrincipal loadUserByUsername(String cedula) throws UsernameNotFoundException 
    {
		
     Usuario usuario = rUsuario.findByCedula(cedula).get();
		
     boolean enabled = true;
     boolean accountNonExpired = true;
     boolean credentialsNotExpired = true;
     boolean accountNonLocked = true;
     
     UsuarioPrincipal principal = new UsuarioPrincipal(usuario, enabled, accountNonExpired,
    		 credentialsNotExpired, accountNonLocked,
             getAuthorities(usuario));
     
     return principal;
    }
    
    private List<GrantedAuthority> getAuthorities(Usuario usuario) 
    {
    	List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuario.getRol().getTipo());
        grantList.add(grantedAuthority);
		return grantList;
    }
}
*/