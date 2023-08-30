package Presentacion.Recepcionista.VRecepcionistaCasosUso;

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
import Negocio.Recepcionista.TRecepcionista;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEditarRecepcionista2 extends JFrame implements IGUI {
	private JPanel panel;
	private JTextField id, nombre, salario, activo, numRecepcionista;
	
	
	public VEditarRecepcionista2() {
		super("Editar Recepcinista"); 
		panel = new JPanel(new GridLayout(6, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		nombre = new JTextField();
		salario = new JTextField();
		numRecepcionista = new JTextField();
		activo = new JTextField();
		
	}
	
	
	public void update(Object data) {
		TRecepcionista tRec = (TRecepcionista) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tRec.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		//Nombre
		JLabel lNombre = new JLabel("Nombre: ");
		nombre.setText(tRec.getNombre());
		nombre.setEditable(true);
		panel.add(lNombre);
		panel.add(nombre);
		//Salario
		JLabel lSalario = new JLabel("Salario: ");
		salario.setText(Float.toString(tRec.getSalario()));
		salario.setEditable(true);
		panel.add(lSalario);
		panel.add(salario);
		//NumRecepcionista
		JLabel lnRecep = new JLabel("NumRecepcionista: ");
		numRecepcionista.setText(Integer.toString(tRec.getNumRecepcionista()));
		numRecepcionista.setEditable(true);
		panel.add(lnRecep);
		panel.add(numRecepcionista);
		//Disponible
		JLabel lAct = new JLabel("Activo: ");
		activo.setText(Boolean.toString(tRec.getActivo()));
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
				int _id = tRec.getId();
				String nom = nombre.getText();
				int nRecep = Integer.parseInt(numRecepcionista.getText());
				float salary = Float.parseFloat(salario.getText());
				Controller.obtenerInstancia().accion(Events.MODIFICAR_RECEPCIONISTA2, new TRecepcionista(_id, nRecep, nom, salary, true));
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
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_RECEPCIONISTA, null);
			}
		});
		this.pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}
