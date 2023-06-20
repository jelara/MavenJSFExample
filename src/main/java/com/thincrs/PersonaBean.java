package com.thincrs;

import java.util.List;

import javax.inject.Named;

@Named(value = "personaBean")
public class PersonaBean {
	private List<Persona> personas;

	public List<Persona> getPersonas() {
		if(personas == null) {
			PersonaDAO personaDAO = new PersonaDAO();
			personas = personaDAO.cargarTodas();
		}
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
}
