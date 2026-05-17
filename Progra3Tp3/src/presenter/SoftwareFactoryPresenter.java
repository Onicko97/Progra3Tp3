package presenter;

import model.GestorEmpleados;
import view.VentanaPrincipal;

public class SoftwareFactoryPresenter {
	
	VentanaPrincipal _ventana;
	GestorEmpleados _gestor;
	
	public SoftwareFactoryPresenter(VentanaPrincipal ventana, GestorEmpleados gestor) {
		this._ventana = ventana;
		this._gestor = gestor;
	}
}
