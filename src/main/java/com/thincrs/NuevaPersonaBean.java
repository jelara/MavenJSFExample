package com.thincrs;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class NuevaPersonaBean {
	private String nombre;
	private String apellidos;
	private String fechaNacimiento;
	private String genero;
	private String entidadNacimiento;
	private String curp;

	public String getApellidos() {
		return apellidos;
	}

	public String getCurp() {
		return curp;
	}

	public String getEntidadNacimiento() {
		return entidadNacimiento;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
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

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String enviar() throws ParseException {
		PersonaDAO personaDAO = new PersonaDAO();
		
		Persona persona = new Persona(
			0,
			this.nombre,
			this.apellidos,
			new Date(DateFormat.getDateInstance(DateFormat.SHORT).parse(this.fechaNacimiento).getTime()),
			this.genero,
			this.entidadNacimiento,
			this.curp
		);
		
		personaDAO.insertarPersona(persona);
		
		return "personas";
	}

}
