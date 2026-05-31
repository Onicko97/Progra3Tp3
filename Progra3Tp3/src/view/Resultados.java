package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;

import presenter.SoftwareFactoryPresenter;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Resultados extends JDialog {
	
	private static final long serialVersionUID = 1L;
	SoftwareFactoryPresenter presenter;
	JLabel cantLideres;
	JLabel cantArquitectos;
	JLabel cantProg;
	JLabel cantTesters;

	public Resultados() {
		/*setBackground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout());

        add(new JLabel("Esta es la PANTALLA 1"));
        
        JButton btnIrAPantalla2 = new JButton("Ir a Pantalla 2");
        add(btnIrAPantalla2);*/
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Mejor Equipo");
		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(null);
		
		JLabel lblRequerimientosDeEquipo = new JLabel("Requerimientos del Equipo:");
		lblRequerimientosDeEquipo.setBounds(10, 11, 165, 22);
		getContentPane().add(lblRequerimientosDeEquipo);
		
		JLabel lblLideres = new JLabel("Lideres:");
		lblLideres.setBounds(20, 44, 46, 14);
		getContentPane().add(lblLideres);
		
		cantLideres = new JLabel("0");
		cantLideres.setBounds(129, 44, 46, 14);
		getContentPane().add(cantLideres);
		
		JLabel lblArquitectos = new JLabel("Arquitectos:");
		lblArquitectos.setBounds(20, 69, 72, 14);
		getContentPane().add(lblArquitectos);
		
		cantArquitectos = new JLabel("0");
		cantArquitectos.setBounds(129, 69, 46, 14);
		getContentPane().add(cantArquitectos);
		
		JLabel lblProgramadores = new JLabel("Programadores:");
		lblProgramadores.setBounds(20, 95, 84, 14);
		getContentPane().add(lblProgramadores);
		
		cantProg = new JLabel("0");
		cantProg.setBounds(129, 95, 46, 14);
		getContentPane().add(cantProg);
		
		JLabel lblTesters = new JLabel("Testers:");
		lblTesters.setBounds(20, 120, 46, 14);
		getContentPane().add(lblTesters);
		
		cantTesters = new JLabel("0");
		cantTesters.setBounds(129, 120, 46, 14);
		getContentPane().add(cantTesters);
		
		JButton btnResolver = new JButton("Resolver");
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				presenter.buscarEquipo();
				
			}
		});
		btnResolver.setBounds(275, 150, 89, 23);
		getContentPane().add(btnResolver);
	}
	
	public void settearRequerimientos(List<String> requerimientos) {
		cantLideres.setText(requerimientos.get(0));
		cantArquitectos.setText(requerimientos.get(1));
		cantProg.setText(requerimientos.get(2));
		cantTesters.setText(requerimientos.get(3));
	}
	
	public void settearPresenter(SoftwareFactoryPresenter presenter) {
		this.presenter = presenter;
	}
	
}
