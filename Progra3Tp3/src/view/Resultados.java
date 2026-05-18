package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Resultados extends JPanel {
	
	
	private static final long serialVersionUID = 1L;

	public Resultados() {
		setBackground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout());

        add(new JLabel("Esta es la PANTALLA 1"));
        
        JButton btnIrAPantalla2 = new JButton("Ir a Pantalla 2");
        add(btnIrAPantalla2);
	}
}
