package com.thincrs;

import javax.inject.Named;

@Named(value = "helloBean")
public class HelloBean {
	private String nombre;

	public HelloBean() {
		this.nombre = "Pepe";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
