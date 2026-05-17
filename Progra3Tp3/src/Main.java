import model.ControladorPersonas;
import presenter.SoftwareFactoryPresenter;
import view.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		
		VentanaPrincipal ventana = new VentanaPrincipal();
		ControladorPersonas controlador = new ControladorPersonas();
		SoftwareFactoryPresenter presenter = new SoftwareFactoryPresenter(ventana, controlador);
		System.out.println("hello world");
	}

}
