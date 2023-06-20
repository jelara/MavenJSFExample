package com.thincrs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
	static final String DB_URL = "jdbc:mysql://localhost/mascotas";
	static final String USER = "root";
	static final String PASS = "thincrs2023";
	static final String QUERY_CARGARTODAS = "select * from persona;";

	public List<Persona> cargarTodas() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conn;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement sentencia = conn.prepareStatement(QUERY_CARGARTODAS);
			
			ResultSet resultSetPersonas = sentencia.executeQuery();
			
			List<Persona> todasPersonas = new ArrayList<Persona>();
			
			while(resultSetPersonas.next()) {
				todasPersonas.add(new Persona(resultSetPersonas.getInt("id"), resultSetPersonas.getString("nombre")));
			}
			
			conn.close();
			
			return todasPersonas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
