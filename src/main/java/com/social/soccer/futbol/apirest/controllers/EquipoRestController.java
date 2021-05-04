package com.social.soccer.futbol.apirest.controllers;


import java.io.IOException;
import java.net.MalformedURLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import com.social.soccer.futbol.apirest.models.services.IEquipoService;
import com.social.soccer.futbol.apirest.models.services.IUploadFileService;

@CrossOrigin(origins= {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class EquipoRestController {
	
	@Autowired
	private IEquipoService equipoService;
	
	@Autowired
	private IUploadFileService uploadService;
		
	
	@GetMapping("/equipos")
	public List<Equipo> index(){
		return equipoService.findAll();
	}
	
	@GetMapping("/equipos/page/{page}")
	public Page<Equipo> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 12);
		return equipoService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/equipos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Equipo equipo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			equipo = equipoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		  
		}
		
		
		if(equipo == null) {
			response.put("mensaje", "El equipo ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Equipo>(equipo, HttpStatus.OK );
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/equipos")
	public ResponseEntity<?> create(@RequestBody Equipo equipo, BindingResult result) {
		
		Equipo equipoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			equipoNew = equipoService.save(equipo);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			

		}
		response.put("mensaje", "El equipo ha sido creado con éxito!");
		response.put("equipo", equipoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/equipos/{id}")
	public ResponseEntity<?> update(@RequestBody Equipo equipo, @PathVariable Long id) {
		
		Equipo equipoActual = equipoService.findById(id);
		
		
		Equipo equipoUpdate = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(equipoActual == null) {
			response.put("mensaje", "Error: nose pudo editar, el equipo ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
		equipoActual.setNombre(equipo.getNombre());
		equipoActual.setCategoria(equipo.getCategoria());
		equipoActual.setDirigente(equipo.getDirigente());
		equipoActual.setCreateAt(equipo.getCreateAt());

		
		equipoUpdate = equipoService.save(equipoActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el equipo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			

		}
		
		response.put("mensaje", "El equipo ha sido actualizado con éxito!");
		response.put("equipo", equipoUpdate);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/equipos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Equipo equipo = equipoService.findById(id);
			String nombreFotoAnterior = equipo.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);

			equipoService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el equipo en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			

		}
		response.put("mensaje", "El equipo fue eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/equipos/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Equipo equipo = equipoService.findById(id);
		
		if(!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del equipo");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			
			String nombreFotoAnterior = equipo.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
			equipo.setFoto(nombreArchivo);
			
			equipoService.save(equipo);
			
			response.put("equipo", equipo);
			response.put("mensaje", "Has subido correctamente la imagen:" + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
		
	}
}
