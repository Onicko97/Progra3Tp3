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
}
