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
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Factura.TFactura;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VAltaFactura extends JFrame implements IGUI {
	public Controller ctrl;
	private Integer id_mesa;
	private Integer id_empleado;
	
	
	public VAltaFactura() {
		super("Alta factura");
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


		// Id_mesa
		idInfoPanel.add(new JLabel("ID de mesa: "));
		JSpinner idMesaSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(idMesaSpin);
		idMesaSpin.setPreferredSize(new Dimension(50, 20));
		id_mesa = (Integer) idMesaSpin.getValue();
		idMesaSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				id_mesa = (Integer) idMesaSpin.getValue();
			}
		});

		// Id_empleado
		idInfoPanel.add(new JLabel("ID de Empleado: "));
		JSpinner idEmpSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(idEmpSpin);
		idEmpSpin.setPreferredSize(new Dimension(50, 20));
		id_empleado = (Integer) idEmpSpin.getValue();
		idEmpSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				id_empleado = (Integer) idEmpSpin.getValue();
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
				ctrl.accion(Events.ALTA_FACTURA, new TFactura(0, id_mesa, id_empleado, null, null, true));
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
