package Presentacion.Mesa.VMesaCasosUso;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Mesa.TMesa;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;


public class VModificarMesa2 extends JFrame implements IGUI {
	
	private JPanel panel;
	private JTextField id, numero, capacidad, activo, disponibilidad;
	
	
	public VModificarMesa2() {
		super("Modificar mesa"); 
		panel = new JPanel(new GridLayout(6, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		numero = new JTextField();
		capacidad = new JTextField();
		disponibilidad = new JTextField();
		activo = new JTextField();
		
	}
	
	
	public void update(Object data) {
		TMesa tm = (TMesa) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tm.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		//Numero
		JLabel lNum = new JLabel("Numero: ");
		numero.setText(Integer.toString(tm.getNumMesa()));
		numero.setEditable(true);
		panel.add(lNum);
		panel.add(numero);
		//Capacidad
		JLabel lCap = new JLabel("Capacidad: ");
		capacidad.setText(Integer.toString(tm.getCapacidad()));
		capacidad.setEditable(true);
		panel.add(lCap);
		panel.add(capacidad);
		//Disponible
		JLabel lDisp = new JLabel("Disponibilidad: ");
		disponibilidad.setText(Boolean.toString(tm.getDisponibilidad()));
		disponibilidad.setEditable(false);
		panel.add(lDisp);
		panel.add(disponibilidad);
		//Disponible
		JLabel lAct = new JLabel("Activo: ");
		activo.setText(Boolean.toString(tm.getActivo()));
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
				int _id = tm.getId();
				int numMesa = Integer.parseInt(numero.getText());
				int cap = Integer.parseInt(capacidad.getText());
				Controller.obtenerInstancia().accion(Events.MODIFICAR_MESA2, new TMesa(_id, numMesa, true, cap, true));
				setVisible(false);
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(panel, "Introduzca datos válidos");
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
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_MESA, null);
			}
		});
		this.pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}