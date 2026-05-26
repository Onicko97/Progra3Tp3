package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Empleado;
import presenter.SoftwareFactoryPresenter;

public class Vista extends JPanel {
	
	private JTable tablaEmpleados;
	private SoftwareFactoryPresenter presenter;
	private DefaultTableModel modeloTabla;
	public Vista() {
		setLayout(new BorderLayout(10, 10));
		
		JPanel botonesContenedor = new JPanel();
		botonesContenedor.setPreferredSize(new Dimension(450, 320));
		add(botonesContenedor, BorderLayout.NORTH);
		GridBagLayout gbl_botonesContenedor= new GridBagLayout();
		gbl_botonesContenedor.columnWidths = new int[]{64, 64, 0, 160};
		gbl_botonesContenedor.rowHeights = new int[]{110, 30, 30, 12};
		gbl_botonesContenedor.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_botonesContenedor.rowWeights = new double[]{30, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		botonesContenedor.setLayout(gbl_botonesContenedor);
		JButton btnListaEmpleados = new JButton("Carga de empleados");
		btnListaEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.onClick("cargaEmpleados");
			}
		});
		btnListaEmpleados.setBounds(218, 124, 187, 23);
	
		
		JButton btnIncompatibles = new JButton("Lista de Incompatibles");
		btnIncompatibles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.onClick("incompatibles");
			}
		});
		btnIncompatibles.setBounds(218, 177, 187, 23);
	
		
		JButton btnRequerimientos = new JButton("Requerimientos");
		btnRequerimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presenter.onClick("requerimientos");
			}
		});
		btnRequerimientos.setBounds(218, 81, 187, 23);

		
		JButton btnResolver = new JButton("Resolver");
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				presenter.onClick("resultados");
			}
		});
		btnResolver.setBounds(218, 232, 187, 23);
		botonesContenedor.add(btnListaEmpleados);
		botonesContenedor.add(btnIncompatibles);
		botonesContenedor.add(btnRequerimientos);
		botonesContenedor.add(btnResolver);
		

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBounds(10, 208, 604, 200);
		add(scrollPane);
		tablaEmpleados = new JTable();
		scrollPane.setViewportView(tablaEmpleados);
		modeloTabla = new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
				},
				new String[] {
					"Nombre", "Rol", "Calificacion", "Incompatible con"
				}) {
		    @Override
		    public boolean isCellEditable(int row, int col) { return false; }
		};
		tablaEmpleados.setModel(modeloTabla);
		
	}
	
	public List<String> getNombresEmpleados() {
		
		if(tablaVacia())
			throw new InvalidParameterException("Empleados invalidos");
		
		int col = 0;
		List<String> nombres = new ArrayList<String>();
		for(int i = 0; i < tablaEmpleados.getRowCount(); i++) {
			Object nombre = tablaEmpleados.getValueAt(i, col);
			nombres.add(nombre.toString());
		}
		return nombres;
	}
	
	private boolean tablaVacia() {
		boolean vacios = true;
		int col = 0;
		for (int i = 0; i < tablaEmpleados.getRowCount(); i++) {
			if (tablaEmpleados.getValueAt(i, col) != null) {
				vacios = false;
			}
		}
		return vacios;
	}
	
	 public void actualizarTabla(String empleado, int index, String empleadoSeleccionado, int index2) {
		 	//Object nombreIncompatible = modeloTabla.getValueAt(index, 0);
		 	
		 	modeloTabla.setValueAt(empleadoSeleccionado, index, 3);
		 	modeloTabla.setValueAt(empleado, index2, 3);
	        
	    }
	
	 public void actualizarTabla(List<Empleado> empleados) {
		 	
	        modeloTabla.setRowCount(0);
	        for (Empleado e : empleados) {
	            modeloTabla.addRow(new Object[]{
	                e.get_nombre(), e.get_rol(), e.get_calificacionHistorica(), ""
	            });
	        }
	        
	    }

	 public void modificarCelda() {
		 
	 }
	
	 public void setPresenter(SoftwareFactoryPresenter presenter) {
	        this.presenter = presenter;
	    }
}
