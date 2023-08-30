package Presentacion.Reserva;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Reserva.TReserva;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VCrearReserva extends JFrame implements IGUI {
	public Controller ctrl;
	private int _id;
	private int codigo;
	private Date fechaInicio;
	private int dias;
	private int id_cliente;
	private int id_recepcionista;
	private int num_habitaciones;

	public VCrearReserva() {
		super("Alta reserva");
		ctrl = Controller.obtenerInstancia();
		initIGUI();
	}

	public void initIGUI() {
		//Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);

		// Panel de datos
		JPanel idInfoPanel = new JPanel();
		mainPanel.add(idInfoPanel);

		// Codigo
		idInfoPanel.add(new JLabel("Codigo: "));
		JSpinner plantaSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(plantaSpin);
		plantaSpin.setPreferredSize(new Dimension(50, 20));
		codigo = (Integer) plantaSpin.getValue();
		plantaSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				codigo = (Integer) plantaSpin.getValue();
			}
		});

		// Dias
		idInfoPanel.add(new JLabel("Dias: "));
		JSpinner numeroSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(numeroSpin);
		numeroSpin.setPreferredSize(new Dimension(50, 20));
		dias = (Integer) numeroSpin.getValue();
		numeroSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				dias = (Integer) numeroSpin.getValue();
			}
		});
		
		// IDCliente
		idInfoPanel.add(new JLabel("ID Cliente: "));
		JSpinner clienteSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(clienteSpin);
		clienteSpin.setPreferredSize(new Dimension(50, 20));
		id_cliente = (Integer) clienteSpin.getValue();
		clienteSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				id_cliente = (Integer) clienteSpin.getValue();
			}
		});
		
		// IDRecepcionista
		idInfoPanel.add(new JLabel("ID Recepcionista: "));
		JSpinner reSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(reSpin);
		reSpin.setPreferredSize(new Dimension(50, 20));
		id_recepcionista = (Integer) reSpin.getValue();
		reSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				id_recepcionista = (Integer) reSpin.getValue();
			}
		});

		//Panel de botones
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);

		//Boton OK
		JButton okB = new JButton("OK");
		buttonsPanel.add(okB);

		//FechaInicio
		fechaInicio = new Date(Calendar.getInstance().getTimeInMillis());
		
		// Num_habitaciones
		idInfoPanel.add(new JLabel("Numero de habitaciones: "));
		JSpinner num_hSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(num_hSpin);
		num_hSpin.setPreferredSize(new Dimension(50, 20));
		num_habitaciones = (Integer) num_hSpin.getValue();
		num_hSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				num_habitaciones = (Integer) num_hSpin.getValue();
			}
		});
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.CREAR_RESERVA, new TReserva(_id, codigo, dias, id_cliente, id_recepcionista, num_habitaciones, fechaInicio, true));
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
		setVisible(true);
	}
}