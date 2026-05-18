package model;

import java.util.HashMap;

public class Requerimiento {
	private int _cantLideres;
	private int _cantArquitectos;
	private int _cantProgramadores;
	private int _cantTesters;
	private HashMap<String,Integer> _requerimiento; //clave = rol valor = cantidad
	
	public Requerimiento(int cantLideres, int cantArquitectos, int cantProgramadores, int cantTesters) {
		_cantLideres = cantLideres;
		_cantArquitectos = cantArquitectos;
		_cantProgramadores = cantProgramadores;
		_cantTesters = cantTesters;
		_requerimiento = new HashMap<>();
		inicializarRequerimiento();
	}
	
	private void inicializarRequerimiento() {
		_requerimiento.put("Lideres", _cantLideres);
		_requerimiento.put("Arquitectos", _cantArquitectos);
		_requerimiento.put("Programadores", _cantProgramadores);
		_requerimiento.put("Testers", _cantTesters);
	}

	public int getCantLideres() {
		return _requerimiento.get("Lideres");
	}

	public int getCantArquitectos() {
		return _requerimiento.get("Arquitectos");
	}

	public int getCantProgramadores() {
		return _requerimiento.get("Programadores");
	}

	public int get_cantTesters() {
		return _requerimiento.get("Testers");
	}
	
	//esta funcion no se si va a ser necesaria:
	public HashMap<String, Integer> getRequerimiento() {
		return new HashMap<String, Integer>(_requerimiento);
	}
	
	
}
