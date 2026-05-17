package presenter;

import model.ControladorPersonas;
import view.VentanaPrincipal;

public class SoftwareFactoryPresenter {
	
	VentanaPrincipal _ventana;
	ControladorPersonas _controlador;
	
	public SoftwareFactoryPresenter(VentanaPrincipal ventana, ControladorPersonas controlador) {
		this._ventana = ventana;
		this._controlador = controlador;
	}
}
