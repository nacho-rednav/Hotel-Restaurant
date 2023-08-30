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

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarPorEmpleado extends JFrame implements IGUI {

	public Controller ctrl;
	private int _id;
	private JButton cancelButton;
	
	
	public VMostrarPorEmpleado() {
		super("Mostrar facturas por empleado");
		ctrl = Controller.obtenerInstancia();
		
		initIGUI();

	}
	
	public void initIGUI(){
		// Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);

		// Panel de datos
		JPanel idInfoPanel = new JPanel();
		mainPanel.add(idInfoPanel);

		// ID
		idInfoPanel.add(new JLabel("ID empleado: "));
		JSpinner idSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(idSpin);
		idSpin.setPreferredSize(new Dimension(50, 20));
		_id = (Integer) idSpin.getValue();
		idSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_id = (Integer) idSpin.getValue();
			}
		});

		// Panel de botones
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);

		// Boton OK
		JButton okB = new JButton("OK");
		buttonsPanel.add(okB);

		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MOSTRAR_FACTURA_POR_EMPLEADO, _id);
				setVisible(false);
			}
		});

		// Boton Cancelar
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

	@Override
	public void update(Object data) {
		
	}
	
	

}
