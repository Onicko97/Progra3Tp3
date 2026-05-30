package model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Requerimiento {
	private int _cantLideres;
	private int _cantArquitectos;
	private int _cantProgramadores;
	private int _cantTesters;
	private HashMap<String,Integer> _requerimiento; //clave = rol valor = cantidad
	
	public Requerimiento(int cantLideres, int cantArquitectos, int cantProgramadores, int cantTesters) {
		
		if(!validacion(cantLideres, cantArquitectos, cantProgramadores, cantTesters))
			throw new InvalidParameterException("Requerimientos invalidos");
		
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
	
	private boolean validacion(int cantLideres, int cantArquitectos, int cantProgramadores, int cantTesters) {
		if(cantLideres < 0 || cantArquitectos < 0 || cantProgramadores < 0 || cantTesters < 0)
			return false;
		return true;
	}
	
	public List<String> getStringsRequerimientos() {
		List<String> requerimientos = new ArrayList<String>();
		requerimientos.add(Integer.toString(_cantLideres));
		requerimientos.add(Integer.toString(_cantArquitectos));
		requerimientos.add(Integer.toString(_cantProgramadores));
		requerimientos.add(Integer.toString(_cantTesters));
		return requerimientos;
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
