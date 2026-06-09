package model;

import java.util.ArrayList;
import java.util.List;

public class MejorCalificado {
	private List<Empleado> lideres;
	private List<Empleado> arquitectos;
	private List<Empleado> testers;
	private List<Empleado> programadores;
	
	private int cantidadCasoBase;
    private int cantidadEquiposValidos;
    private int cantidadEquiposInvalidos;
    private long tiempoTotal;
	
	public MejorCalificado() {
		this.lideres = new ArrayList<Empleado>();
		this.arquitectos = new ArrayList<Empleado>();
		this.testers = new ArrayList<Empleado>();
		this.programadores = new ArrayList<Empleado>();
		}

	public List<Empleado> resolver(List<Empleado> empleados,int reqLid, int reqArq, int reqProg, int reqTest) {
		cantidadCasoBase = 0;
        cantidadEquiposValidos = 0;
        cantidadEquiposInvalidos = 0;
        long inicio = System.currentTimeMillis();
        
		this.lideres.clear();
	    this.arquitectos.clear();
	    this.programadores.clear();
	    this.testers.clear();
	    
		separarEmpleadosPorRol(empleados);
		List<List<Empleado>> conjuntos = obtenerConjuntosSegunRequerimientos(reqLid, reqArq, reqProg, reqTest);
		List<Empleado> mejorEquipo = buscarMejorEquipo(conjuntos); 
	    
		tiempoTotal = System.currentTimeMillis() - inicio;
		return mejorEquipo;
	    
	}
	
	private List<List<Empleado>> obtenerCombinaciones(List<Empleado> empleadosMismoRol, int requeridos) {
		List<List<Empleado>> resultado = new ArrayList<>();
		List<Empleado> vacio = new ArrayList<>();
		int inicio = 0;
		backtrack(inicio, resultado, empleadosMismoRol ,vacio, requeridos);
		return resultado;
	}
	
	private List<List<Empleado>> backtrack(int inicio, List<List<Empleado>> empleadosComb, List<Empleado> empleados, List<Empleado> comb, int requeridos ) {
		if (comb.size() == requeridos ) {
			empleadosComb.add(new ArrayList<>(comb)); //lo clone para que no se borre
			cantidadCasoBase++;
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
		if (conjuntos == null || conjuntos.isEmpty()) {
	        return new ArrayList<>(); 
	    }
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
	
//fuerza bruta	
	public List<List<Empleado>> obtenerConjuntosSegunRequerimientos(int cantLideres,int cantArquitectos, int cantProgramadores, int cantTesters) {
		
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
	                    	cantidadEquiposValidos++;
	                        todosLosEquiposValidos.add(equipoCandidato);
	                    }
	                    else {
	                        cantidadEquiposInvalidos++;
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
	
	public int getCantidadCasoBase() { return cantidadCasoBase; }
	public int getCantidadEquiposValidos() { return cantidadEquiposValidos; }
	public int getCantidadEquiposInvalidos() { return cantidadEquiposInvalidos; }
	public long getTiempoTotal() { return tiempoTotal; }
	
}
