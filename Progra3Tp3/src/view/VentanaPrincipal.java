package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import presenter.SoftwareFactoryPresenter;

import javax.swing.JDialog;

public class VentanaPrincipal extends JFrame {

	private final CardLayout cardLayout = new CardLayout();
	private final JPanel contenedor = new JPanel(cardLayout);
	private final Vista vista;
	private final CargaEmpleados cargaEmpleados;
	private final EmpleadosIncompatibles empleadosIncompatibles;
	private final Requerimientos requerimientos;
	private final Resultados resultado;

	public VentanaPrincipal() {
		vista = new Vista();
		resultado = new Resultados();
		cargaEmpleados = new CargaEmpleados();
		empleadosIncompatibles = new EmpleadosIncompatibles();
		requerimientos = new Requerimientos();
		configurarLayout();
		propiedadesPorDefecto();
	}
	
	private void configurarLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		
		contenedor.add(vista, "vistaPrincipal");
		//contenedor.add(resultado, "resultados");
		getContentPane().add(contenedor, BorderLayout.CENTER);
	  mostrarVistaPrincipal();
	}
	
	private void propiedadesPorDefecto() {
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	 public void mostrar() {
	        EventQueue.invokeLater(() -> setVisible(true));
	    }

	public void mostrarVistaPrincipal() {
		cardLayout.show(contenedor, "vistaPrincipal");
	}
	
	public void mostrarResultados(List<String> requerimientos, SoftwareFactoryPresenter presenter) {
		//cardLayout.show(contenedor, "resultados");
		
		resultado.settearPresenter(presenter);
		resultado.settearRequerimientos(requerimientos);
		resultado.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		resultado.setVisible(true);
	}
	
	
	public void mostrarCargaEmpleados() {
		
		cargaEmpleados.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		cargaEmpleados.setVisible(true);
	}
	public void mostrarIncompatibles() {
		
		empleadosIncompatibles.setComboBoxEmpleados(vista.getNombresEmpleados());
		empleadosIncompatibles.setListaIncompatiblesEmpleados(vista.getNombresEmpleados());
		empleadosIncompatibles.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		empleadosIncompatibles.setVisible(true);
	}
	public void mostrarRequerimientos(SoftwareFactoryPresenter presenter) {
	
		requerimientos.setPresenter(presenter);
		requerimientos.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		requerimientos.setVisible(true);
	}
	
	public void actualizarCheckBoxIncompatibles() {
		empleadosIncompatibles.setComboBoxEmpleados(vista.getNombresEmpleados());
	}
	
	public Vista getVista() {
		return vista;
	}
	
	public CargaEmpleados getCargaEmpleados() {
		return cargaEmpleados;
	}

	public EmpleadosIncompatibles getEmpleadosIncompatibles() {
		
		return empleadosIncompatibles;
	}
	
	public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
	
	public void dibujarEquipoResultante(List<String> stringsEquipo) {
	    this.resultado.mostrarListaFinal(stringsEquipo);
	}
	public void dibujarEquipoHeuristica(List<String> stringsEquipo) {
		this.resultado.mostrarEquipoHeuristica(stringsEquipo);
	}

	public void setPresenter(SoftwareFactoryPresenter presenter) {
	    this.vista.setPresenter(presenter);
	    this.cargaEmpleados.setPresenter(presenter);
	    this.empleadosIncompatibles.setPresenter(presenter);
	    this.requerimientos.setPresenter(presenter);
	}
	public void mostrarCargandoResultados(boolean activar) {
	    resultado.mostrarCargando(activar); 
	}
	
	public void mostrarEstadisticas(List<String> estadisticas ) {
		resultado.mostrarEstadisticas(estadisticas);
	}
	
}
