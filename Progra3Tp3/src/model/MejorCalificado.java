package model;

import java.util.ArrayList;
import java.util.List;

public class MejorCalificado {
	private List<Empleado> lideres;
	private List<Empleado> arquitectos;
	private List<Empleado> testers;
	private List<Empleado> programadores;
	
	private int lideresRequeridos;
	private int arquitectosRequeridos;
	private int testersRequeridos;
	private int programadoresRequeridos;
	private int equipoTotal;
	
	
	public MejorCalificado(int lideresRequeridos, int arquitectosRequeridos, int testersRequeridos, int programadoresRequeridos ) {
		this.lideres = new ArrayList<Empleado>();
		this.arquitectos = new ArrayList<Empleado>();
		this.testers = new ArrayList<Empleado>();
		this.programadores = new ArrayList<Empleado>();
		this.lideresRequeridos = lideresRequeridos;
		this.arquitectosRequeridos = arquitectosRequeridos;
		this.testersRequeridos = testersRequeridos;
		this.programadoresRequeridos = programadoresRequeridos;
		this.equipoTotal = lideresRequeridos + arquitectosRequeridos + testersRequeridos + programadoresRequeridos;
		}

	public List<Empleado> resolver(List<Empleado> empleados) {
		separarEmpleadosPorRol(empleados);
		List<List<Empleado>> conjuntos = obtenerConjuntosSegunRequerimientos();
		List<Empleado> equipoMasCalificado = buscarMejorEquipo(conjuntos);
		return equipoMasCalificado;
	}
	
	public List<List<Empleado>> obtenerCombinaciones(List<Empleado> empleadosMismoRol, int requeridos) {
		List<List<Empleado>> resultado = new ArrayList<>();
		List<Empleado> vacio = new ArrayList<>();
		int inicio = 0;
		backtrack(inicio, resultado, empleadosMismoRol ,vacio, requeridos);
		return resultado;
	}
	
	public List<List<Empleado>> backtrack(int inicio, List<List<Empleado>> empleadosComb, List<Empleado> empleados, List<Empleado> comb, int requeridos ) {
		if (comb.size() == requeridos ) {
			empleadosComb.add(comb);
			return null;
		}
		int limite = empleados.size() - (requeridos - comb.size());
		for (int i = inicio; i <= limite; i++) {
			comb.add(empleados.get(i));
			backtrack(i + 1, empleadosComb, empleados, comb, requeridos );
			comb.remove(comb.size() - 1);
		}
		return empleadosComb;
	}
	
	public List<Empleado> buscarMejorEquipo(List<List<Empleado>> conjuntos) {
		List<Empleado> primero = conjuntos.get(0); 
		//buscarMejorEquipo(primero, )
		return null;
	}
	
	//public 
	
	
	public void separarEmpleadosPorRol(List<Empleado> empleados) {
		for(Empleado e: empleados) {
			switch(e.get_rol()) {
			case "tester":
				testers.add(e);
				break;
			case "programador":
				programadores.add(e);
				break;
			case "arquitecto":
				arquitectos.add(e);
				break;
			case "lider":
				lideres.add(e);
				break;
			default:
				break;
			}
		}
	}
	
	
	public List<List<Empleado>> obtenerConjuntosSegunRequerimientos() {
		List<Empleado> conjunto = new ArrayList<Empleado>();
		
		return null;
	}
}
