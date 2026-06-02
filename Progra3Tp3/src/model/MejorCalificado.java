package model;

import java.util.ArrayList;
import java.util.List;

public class MejorCalificado {
	private List<Empleado> lideres;
	private List<Empleado> arquitectos;
	private List<Empleado> testers;
	private List<Empleado> programadores;
	
//	private int lideresRequeridos;
//	private int arquitectosRequeridos;
//	private int testersRequeridos;
//	private int programadoresRequeridos;
//	private int equipoTotal;
	
	
	public MejorCalificado() {
		this.lideres = new ArrayList<Empleado>();
		this.arquitectos = new ArrayList<Empleado>();
		this.testers = new ArrayList<Empleado>();
		this.programadores = new ArrayList<Empleado>();
//		this.lideresRequeridos = lideresRequeridos;
//		this.arquitectosRequeridos = arquitectosRequeridos;
//		this.testersRequeridos = testersRequeridos;
//		this.programadoresRequeridos = programadoresRequeridos;
//		this.equipoTotal = lideresRequeridos + arquitectosRequeridos + testersRequeridos + programadoresRequeridos;
		}

	public List<Empleado> resolver(List<Empleado> empleados,int reqLid, int reqArq, int reqProg, int reqTest) {
		separarEmpleadosPorRol(empleados);
		List<List<Empleado>> conjuntos = obtenerConjuntosSegunRequerimientos(reqLid, reqArq, reqProg, reqTest);
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
			empleadosComb.add(new ArrayList<>(comb)); //lo clone para que no se borre
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
		
	    int mejorCalificacionTotal = 0;
	    List<Empleado> mejor = conjuntos.get(0); 
	    
	    for(List<Empleado> equipo : conjuntos) {
	        int calificacion = 0;
	        for(Empleado emp : equipo) {
	            calificacion += emp.get_calificacionHistorica();
	        }
	        if(calificacion > mejorCalificacionTotal) {
	            mejor = equipo;
	            mejorCalificacionTotal = calificacion;
	        }
	    }
	    return mejor;
	}	
	
	public void separarEmpleadosPorRol(List<Empleado> empleados) {
	    for(Empleado e: empleados) {
	    	
	    	switch(e.get_rol()) {
	            case "Tester":
	                testers.add(e);
	                break;
	            case "Programador":
	                programadores.add(e);
	                break;
	            case "Arquitecto":
	                arquitectos.add(e);
	                break;
	            case "Lider de proyecto": 
	                lideres.add(e);
	                break;
	            
	        }
	    }
	}
	
	
	public List<List<Empleado>> obtenerConjuntosSegunRequerimientos(int cantLideres,int cantArquitectos, int cantProgramadores, int cantTesters) {
		//List<List<Empleado>> posiblesLideres = new ArrayList<List<Empleado>>();
		//combinacionesLideres(posiblesLideres, new ArrayList<Empleado>(), 2, 0); //hay que cambiar la cantidad de lideres por lo que pide requerimientos
		
		List<List<Empleado>> posiblesLideres = obtenerCombinaciones(lideres, cantLideres);
	    List<List<Empleado>> posiblesArquitectos = obtenerCombinaciones(arquitectos, cantArquitectos);
	    List<List<Empleado>> posiblesProgramadores = obtenerCombinaciones(programadores, cantProgramadores);
	    List<List<Empleado>> posiblesTesters = obtenerCombinaciones(testers, cantTesters);
		
	    List<List<Empleado>> todosLosEquiposValidos = new ArrayList<>();
	    //aca se ven todos los equipos posibles
	    for (List<Empleado> cLid : posiblesLideres) {
	        for (List<Empleado> cArq : posiblesArquitectos) {
	            for (List<Empleado> cProg : posiblesProgramadores) {
	                for (List<Empleado> cTest : posiblesTesters) {
	                    
	                    // y aca fusiono las combinaciones
	                    List<Empleado> equipoCandidato = new ArrayList<>();
	                    equipoCandidato.addAll(cLid);
	                    equipoCandidato.addAll(cArq);
	                    equipoCandidato.addAll(cProg);
	                    equipoCandidato.addAll(cTest);
	                    
	                    // si ninguno se odia entre sí, pasa la prueba
	                    if (esEquipoValido(equipoCandidato)) {
	                        todosLosEquiposValidos.add(equipoCandidato);
	                    }
	                }
	            }
	        }
	    }
	    return todosLosEquiposValidos;
	
	}
	private boolean esEquipoValido(List<Empleado> equipo) {
	   //recorro los empleados y me fijo si algunos son incompatibles
		for (int i = 0; i < equipo.size(); i++) {
	        Empleado emple1 = equipo.get(i);
	        for (int j = i + 1; j < equipo.size(); j++) {
	            Empleado emple2 = equipo.get(j);

	            if (emple1.getIncompatibles().contains(emple2)) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
	private List<List<Empleado>> combinacionesLideres(List<List<Empleado>> posiblesLideres, List<Empleado> temporal
														, int cantLideres, int inicio){
		
		// Caso base: si la combinacion tiene el tamanio correcto K
        if (temporal.size() == cantLideres) {
            posiblesLideres.add(new ArrayList<>(temporal));
            return posiblesLideres;
        }

        // Iterar y construir combinaciones
        for (int i = inicio; i < lideres.size(); i++) {
            temporal.add(lideres.get(i));
            posiblesLideres = combinacionesLideres(posiblesLideres, temporal, cantLideres, i + 1);
            temporal.remove(temporal.size() - 1); // Backtracking
        }
		
		return posiblesLideres;
	}
	
//	para testear
//	public void run() {
//		lideres.add(new Empleado("Lider de proyecto", 0, "Juan"));
//		lideres.add(new Empleado("Lider de proyecto", 0, "Carlos"));
//		lideres.add(new Empleado("Lider de proyecto", 0, "Manuel"));
//		lideres.add(new Empleado("Lider de proyecto", 0, "Pepe"));
//		List<List<Empleado>> resultados = new ArrayList<>();
//		System.out.println(combinacionesLideres(resultados, new ArrayList<Empleado>(), 2, 0));
//	}
	
}
