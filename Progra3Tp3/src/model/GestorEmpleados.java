package model;

import java.security.InvalidParameterException;
import java.util.HashMap;

public class GestorEmpleados {
	
	HashMap<String, Empleado> _listaEmpleados;		//clave=nombre valor=Empleado
	HashMap<String, String> _listaIncompatibles;	//clave=nombre1 valor=nombre2
	
	public GestorEmpleados() {
		this._listaEmpleados = new HashMap<String, Empleado>();
		this._listaIncompatibles = new HashMap<String, String>();
	}
	
	public HashMap<String, Empleado> getListaEmpleados() {
		return this._listaEmpleados;
	}
	
	public void agregarEmpleado(String nombre, String rol, int calificacion) {
		
		if(nombre == "" || rol == "" || calificacion < 1 || calificacion > 5)
			throw new InvalidParameterException("datos de empleado invalidos");
		
		Empleado emp = new Empleado(rol, calificacion, nombre);
		_listaEmpleados.put(nombre, emp);
	}
	
}
