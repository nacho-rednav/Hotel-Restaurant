package Presentacion.Factura.VFacturaCasosUsos;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Factura.TLineaDePedido;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEliminarMenusFactura extends JFrame implements IGUI {
	public Controller ctrl;
	private Integer id_menu;
	private Integer id_factura;
	private Integer cantidad;
	
	
	public VEliminarMenusFactura() {
		super("Eliminar menus factura");
		ctrl = Controller.obtenerInstancia();
		
		initIGUI();
	}
	
	public void initIGUI(){
		//Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);
		
		
		
		// Panel de datos
		JPanel idInfoPanel = new JPanel();
		mainPanel.add(idInfoPanel);


		// Id_menu
		idInfoPanel.add(new JLabel("ID de menu: "));
		JSpinner idMenuSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(idMenuSpin);
		idMenuSpin.setPreferredSize(new Dimension(50, 20));
		id_menu = (Integer) idMenuSpin.getValue();
		idMenuSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				id_menu = (Integer) idMenuSpin.getValue();
			}
		});

		// Id_factura
		idInfoPanel.add(new JLabel("ID de factura: "));
		JSpinner idFactSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(idFactSpin);
		idFactSpin.setPreferredSize(new Dimension(50, 20));
		id_factura = (Integer) idFactSpin.getValue();
		idFactSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				id_factura = (Integer) idFactSpin.getValue();
			}
		});
		
		// Cantidad
		idInfoPanel.add(new JLabel("Cantidad: "));
		JSpinner cantSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(cantSpin);
		cantSpin.setPreferredSize(new Dimension(50, 20));
		cantidad = (Integer) cantSpin.getValue();
		cantSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				cantidad = (Integer) cantSpin.getValue();
			}
		});
		
		//Panel de botones
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);
		
		
		//Boton OK
		JButton okB = new JButton("OK");
		buttonsPanel.add(okB);
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ELIMINAR_MENUS_FACTURA, new TLineaDePedido(id_menu, cantidad, id_factura, null));
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
				ctrl.accion(Events.ABRIR_VFACTURA, null);
			}
		});
		
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	
	
	public void update(Object data) {
		
	}
}