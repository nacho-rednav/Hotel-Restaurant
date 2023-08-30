package Presentacion.Reserva;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import java.util.Set;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Reserva.TLineaPedidoHabitacion;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class VDesvincularHabitacionReserva extends JFrame implements IGUI {
	public Controller ctrl;
	private int id_reserva;
	private int id_habitacion;

	public VDesvincularHabitacionReserva() {
		super("Desvincular habitación de reserva");
		ctrl = Controller.obtenerInstancia();

		initIGUI();
	}

	public void initIGUI() {
		//Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);

		//Panel de datos
		JPanel idInfoPanel = new JPanel();
		mainPanel.add(idInfoPanel);

		//IDReserva
		idInfoPanel.add(new JLabel("ID Reserva: "));
		JSpinner idSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(idSpin);
		idSpin.setPreferredSize(new Dimension(50, 20));
		id_reserva = (Integer) idSpin.getValue();
		idSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				id_reserva = (Integer) idSpin.getValue();
			}
		});

		//IDHabitacion
		idInfoPanel.add(new JLabel("ID Habitación: "));
		JSpinner idhSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(idhSpin);
		idhSpin.setPreferredSize(new Dimension(50, 20));
		id_habitacion = (Integer) idhSpin.getValue();
		idhSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				id_habitacion = (Integer) idhSpin.getValue();
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
				ctrl.accion(Events.DESVINCULAR_HABITACION_RESERVA, new TLineaPedidoHabitacion(id_reserva, id_habitacion));
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

	public void update(Object data) {

	}
}