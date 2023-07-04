package com.thincrs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "personaBean")
@RequestScoped
public class PersonaBean {
	private List<Persona> personas;

	public void eliminar(int idPersona) {
		PersonaDAO personaDAO = new PersonaDAO();

		personaDAO.eliminarPersona(idPersona);

		personas = (new PersonaDAO()).cargarTodas();
	}

	public List<Persona> getPersonas() {
		if (personas == null) {
			PersonaDAO personaDAO = new PersonaDAO();
			personas = personaDAO.cargarTodas();
		}
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

}
