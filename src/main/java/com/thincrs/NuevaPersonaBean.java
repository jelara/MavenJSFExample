package com.thincrs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.text.ParseException;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class NuevaPersonaBean {
	private String nombre;
	private String apellidos;
	private java.util.Date fechaNacimiento;
	private String genero;
	private String entidadNacimiento;
	private String curp;
	private Part foto;

	public String enviar() throws ParseException {
		PersonaDAO personaDAO = new PersonaDAO();
		
		Persona persona = new Persona(
			0,
			this.nombre,
			this.apellidos,
			new Date(this.fechaNacimiento.getTime()),
			this.genero,
			this.entidadNacimiento,
			this.curp
		);
		
		int idPersona = personaDAO.insertarPersona(persona);
		
		String carpetaFotos = Path.of(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/"), "fotos").toString();
		
		File fileFoto = new File(carpetaFotos, String.valueOf(idPersona));
		
	    try (InputStream input = foto.getInputStream()) {
	        Files.copy(input, fileFoto.toPath());
	    }
	    catch (IOException e) {
	        // Show faces message?
	    }		
		return "personas";
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getCurp() {
		return curp;
	}

	public String getEntidadNacimiento() {
		return entidadNacimiento;
	}

	public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Part getFoto() {
		return foto;
	}

	public String getGenero() {
		return genero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public void setEntidadNacimiento(String entidadNacimiento) {
		this.entidadNacimiento = entidadNacimiento;
	}

	public void setFechaNacimiento(java.util.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setFoto(Part foto) {
		this.foto = foto;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
