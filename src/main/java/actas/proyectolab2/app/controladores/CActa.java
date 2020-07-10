package actas.proyectolab2.app.controladores;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import actas.proyectolab2.app.excepciones.MensajeErrorDeCampo;
import actas.proyectolab2.app.excepciones.RecursoNoEncontrado;
import actas.proyectolab2.app.miscelaneos.UsuarioPrincipal;
import actas.proyectolab2.app.modelos.Acta;
import actas.proyectolab2.app.modelos.Decanato;
import actas.proyectolab2.app.modelos.Estatus;
import actas.proyectolab2.app.modelos.Usuario;
import actas.proyectolab2.app.servicios.SActa;
import actas.proyectolab2.app.servicios.SDecanato;
import actas.proyectolab2.app.servicios.SEstatus;
import actas.proyectolab2.app.servicios.SUsuario;

@RestController
public class CActa {
		
		@Autowired
		SActa sActa;
		
		@Autowired
		SUsuario sUsuario;
		
		@Autowired
		SDecanato sDecanato;
		
		@Autowired
		SEstatus sEstatus;
		
		private static final String baseDir = setDirectorio();
		
		static String setDirectorio(){
			String baseDir = "";
			Path currentRelativePath = Paths.get("");
			baseDir = currentRelativePath.toAbsolutePath().toString();
			String so = System.getProperty("os.name").toLowerCase();
			if(so.contains("win"))
				return baseDir+"\\src\\main\\resources\\actas\\";
			else
				return baseDir+"/src/main/resources/actas/";
		}
				
		
		@GetMapping("/acta/ver/{id}")
		Acta verActa(@PathVariable Long id, Authentication auth) throws RecursoNoEncontrado
		{
			Usuario usuario = sUsuario.encontrarPorCedula(((UsuarioPrincipal)auth.getPrincipal()).getUsuario().getCedula());
			Decanato decanato = sDecanato.encontrarPorId(((UsuarioPrincipal)auth.getPrincipal()).getUsuario().getDecanato().getId());
			Acta acta = sActa.encontrarPorId(id);
			
			if(acta != null)
				if (usuario.getRoles().get(0).getTipo().toString() == "ROLE_ADMIN")
					return acta;
				else if(decanato == acta.getDecanato())
					return acta;
				else throw new RecursoNoEncontrado("No se pudo encontrar la acta solicitada");
			else throw new RecursoNoEncontrado("No se pudo encontrar la acta solicitada");
		}
		
		@Secured("ROLE_ADMIN")
		@GetMapping("/acta/ver/todas")
		List <Acta> verActas() throws RecursoNoEncontrado
		{
			List<Acta> actas = sActa.encontrarTodas();
			if(actas != null)
				return actas;
			else throw new RecursoNoEncontrado("No se pudieron encontrar actas registradas");
		}
		
		@GetMapping("/acta/ver/decanato")
		List <Acta> verActasPorDecanato(Authentication auth) throws RecursoNoEncontrado
		{
			Decanato decanato = ((UsuarioPrincipal)auth.getPrincipal()).getUsuario().getDecanato();
			List<Acta> actas = sActa.encontrarPorDecanato(decanato);
			if(actas != null)
				return actas;
			else throw new RecursoNoEncontrado("No se pudieron encontrar actas registradas para el decanato");
		}
		
		@GetMapping("/acta/ver/usuario")
		List <Acta> verActasPorUsuario(Authentication auth) throws RecursoNoEncontrado
		{
			Usuario usuario = ((UsuarioPrincipal)auth.getPrincipal()).getUsuario();
			List<Acta> actas = sActa.encontrarPorUsuario(usuario);
			if(actas != null)
				return actas;
			else throw new RecursoNoEncontrado("No se pudieron encontrar actas registradas para el usuario");
		}
				
