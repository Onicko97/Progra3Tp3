package presenter;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import model.Empleado;
import model.GestorEmpleados;
import model.Hilo;
import view.CargaEmpleados;
import view.EmpleadosIncompatibles;
import view.VentanaPrincipal;
import view.Vista;

public class SoftwareFactoryPresenter {
	
	VentanaPrincipal _ventana;
	GestorEmpleados _gestor;
	private Vista vista;
	private CargaEmpleados cargaEmpleados;
	private EmpleadosIncompatibles empleadosIncompatibles;
	
	public SoftwareFactoryPresenter(VentanaPrincipal ventana, GestorEmpleados gestor) {
		this._ventana = ventana;
		this._gestor = gestor;
		this.vista = ventana.getVista();
		this.cargaEmpleados = ventana.getCargaEmpleados();
		this.empleadosIncompatibles = ventana.getEmpleadosIncompatibles();
		this.vista.setPresenter(this);
		this.cargaEmpleados.setPresenter(this);
		this.empleadosIncompatibles.setPresenter(this);
	}
	
	public void iniciar() {
		_ventana.mostrar();
	}
	
	public void onAgregarIncompatible(String empleado, int index, String empleadoIncompatibleSeleccionado, int index2) {
		
		if(_gestor.getIncompatibles(empleado).contains(_gestor.buscarEmpleadoPorNombre(empleadoIncompatibleSeleccionado))) {
			JOptionPane.showMessageDialog(null, "Los empleados seleccionados ya son incompatibles");
			return;
		}
		
		Vista vista = _ventana.getVista();
		List<Empleado> empleados = new ArrayList<Empleado>(_gestor.getListaEmpleados().values());
		Empleado incomp = _gestor.buscarEmpleadoPorNombre(empleadoIncompatibleSeleccionado);
		Empleado incomp2 = _gestor.buscarEmpleadoPorNombre(empleado);
		_gestor.buscarEmpleadoPorNombre(empleado).agregarIncompatible(incomp);
		_gestor.buscarEmpleadoPorNombre(empleadoIncompatibleSeleccionado).agregarIncompatible(incomp2);
	  vista.actualizarTabla(empleado, index, empleadoIncompatibleSeleccionado, index2);
	}
	
	public void onAgregarEmpleado(String nombre, String rol, String calificacion) {
		if(nombre.isBlank() || rol.equals("Seleccione un rol") || calificacion.equals("Seleccione una calificacion")) {
			_ventana.mostrarError("complete todos los campos");
			return;
		}
		_gestor.agregarEmpleado(nombre, rol, Integer.parseInt(calificacion));
		Vista vista = _ventana.getVista();
		List<Empleado> empleados = new ArrayList<Empleado>(_gestor.getListaEmpleados().values());
	  vista.actualizarTabla(empleados);
	  _ventana.actualizarCheckBoxIncompatibles();
	}
	
	public void onClick(String prop) {
		switch(prop) {
		case "cargaEmpleados":
			_ventana.mostrarCargaEmpleados();
			break;
		case "incompatibles":
			try {
				_ventana.mostrarIncompatibles();
			}catch(InvalidParameterException e){
				_ventana.mostrarError("No hay empleados para agregar incompatibilidad");
			}
			
			break;
		case "requerimientos":
			_ventana.mostrarRequerimientos(this);
			break;
		case "resultados":
			try {
			_ventana.mostrarResultados(_gestor.getStringsRequerimientos(), this);
			}catch(NullPointerException e) {
				_ventana.mostrarError("Requerimientos de equipo no ingresados");
			}
			break;
		default:
			break;
		}
	}
	
	public void settearRequerimientos(String cantLideres, String cantArquitectos, String cantProgramadores, String cantTesters) {
		if(esNumerico(cantLideres) && esNumerico(cantArquitectos) && esNumerico(cantProgramadores) && esNumerico(cantTesters)) {
			int _cantLideres = Integer.parseInt(cantLideres);
			int _cantArquitectos = Integer.parseInt(cantArquitectos);
			int _cantProgramadores = Integer.parseInt(cantProgramadores);
			int _cantTesters = Integer.parseInt(cantTesters);
			_gestor.settearRequerimientos(_cantLideres, _cantArquitectos, _cantProgramadores, _cantTesters);
		}else {
			_ventana.mostrarError("Ingrese valores validos en todos los campos (enteros mayores o iguales a 0)");
		}
		
	}
	
	public static boolean esNumerico(String str) {
	    if (str == null || str.isBlank()) {
	        return false;
	    }
	    try {
	        int n = Integer.parseInt(str);
	        if(n < 0) { //puede pasar que un proyecto pueda tener 0 testers o 0 arquitectos?
	        	throw new NumberFormatException();
	        }
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
//desde aca se crea Hilo y no en _gestor para que hilo pueda avisar qeu termino 	
	public void buscarEquipo() {
		_ventana.mostrarCargandoResultados(true);
		Hilo hilo = new Hilo(this, _gestor); 
	    hilo.execute();
		}
//usamos streams
	public void equipoCalculadoExitosamente(List<Empleado> equipo) {
		_ventana.mostrarCargandoResultados(false);
		List<String> stringsEquipo = equipo.stream()
	        .map(e -> e.get_rol() + " - " + e.get_nombre() + " - " + e.get_calificacionHistorica())
	        .collect(Collectors.toList());
	    _ventana.dibujarEquipoResultante(stringsEquipo);
	}
	
	
}
