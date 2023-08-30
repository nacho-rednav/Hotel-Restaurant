package Presentacion.Reserva;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Negocio.Reserva.TReserva;

public class VEditarReserva2 extends JFrame implements IGUI {
	private JPanel panel;
	private JTextField id, codigo, dias, activo, id_cliente, id_recepcionista, num_habitaciones, fechaInicio;
	
	
	public VEditarReserva2() {
		super("Modificar reserva"); 
		panel = new JPanel(new GridLayout(12, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		codigo = new JTextField();
		dias = new JTextField();
		activo = new JTextField();
		id_cliente = new JTextField();
		id_recepcionista = new JTextField();
		num_habitaciones = new JTextField();
		fechaInicio = new JTextField();
	}
	
	
	public void update(Object data) {
		TReserva tm = (TReserva) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tm.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		//Codigo
		JLabel lNum = new JLabel("Codigo: ");
		codigo.setText(Integer.toString(tm.getCodigo()));
		codigo.setEditable(true);
		panel.add(lNum);
		panel.add(codigo);
		//Dias
		JLabel lCap = new JLabel("Dias: ");
		dias.setText(Integer.toString(tm.getNumDias()));
		dias.setEditable(true);
		panel.add(lCap);
		panel.add(dias);
		//Activo
		JLabel lAct = new JLabel("Activo: ");
		activo.setText(Boolean.toString(tm.getActivo()));
		activo.setEditable(false);
		panel.add(lAct);
		panel.add(activo);
		//IDCliente
		JLabel lIC = new JLabel("ID Cliente: ");
		id_cliente.setText(Integer.toString(tm.getId_Cliente()));
		id_cliente.setEditable(true);
		panel.add(lIC);
		panel.add(id_cliente);
		//IDRecepcionista
		JLabel lIR = new JLabel("ID Recepcionista: ");
		id_recepcionista.setText(Integer.toString(tm.getId_Recepcionista()));
		id_recepcionista.setEditable(true);
		panel.add(lIR);
		panel.add(id_recepcionista);
		//Num_Habitaciones
		JLabel lNH = new JLabel("Numero de habitaciones: ");
		num_habitaciones.setText(Integer.toString(tm.getNum_Habitaciones()));
		num_habitaciones.setEditable(true);
		panel.add(lNH);
		panel.add(num_habitaciones);
		//FechaInicio
		JLabel lF = new JLabel("Fecha: ");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		if(tm.getFechaInicio() != null){
			fechaInicio.setText(formato.format(tm.getFechaInicio()));
		}
		else{
			fechaInicio.setText("Sin cerrar reserva");
		}
		fechaInicio.setEditable(false);
		panel.add(lF);
		panel.add(fechaInicio);
		//Boton OK
		JButton okB = new JButton("OK");
		panel.add(okB);
				
		
		okB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				int _id = tm.getId();
				int codig = Integer.parseInt(codigo.getText());
				int dia = Integer.parseInt(dias.getText());
				int id_client = Integer.parseInt(id_cliente.getText());
				int id_recep = Integer.parseInt(id_recepcionista.getText());
				int num_habitacion = Integer.parseInt(num_habitaciones.getText());
				Controller.obtenerInstancia().accion(Events.MODIFICAR_RESERVA2, new TReserva(_id, codig, dia, id_client, id_recep, num_habitacion, tm.getFechaInicio(), true));
				setVisible(false);
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(panel, "Introduzca datos v�lidos");
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
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_RESERVA, null);
			}
		});
		this.pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}