		@PostMapping(value = "/acta/guardar", headers = "Content-Type=multipart/form-data")
		public Acta guardarActa(@RequestParam("tipo") char tipo ,@RequestParam("fecha") String fecha,
				@RequestParam("descripcion") String descripcion,@RequestParam(name = "archivo") MultipartFile archivo,
				Authentication auth) 
		{
			Usuario usuario = sUsuario.encontrarPorCedula(((UsuarioPrincipal)auth.getPrincipal()).getUsuario().getCedula());
			Acta acta = new Acta();
			acta.setTipo(tipo);
			acta.setDescripcion(descripcion);
			LocalDate fc = LocalDate.parse(fecha);
			acta.setFecha(fc);
			acta.setUsuario(usuario);
			acta.setDecanato(usuario.getDecanato());
			acta = sActa.crearOActualizar(acta);
			
			Path path = Paths.get(baseDir + acta.getId().toString());
			try {
				Files.copy(archivo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return acta;
		}
		
		
		@PostMapping(value = "/acta/editar", headers = "Content-Type=multipart/form-data")
		Acta editarActa(@RequestParam("id") String id, @RequestParam("tipo") char tipo,
						@RequestParam("fecha") String fecha, @RequestParam("descripcion") String descripcion,
						@RequestParam(name = "archivo") MultipartFile archivo, Authentication auth, @RequestParam("padre") String padre) throws IOException
		{	
			Usuario usuario = sUsuario.encontrarPorCedula(((UsuarioPrincipal)auth.getPrincipal()).getUsuario().getCedula());
			Long idl = Long.parseLong(id);
			Acta acta = sActa.encontrarPorId(idl);
			
			Estatus estatus = sEstatus.encontrarPorId(Long.parseLong(padre));
			
			acta.setTipo(tipo);
			acta.setDescripcion(descripcion);
			LocalDate fc = LocalDate.parse(fecha);
			acta.setFecha(fc);
			acta.setUsuario(usuario);
			acta.setDecanato(usuario.getDecanato());
			
			if(archivo.isEmpty()) {}
			else {
			Path path = Paths.get(baseDir + id);
				try {
					Files.copy(archivo.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			acta.setEstatus(estatus);
			return sActa.crearOActualizar(acta);
		}
		
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		List<MensajeErrorDeCampo> manejadorDeExcepcion(MethodArgumentNotValidException e){
			List<FieldError> erroresDeCampo = e.getBindingResult().getFieldErrors();
			List<MensajeErrorDeCampo> mensajesErrorDeCampo = erroresDeCampo.stream().map(errorDeCampo -> new MensajeErrorDeCampo(errorDeCampo.getField(), errorDeCampo.getDefaultMessage())).collect(Collectors.toList());
			return mensajesErrorDeCampo;
		}
		
		@GetMapping("/acta/descargar/{id}")
	    public ResponseEntity<Resource> downloadFileFromLocal(@PathVariable String id) {
			Acta acta = sActa.encontrarPorId(Long.parseLong(id));
	        Path path = Paths.get(baseDir + id);
	        Resource resource = null;
	        try {
	            resource = new UrlResource(path.toUri());
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }
	        
	        String nombreArchivo = "Acta-Sesion-"+acta.getTipo()+"-"+acta.getFecha().toString()+".pdf";
	        
	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_PDF)
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombreArchivo + "\"")
	                .body(resource);
	    }
		
		@DeleteMapping("/acta/eliminar/{id}")
		void eliminarActas(@PathVariable Long id)
		{
			Path path = Paths.get(baseDir + id.toString());
			try {
				Files.deleteIfExists(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sActa.eliminarPorId(id);
		}
		
		@GetMapping("/acta/estatus/{id}")
		Estatus getEstatusActa(@PathVariable Long id) throws RecursoNoEncontrado
		{
			Acta acta = sActa.encontrarPorId(id);
			return acta.getEstatus();
		}
		
		@GetMapping("/acta/reporte/estatus/{id}")
		List<Acta> getActaEstatus(@PathVariable String id){
			Estatus estatus =  sEstatus.encontrarPorId(Long.parseLong(id));
			return sActa.encontrarPorEstatus(estatus);
		}
}

