package Presentacion.Cliente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Cliente.TCliente;
import Negocio.Habitacion.TBasica;
import Negocio.Habitacion.THabitacion;
import Negocio.Habitacion.TSuite;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEditarCliente2 extends JFrame implements IGUI{
	private JPanel panel;
	private JTextField id, activo, nombre, email;
		
	public VEditarCliente2() {
		super("Modificar Cliente"); 
		panel = new JPanel(new GridLayout(5, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		nombre = new JTextField();
		email = new JTextField();
		activo = new JTextField();
	}
	
	@Override
	public void update(Object data) {
		TCliente tc = (TCliente) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tc.getID()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		//Nombre
		JLabel lNomb = new JLabel("Nombre: ");
		nombre.setText(tc.getNombre());
		nombre.setEditable(true);
		panel.add(lNomb);
		panel.add(nombre);
		//Email
		JLabel lEml = new JLabel("Email: ");
		email.setText(tc.getEmail());
		email.setEditable(true);
		panel.add(lEml);
		panel.add(email);
		//Activo
		JLabel lAct = new JLabel("Activo: ");
		activo.setText(Boolean.toString(tc.getActivo()));
		activo.setEditable(false);
		panel.add(lAct);
		panel.add(activo);
		
		//Boton OK
		JButton okB = new JButton("OK");
		panel.add(okB);
				
		
		okB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				int _id = tc.getID();
				String _nombre = nombre.getText();
				String _email = email.getText();
				Controller.obtenerInstancia().accion(Events.MODIFICAR_CLIENTE2, new TCliente(_id, true, _nombre, _email));
				setVisible(false);
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(panel, "Introduzca datos validos");
				setVisible(true);
			}
		}
	});
				
				
		//Boton Cancelar
		JButton cancelB = new JButton("Cancel");
		panel.add(cancelB);
				
		cancelB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_CLIENTE, null);
			}
		});
		this.pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}

