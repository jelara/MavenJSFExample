package com.thincrs;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIData;
import javax.inject.Named;

@Named(value = "personaBean")
@RequestScoped
public class PersonaBean {
	private List<Persona> personas;
	private UIData tablaPersonas;

	public String eliminar() {
		Persona personaSeleccionada = (Persona) tablaPersonas.getRowData();
		
		PersonaDAO personaDAO = new PersonaDAO();
		
		personaDAO.eliminarPersona(personaSeleccionada.getId());
		
		return "personas";
	}

	public List<Persona> getPersonas() {
		if(personas == null) {
			PersonaDAO personaDAO = new PersonaDAO();
			personas = personaDAO.cargarTodas();
		}
		return personas;
	}

	public UIData getTablaPersonas() {
		return tablaPersonas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	public void setTablaPersonas(UIData tablaPersonas) {
		this.tablaPersonas = tablaPersonas;
	}
}
