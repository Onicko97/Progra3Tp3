package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import presenter.SoftwareFactoryPresenter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Requerimientos extends JDialog {

	private SoftwareFactoryPresenter presenter;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCantLideres;
	private JTextField textFieldCantArquitectos;
	private JTextField textFieldCantProgramadores;
	private JTextField textFieldCantTesters;


	/*public static void main(String[] args) {
		try {
			Requerimientos dialog = new Requerimientos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void setPresenter(SoftwareFactoryPresenter presenter) {
		this.presenter = presenter;
	}
	
	public Requerimientos() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Requerimientos");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCantLideres = new JLabel("Cantidad de Lideres de proyecto: ");
			lblCantLideres.setBounds(35, 45, 172, 14);
			contentPanel.add(lblCantLideres);
		}
		{
			textFieldCantLideres = new JTextField();
			textFieldCantLideres.setBounds(217, 42, 86, 20);
			contentPanel.add(textFieldCantLideres);
			textFieldCantLideres.setColumns(10);
		}
		{
			JLabel lblCantArquitectos = new JLabel("Cantidad de Arquitectos:");
			lblCantArquitectos.setBounds(35, 70, 172, 14);
			contentPanel.add(lblCantArquitectos);
		}
		{
			textFieldCantArquitectos = new JTextField();
			textFieldCantArquitectos.setColumns(10);
			textFieldCantArquitectos.setBounds(217, 67, 86, 20);
			contentPanel.add(textFieldCantArquitectos);
		}
		{
			JLabel lblCantProgramadores = new JLabel("Cantidad de Programadores:");
			lblCantProgramadores.setBounds(35, 95, 172, 14);
			contentPanel.add(lblCantProgramadores);
		}
		{
			textFieldCantProgramadores = new JTextField();
			textFieldCantProgramadores.setColumns(10);
			textFieldCantProgramadores.setBounds(217, 92, 86, 20);
			contentPanel.add(textFieldCantProgramadores);
		}
		{
			JLabel lblCantTesters = new JLabel("Cantidad de Testers:");
			lblCantTesters.setBounds(35, 120, 172, 14);
			contentPanel.add(lblCantTesters);
		}
		{
			textFieldCantTesters = new JTextField();
			textFieldCantTesters.setColumns(10);
			textFieldCantTesters.setBounds(217, 117, 86, 20);
			contentPanel.add(textFieldCantTesters);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						int cantLideres = Integer.parseInt(textFieldCantLideres.getText());
						int cantArquitectos = Integer.parseInt(textFieldCantArquitectos.getText());
						int cantProgramadores = Integer.parseInt(textFieldCantProgramadores.getText());
						int cantTesters = Integer.parseInt(textFieldCantTesters.getText());
						presenter.settearRequerimientos(cantLideres, cantArquitectos, cantProgramadores, cantTesters);
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
