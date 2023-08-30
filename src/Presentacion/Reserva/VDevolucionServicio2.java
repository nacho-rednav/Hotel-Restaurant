package Presentacion.Reserva;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Negocio.Reserva.Pair;
import Negocio.Reserva.TLineaPedidoServicios;
import Negocio.Servicio.TServicio;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class VDevolucionServicio2 extends JFrame implements IGUI {
	public Controller ctrl;
	private JPanel panel;

	public VDevolucionServicio2() {
		super("Devolución de servicio");
		panel = new JPanel(new GridLayout(12, 4));
		ctrl = Controller.obtenerInstancia();
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void update(Object data) {
		
		TLineaPedidoServicios lineapedidos = (TLineaPedidoServicios) data;
		ArrayList<Pair<TServicio, Date>> pedido = lineapedidos.getIdsServicios();
		JRadioButton[] rb = new JRadioButton[pedido.size()];
		JLabel lNH = new JLabel("Servicios: ");
		panel.add(lNH);
        for(int i = 0; i < pedido.size(); i++){		    
			rb[i] = new JRadioButton(pedido.get(i).getFirst().getTipo());
			panel.add(rb[i]);
		}
		
		
		//Panel de botones
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(buttonsPanel);

		//Boton OK
		JButton okB = new JButton("OK");
		buttonsPanel.add(okB);

		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Pair<TServicio,Date>> devpedido = new ArrayList<Pair<TServicio,Date>>();
				
				for(int i = 0; i < rb.length; i++){
					
					if(rb[i].isSelected()){
						devpedido.add(new Pair<TServicio, Date>(pedido.get(i).getFirst(), pedido.get(i).getSecond()));
					}
				}
		        
		        
		        TLineaPedidoServicios lineapedidosadevolver = new TLineaPedidoServicios(lineapedidos.getIdReserva(), devpedido); 
				ctrl.accion(Events.DEVOLUCION_SERVICIO_RESERVA2, lineapedidosadevolver); 
				setVisible(false);
			}
		});

		//Boton Cancelar
		JButton cancelB = new JButton("Cancel");
		buttonsPanel.add(cancelB);

		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VRESERVA, null);
			}
		});

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}