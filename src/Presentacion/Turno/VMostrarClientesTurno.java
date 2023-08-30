package Presentacion.Turno;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

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

import Negocio.Turno.TTurno;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarClientesTurno extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	private Controller ctrl;
	private int _id;
	
	public VMostrarClientesTurno() {
		super("Elegir un turno");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initIGUI();
			}
		});
	}
	
	private void initIGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);
		
		//Panel de datos
		JPanel idInfoPanel = new JPanel();
		mainPanel.add(idInfoPanel);
		
		//ID
		idInfoPanel.add(new JLabel("ID: "));
		JSpinner idTurnoSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(idTurnoSpin);
		idTurnoSpin.setPreferredSize(new Dimension(50, 20));
		_id = (Integer) idTurnoSpin.getValue();
		idTurnoSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_id = (Integer) idTurnoSpin.getValue();
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
				ctrl.accion(Events.MOSTRAR_TURNO_CLIENTES, _id);
				setVisible(false);
			}
		});
		
		//CANCEL BUTTON
		JButton cancelButton = new JButton("Cancel");
		buttonsPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VTURNO, null);

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
