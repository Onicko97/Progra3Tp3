package view;


import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

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
	
	JLabel lblCasoBase;
	JLabel lblValidos;
	JLabel lblInvalidos;
	JLabel lblTiempo;
	
	private JTextArea txtResultados;
	private JTextArea txtHeuristica;
	private JProgressBar barraProgreso;
	private JButton btnResolver;
	
	public Resultados() {
		/*setBackground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout());

        add(new JLabel("Esta es la PANTALLA 1"));
        
        JButton btnIrAPantalla2 = new JButton("Ir a Pantalla 2");
        add(btnIrAPantalla2);*/
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Mejor Equipo");
		setBounds(100, 100, 1000, 600);
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
		
		//estadisticas
		
		lblCasoBase = new JLabel("Casos base: ");
		lblCasoBase.setBounds(20, 460, 250, 14);
		getContentPane().add(lblCasoBase);

		lblValidos = new JLabel("Equipos válidos: ");
		lblValidos.setBounds(20, 480, 250, 14);
		getContentPane().add(lblValidos);

		lblInvalidos = new JLabel("Equipos descartados: ");
		lblInvalidos.setBounds(20, 500, 250, 14);
		getContentPane().add(lblInvalidos);

		lblTiempo = new JLabel("Tiempo total: ");
		lblTiempo.setBounds(20, 520, 250, 14);
		getContentPane().add(lblTiempo);
		
		//algoritmos
		JLabel lblBacktracking = new JLabel("Backtracking:");
		lblBacktracking.setBounds(20, 170, 150, 14);
		getContentPane().add(lblBacktracking);

		JLabel lblHeuristica = new JLabel("Heurística:");
		lblHeuristica.setBounds(510, 170, 150, 14);
		getContentPane().add(lblHeuristica);
		
		//equipo backtracking
		
		txtResultados = new JTextArea();
	    txtResultados.setEditable(false);
	    
	    javax.swing.JScrollPane scrollResultados = new javax.swing.JScrollPane(txtResultados);
	    scrollResultados.setBounds(20, 190, 460, 250); 
	    getContentPane().add(scrollResultados);
		
	    //equipo heuristica
	 
	    txtHeuristica = new JTextArea();
	    txtHeuristica.setEditable(false);
	    javax.swing.JScrollPane scrollHeuristica = new javax.swing.JScrollPane(txtHeuristica);
	    scrollHeuristica.setBounds(510, 190, 460, 250);
	    getContentPane().add(scrollHeuristica);
	    
	    //barra de carga
	    
	    barraProgreso = new JProgressBar();
	    barraProgreso.setBounds(185, 114, 230, 20); 
	    barraProgreso.setVisible(false); 
	    getContentPane().add(barraProgreso);
	    
		btnResolver = new JButton("Resolver");
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
	
	public void mostrarListaFinal(List<String> stringsEquipo) {
	    txtResultados.setText(""); 
	    
	    if (stringsEquipo.isEmpty()) {
	        txtResultados.setText("No se encontró ningún equipo compatible.");
	        return;
	    }
	    
	    txtResultados.append("MEJOR EQUIPO ENCONTRADO\n\n");
	    
	    for (String linea : stringsEquipo) {
	        //esto es para separadar el string
	        String[] partes = linea.split(" - ");
	        String rol = partes[0].toUpperCase();
	        String nombre = partes[1];
	        String calif = partes[2];
	        
	   
	        txtResultados.append( rol + " : " + nombre + " (Puntaje: " + calif + " puntos)\n");
	    }
	}
	public void mostrarEquipoHeuristica(List<String> stringsEquipo) {
	    txtHeuristica.setText("");
	    
	    if (stringsEquipo.isEmpty()) {
	        txtHeuristica.setText("No se encontró ningún equipo compatible.");
	        return;
	    }
	    
	    txtHeuristica.append("EQUIPO POR HEURISTICA\n\n");
	    for (String lineaCruda : stringsEquipo) {
	        String[] partes = lineaCruda.split(" - ");
	        txtHeuristica.append(partes[0].toUpperCase() + " : " + partes[1] + " (Puntaje: " + partes[2] + " puntos)\n");
	    }
	}
	
	public void mostrarCargando(boolean activar) {
	    barraProgreso.setIndeterminate(activar); //activa la animacion
	    barraProgreso.setVisible(activar);
	    btnResolver.setEnabled(!activar);
	}
	
	public void mostrarEstadisticas(List<String> estadisticas) {
	    lblCasoBase.setText("Casos base: " + estadisticas.get(0));
	    lblValidos.setText("Equipos válidos: " + estadisticas.get(1));
	    lblInvalidos.setText("Equipos descartados: " + estadisticas.get(2));
	    lblTiempo.setText("Tiempo total: " + estadisticas.get(3) + " ms");
	}
	
}
