package Presentacion.Mesa.VMesaCasosUso;

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

import Negocio.Mesa.TMesa;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VAltaMesa extends JFrame implements IGUI {
	public Controller ctrl;
	private int _id;
	private int numMesa;
	private int cap;

	public VAltaMesa() {
		super("Alta mesa");
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

		// Num_mesa
		idInfoPanel.add(new JLabel("Numero de mesa: "));
		JSpinner numMesaSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(numMesaSpin);
		numMesaSpin.setPreferredSize(new Dimension(50, 20));
		numMesa = (Integer) numMesaSpin.getValue();
		numMesaSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				numMesa = (Integer) numMesaSpin.getValue();
			}
		});

		// Capacidad
		idInfoPanel.add(new JLabel("Capacidad de mesa: "));
		JSpinner capMesaSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(capMesaSpin);
		capMesaSpin.setPreferredSize(new Dimension(50, 20));
		cap = (Integer) capMesaSpin.getValue();
		capMesaSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				cap = (Integer) capMesaSpin.getValue();
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
				ctrl.accion(Events.ALTA_MESA, new TMesa(_id, numMesa, true, cap, true));
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
				ctrl.accion(Events.ABRIR_VMESA, null);
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