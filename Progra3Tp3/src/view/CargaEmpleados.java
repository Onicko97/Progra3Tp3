package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presenter.SoftwareFactoryPresenter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CargaEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private SoftwareFactoryPresenter presenter;


	public CargaEmpleados() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Carga de Empleados");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(40, 40, 67, 14);
		contentPanel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(146, 37, 200, 20);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(40, 80, 46, 14);
		contentPanel.add(lblRol);
		
		JComboBox comboBoxRol = new JComboBox();
		comboBoxRol.setBounds(146, 76, 200, 22);
		contentPanel.add(comboBoxRol);
		comboBoxRol.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un rol","Lider de proyecto","Arquitecto","Programador","Tester"}));
		
		JLabel lblCalificacion = new JLabel("Calificacion:");
		lblCalificacion.setBounds(40, 120, 82, 14);
		contentPanel.add(lblCalificacion);
		
		JComboBox comboBoxCalificacion = new JComboBox();
		comboBoxCalificacion.setBounds(146, 116, 200, 22);
		contentPanel.add(comboBoxCalificacion);
		comboBoxCalificacion.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una calificacion","1","2","3","4","5"}));
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) {
						String nombre = textFieldNombre.getText();
						String rol = comboBoxRol.getSelectedItem().toString();
						String calificacion = comboBoxCalificacion.getSelectedItem().toString();
						presenter.onAgregarEmpleado(nombre, rol, calificacion);
						
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			
	}
	public void setPresenter(SoftwareFactoryPresenter presenter) {
	        this.presenter = presenter;
	    }
}
