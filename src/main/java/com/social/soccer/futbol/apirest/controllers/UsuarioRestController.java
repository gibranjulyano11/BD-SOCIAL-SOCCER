package com.social.soccer.futbol.apirest.controllers;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.social.soccer.futbol.apirest.models.entity.Equipo;
import com.social.soccer.futbol.apirest.models.entity.Usuario;
import com.social.soccer.futbol.apirest.models.services.IUploadFileService;
import com.social.soccer.futbol.apirest.models.services.IUsuarioService;

@CrossOrigin(origins= {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	@GetMapping("/usuarios")
	public List<Usuario> index(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuarios/page/{page}")
	public Page<Usuario> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 24);
		return usuarioService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario = usuarioService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		  
		}
		
		
		if(usuario == null) {
			response.put("mensaje", "El usuario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK );
	}
	
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/usuarios")
	public ResponseEntity<?> create(@RequestBody Usuario usuario, BindingResult result) {
		
		Usuario usuarioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuarioNew = usuarioService.save(usuario);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			

		}
		response.put("mensaje", "El usuario ha sido creado con éxito!");
		response.put("usuario", usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {
		
		Usuario usuarioActual = usuarioService.findById(id);
		
		Usuario usuarioUpdate = null;
		
		
		Map<String, Object> response = new HashMap<>();
		
		if(usuarioActual == null) {
			response.put("mensaje", "Error: nose pudo editar, el usuario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setApellido(usuario.getApellido());
			usuarioActual.setEmail(usuario.getEmail());
			usuarioActual.setDni(usuario.getDni());
			usuarioActual.setPassword(usuario.getPassword());
			usuarioActual.setUsername(usuario.getUsername());
			usuarioActual.setCreateAt(usuario.getCreateAt());
			usuarioActual.setEquipo(usuario.getEquipo());
			

		
			usuarioUpdate = usuarioService.save(usuarioActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			

		}
		
		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
		response.put("equipo", usuarioUpdate);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Usuario usuario = usuarioService.findById(id);
			String nombreFotoAnterior = usuario.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);

			usuarioService.delete(id);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			

		}
		response.put("mensaje", "El usuario fue eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/usuarios/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Usuario usuario = usuarioService.findById(id);
		
		if(!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del usuario");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			
			String nombreFotoAnterior = usuario.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
			usuario.setFoto(nombreArchivo);
			
			usuarioService.save(usuario);
			
			response.put("usuario", usuario);
			response.put("mensaje", "Has subido correctamente la imagen:" + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	

	
	@GetMapping("/usuarios/equipos")
	public List<Equipo> listarEquipos(){
		return usuarioService.findAllEquipos();
	}
	

	public IUsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(IUsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public IUploadFileService getUploadService() {
		return uploadService;
	}

	public void setUploadService(IUploadFileService uploadService) {
		this.uploadService = uploadService;
	}
	
	
	
}
