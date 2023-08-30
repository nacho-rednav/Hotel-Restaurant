package Presentacion.Reserva;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.SpinnerDateModel;

import Negocio.Reserva.Pair;
import Negocio.Reserva.TLineaPedidoServicios;
import Negocio.Servicio.TServicio;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;

public class VCompraServicio2 extends JFrame implements IGUI {
	
	private ArrayList<JRadioButton > servicios;
	private JPanel panel;
	private Date fechaInicio;
	
	
	public VCompraServicio2() {
		super("Comprar servicio"); 
		panel = new JPanel(new GridLayout(12, 4));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	public void update(Object data) {
		TLineaPedidoServicios lineapedidos = (TLineaPedidoServicios) data;
		ArrayList<Pair<TServicio, Date>> pedido = lineapedidos.getIdsServicios();
		ArrayList <JSpinner> spinnerArray = new ArrayList<JSpinner>(pedido.size());
		
		JRadioButton [] rb = new JRadioButton[pedido.size()];
		
		//----------------------------------------------------------------------
		
		/*Date today = new Date();
	    JSpinner diaSpin = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
	    JSpinner.DateEditor editor = new JSpinner.DateEditor(diaSpin, "dd/MM/yy");
	    diaSpin.setEditor(editor);
	    diaSpin.setVisible(true);*/		
		
		//----------------------------------------------------------------------
		
		for(int i = 0; i < pedido.size(); i++){
			Date today = new Date();
		    JSpinner diaSpin = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		    JSpinner.DateEditor editor = new JSpinner.DateEditor(diaSpin, "dd/MM/yy");
		    diaSpin.setEditor(editor);
		    diaSpin.setVisible(true);	
		    
			rb[i] = new JRadioButton(pedido.get(i).getFirst().getTipo());

			panel.add(rb[i]);
			spinnerArray.add(diaSpin);
			panel.add(spinnerArray.get(i));
		}
		
		
		
		
		//Boton OK
		JButton okB = new JButton("OK");
		panel.add(okB);
				
		
		okB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				ArrayList<Pair<TServicio,Date>> devpedido = new ArrayList<Pair<TServicio,Date>>();
				
				for(int i = 0; i < rb.length; i++){
					
					if(rb[i].isSelected()){
						TServicio s = pedido.get(i).getFirst();
		                Date d = (Date) spinnerArray.get(i).getValue();

		                devpedido.add(new Pair<TServicio, Date>(s, d));
					}
				}
				TLineaPedidoServicios lineapedidosdev = new TLineaPedidoServicios(lineapedidos.getIdReserva(), devpedido); 
				Controller.obtenerInstancia().accion(Events.COMPRA_SERVICIO_RESERVA2, lineapedidosdev); 
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
			Controller.obtenerInstancia().accion(Events.ABRIR_VRESERVA, null);
			}
		});
		this.pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}