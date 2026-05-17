import model.GestorEmpleados;
import presenter.SoftwareFactoryPresenter;
import view.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		
		VentanaPrincipal ventana = new VentanaPrincipal();
		GestorEmpleados gestor = new GestorEmpleados();
		SoftwareFactoryPresenter presenter = new SoftwareFactoryPresenter(ventana, gestor);
		System.out.println("hello world");
	}

}
