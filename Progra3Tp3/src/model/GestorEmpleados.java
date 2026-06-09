package model;

import java.util.List;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorEmpleados {
	
	HashMap<String, Empleado> _listaEmpleados;		//clave=nombre valor=Empleado
	//HashMap<String, String> _listaIncompatibles;	//clave=nombre1 valor=nombre2
	Requerimiento requerimientos;
	private MejorCalificado algoritmoBacktracking = new MejorCalificado();
	
	public GestorEmpleados() {
		this._listaEmpleados = new HashMap<String, Empleado>();
		//this._listaIncompatibles = new HashMap<String, String>();
	}
	
	public HashMap<String, Empleado> getListaEmpleados() {
		return this._listaEmpleados;
	}
	
	public Empleado buscarEmpleadoPorNombre(String nombre) {
		return _listaEmpleados.get(nombre);
	}
	
	public void agregarEmpleado(String nombre, String rol, int calificacion) {
		
		if(nombre == "" || nombre == null || rol == "" || rol == null || rol == "Seleccione un rol" || calificacion < 1 || calificacion > 5)
			throw new InvalidParameterException("datos de empleado invalidos");
		
		Empleado emp = new Empleado(rol, calificacion, nombre);
		_listaEmpleados.put(nombre, emp);
	}

	
	public void settearRequerimientos(int cantLideres, int cantArquitectos, int cantProgramadores, int cantTesters) {
		requerimientos = new Requerimiento(cantLideres, cantArquitectos, cantProgramadores, cantTesters);
	}
	
	public List<String> getStringsRequerimientos(){
		return requerimientos.getStringsRequerimientos();
	}
	
	public List<Empleado> getIncompatibles(String nombreEmpleado){
		return _listaEmpleados.get(nombreEmpleado).getIncompatibles();
	}
	
	public List<Empleado> ejecutarBacktracking(List<Empleado> empleados, int lid, int arq, int prog, int test) {
	    return algoritmoBacktracking.resolver(empleados, lid, arq, prog, test);
	}
	public List<String> getEstadisticas() {
	    List<String> estadisticas = new ArrayList<>();
	    estadisticas.add(String.valueOf(algoritmoBacktracking.getCantidadCasoBase()));
	    estadisticas.add(String.valueOf(algoritmoBacktracking.getCantidadEquiposValidos()));
	    estadisticas.add(String.valueOf(algoritmoBacktracking.getCantidadEquiposInvalidos()));
	    estadisticas.add(String.valueOf(algoritmoBacktracking.getTiempoTotal()));
	    return estadisticas;
	}
	
}
