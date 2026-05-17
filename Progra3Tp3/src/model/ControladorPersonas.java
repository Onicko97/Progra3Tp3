package model;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class ControladorPersonas {
	
	HashMap<String, Persona> _listaPersonas;		//clave=nombre valor=persona
	HashMap<String, String> _listaIncompatibles;	//clave=nombre1 valor=nombre2
	
	public ControladorPersonas() {
		this._listaPersonas = new HashMap<String, Persona>();
		this._listaIncompatibles = new HashMap<String, String>();
	}
	
	public void agregarPersona(String nombre, String rol, int calificacion) {
		
		if(nombre == "" || rol == "" || calificacion < 1 || calificacion > 5)
			throw new InvalidParameterException("datos de persona invalidos");
		
		Persona persona = new Persona(rol, calificacion, nombre);
		_listaPersonas.put(nombre, persona);
	}
	
}
