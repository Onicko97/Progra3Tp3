package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

public class VentanaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnListaEmpleados = new JButton("Lista de Empleados");
		btnListaEmpleados.setBounds(218, 124, 187, 23);
		frame.getContentPane().add(btnListaEmpleados);
		
		JButton btnIncompatibles = new JButton("Lista de Incompatibles");
		btnIncompatibles.setBounds(218, 177, 187, 23);
		frame.getContentPane().add(btnIncompatibles);
		
		JButton btnRequerimientos = new JButton("Requerimientos");
		btnRequerimientos.setBounds(218, 81, 187, 23);
		frame.getContentPane().add(btnRequerimientos);
		
		JButton btnResolver = new JButton("Resolver");
		btnResolver.setBounds(218, 232, 187, 23);
		frame.getContentPane().add(btnResolver);
	}
}
