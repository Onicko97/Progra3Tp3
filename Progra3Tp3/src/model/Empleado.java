package model;

public class Empleado {
	
	private String _nombre;
	private String _rol;
	private int _calificacionHistorica;
	
	public Empleado(String rol, int calificacion, String nombre) {
		this._nombre = nombre;
		this._rol = rol;
		this._calificacionHistorica = calificacion;
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
	
}
