package presenter;

import java.util.ArrayList;
import java.util.List;

import model.Empleado;
import model.GestorEmpleados;
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
	
	public void onAgregarIncompatible(int index) {
		Vista vista = _ventana.getVista();
		List<Empleado> empleados = new ArrayList<Empleado>(_gestor.getListaEmpleados().values());
	  vista.actualizarTabla(empleados, index);
	}
	
	public void onAgregarEmpleado(String nombre, String rol, String calificacion) {
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
			_ventana.mostrarIncompatibles();
			break;
		case "requerimientos":
			_ventana.mostrarRequerimientos();
			break;
		case "resultados":
			_ventana.mostrarResultados();
		default:
			break;
		}
	}
}
