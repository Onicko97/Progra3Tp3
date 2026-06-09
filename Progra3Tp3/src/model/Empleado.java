package model;

import java.util.ArrayList;
import java.util.List;

public class Empleado {
	
	private String _nombre;
	private String _rol;
	private int _calificacionHistorica;
	private List<Empleado> incompatibles;
	
	public Empleado(String rol, int calificacion, String nombre) {
		if (calificacion < 1 || calificacion > 5)
	        throw new IllegalArgumentException("Calificacion invalida");
		this._nombre = nombre;
		this._rol = rol;
		this._calificacionHistorica = calificacion;
		this.incompatibles = new ArrayList<Empleado>();
	}
	
	public void agregarIncompatible(Empleado incom) {
		this.incompatibles.add(incom);
	}
	
	public List<Empleado> getIncompatibles() {
		return this.incompatibles;
	}
	
	public String nombre() {
		String nombre = _nombre;
		return nombre;
	}
	
	public String rol() {
		String rol = _rol;
		return rol;
	}
	
	public int calificacion() {
		int calificacion = _calificacionHistorica;
		return calificacion;
	}
	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	public String get_rol() {
		return _rol;
	}

	public void set_rol(String _rol) {
		this._rol = _rol;
	}

	public int get_calificacionHistorica() {
		return _calificacionHistorica;
	}

	public void set_calificacionHistorica(int _calificacionHistorica) {
		this._calificacionHistorica = _calificacionHistorica;
	}
	
	public String nombresIncompatibles() {
		if(this.incompatibles.isEmpty())
			return "";
		StringBuilder string = new StringBuilder();
		for (Empleado empleado : incompatibles) {
			string.append(empleado._nombre);
			string.append(", ");
		}
		string.deleteCharAt(string.length()-1);
		string.deleteCharAt(string.length()-1);
		return string.toString();
	}
	
	@Override
	public String toString() {
		return _nombre;
	}
}
