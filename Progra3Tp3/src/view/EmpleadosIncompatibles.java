package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import presenter.SoftwareFactoryPresenter;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class EmpleadosIncompatibles extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBoxRol;
	private DefaultComboBoxModel<String> modelo;
	private JList listaEmpleados;
	private JScrollPane scrollPane;
	private DefaultListModel modeloLista;
	private SoftwareFactoryPresenter presenter;
	
	public EmpleadosIncompatibles() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Empleados Incompatibles");
		setBounds(200, 200, 400, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblRol = new JLabel("Empleado:");
		lblRol.setBounds(80, 30, 100, 100);
		contentPanel.add(lblRol);
		comboBoxRol = new JComboBox();
		modelo = new DefaultComboBoxModel<>();
		modeloLista = new DefaultListModel<>();
		
		comboBoxRol.setModel(modelo);
		comboBoxRol.setBounds(80, 100, 200, 22);
		contentPanel.add(comboBoxRol);
		JLabel lblInc = new JLabel("Es incompatible con:");
		lblInc.setBounds(80, 120, 120, 100);
		contentPanel.add(lblRol);
		contentPanel.add(lblInc);
		listaEmpleados = new JList<>(modeloLista);
		listaEmpleados.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		listaEmpleados.setBounds(80, 180, 100, 100);
//		contentPanel.add(listaEmpleados);
		
        scrollPane = new JScrollPane(listaEmpleados);
        scrollPane.setBounds(80, 180, 100, 100);
        contentPanel.add(scrollPane);
        
        
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(listaEmpleados.getSelectedValue().toString() == modelo.getSelectedItem().toString()) {
							JOptionPane.showMessageDialog(null, "Un empleado no puede ser incompatible consigo mismo");
							return;
						}
						presenter.onAgregarIncompatible(listaEmpleados.getSelectedValue().toString() ,listaEmpleados.getSelectedIndex(),
								modelo.getSelectedItem().toString(), modelo.getIndexOf(modelo.getSelectedItem()));
						//obtenerSeleccionados();
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
	
	public void setComboBoxEmpleados(List<String> empleados) {
		modelo.removeAllElements();
		String[] nombres = empleados.toArray(new String[empleados.size()]);
		for(String n: nombres) {
			modelo.addElement(n);
		}
		
		
		
	}
	
	public void setListaIncompatiblesEmpleados(List<String> empleados) {
		modeloLista.removeAllElements();
		String[] nombres = empleados.toArray(new String[empleados.size()]);

		for(String n: nombres) {
			modeloLista.addElement(n);
		}
	}
	
//	public void obtenerSeleccionados() {
//        List<String> empleadosSeleccionados = listaEmpleados.getSelectedValuesList();
//        System.out.println(listaEmpleados.getSelectedIndex());
//        for (String emp : empleadosSeleccionados) {
//            System.out.println("Seleccionado: " + emp);
//        }
//    }
	
	 public void setPresenter(SoftwareFactoryPresenter presenter) {
	        this.presenter = presenter;
	    }
}
