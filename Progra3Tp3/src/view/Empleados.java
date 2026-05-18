package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Empleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaEmpleados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Empleados dialog = new Empleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Empleados() {
		setTitle("Empleados");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 208, 604, 200);
		contentPanel.add(scrollPane);
		tablaEmpleados = new JTable();
		scrollPane.setViewportView(tablaEmpleados);
		{
			
			DefaultTableModel modelo = new DefaultTableModel();
			
			modelo.addColumn("Nombre");
			modelo.addColumn("Rol");
			modelo.addColumn("Calificacion");
			
			//a modo de prueba para ver si la tabla funciona:
			modelo.addRow(new String[] {"emp","Programador" ,"3"});
			
			tablaEmpleados.setModel(modelo);
		}
		
		JButton btnAgregarEmpleado = new JButton("Agregar Empleado");
		btnAgregarEmpleado.setBounds(221, 80, 153, 23);
		contentPanel.add(btnAgregarEmpleado);
		
		JButton btnQuitarEmpleado = new JButton("Quitar Empleado");
		btnQuitarEmpleado.setBounds(221, 133, 153, 23);
		contentPanel.add(btnQuitarEmpleado);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//llamar al presenter y/o poner logica
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
