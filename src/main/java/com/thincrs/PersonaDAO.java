package com.thincrs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
	static final String DB_URL = "jdbc:mysql://localhost/mascotas";
	static final String USER = "root";
	static final String PASS = "thincrs2023";
	static final String QUERY_CARGARTODAS = "select * from persona;";
	static final String QUERY_INSERTAR = "insert into persona (nombre, apellidos, fecha_nacimiento, genero, entidad_nacimiento, curp) values (?, ?, ?, ?, ?, ?);";
	static final String QUERY_ELIMINAR = "delete from persona where id = ?;";

	public List<Persona> cargarTodas() {
		try {
			Connection conn = obtenerConexion();
			PreparedStatement sentencia = conn.prepareStatement(QUERY_CARGARTODAS);
			
			ResultSet resultSetPersonas = sentencia.executeQuery();
			
			List<Persona> todasPersonas = new ArrayList<Persona>();
			
			while(resultSetPersonas.next()) {
				todasPersonas.add(construirPersona(resultSetPersonas));
			}
			
			conn.close();
			
			return todasPersonas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Persona construirPersona(ResultSet resultSetPersonas) throws SQLException {
		return new Persona(
			resultSetPersonas.getInt("id"), 
			resultSetPersonas.getString("nombre"),
			resultSetPersonas.getString("apellidos"),
			resultSetPersonas.getDate("fecha_nacimiento"),
			resultSetPersonas.getString("genero"),
			resultSetPersonas.getString("entidad_nacimiento"),
			resultSetPersonas.getString("curp")			
		);
	}
	
	public int insertarPersona(Persona persona) {
		try {
			Connection conn = obtenerConexion();
			PreparedStatement sentencia = conn.prepareStatement(QUERY_INSERTAR, Statement.RETURN_GENERATED_KEYS);
			
			sentencia.setString(1, persona.getNombre());
			sentencia.setString(2, persona.getApellidos());
			sentencia.setDate(3, persona.getFechaNacimiento());
			sentencia.setString(4, persona.getGenero());
			sentencia.setString(5, persona.getEntidadNacimiento());
			sentencia.setString(6, persona.getCurp());
			
			System.out.println(sentencia.toString());
			
			sentencia.execute();
			
			ResultSet generatedKeys = sentencia.getGeneratedKeys();
			generatedKeys.next();
			int idGenerado = generatedKeys.getInt(1);

			conn.close();
			
			return idGenerado;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void eliminarPersona(int id) {
		try {
			Connection conn = obtenerConexion();
			PreparedStatement sentencia = conn.prepareStatement(QUERY_ELIMINAR);
			
			sentencia.setInt(1, id);
			
			System.out.println(sentencia.toString());
			
			sentencia.execute();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection obtenerConexion() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection(DB_URL, USER, PASS);
	}
}
