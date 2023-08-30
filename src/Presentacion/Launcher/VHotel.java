package Presentacion.Launcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VHotel extends JFrame implements IGUI {
	private JButton habitButton;
	private JButton returnButton;
	private JButton recepcionistaButton;
	private JButton clienteButton;
	private JButton reservaButton;
	private JButton turnoButton;

	private Controller ctrl;

	public VHotel() {
		super("Hotel");
		ctrl = Controller.obtenerInstancia();
		initGUI();
	}

	public void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(700, 200));
		setContentPane(mainPanel);

		JPanel botonesPanel = new JPanel();
		botonesPanel.setPreferredSize(new Dimension(700, 300));
		mainPanel.add(botonesPanel, BorderLayout.CENTER);

		// RETURN BUTTON
		returnButton = new JButton();
		ImageIcon icon = new ImageIcon("icons/back.png");
		Image newImg = icon.getImage().getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		returnButton.setIcon(icon);
		returnButton.setPreferredSize(new Dimension(55, 50));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VPRINCIPAL, null);
			}
		});
		botonesPanel.add(returnButton);
		// HABITACION BUTTON
		habitButton = new JButton("HABTACION");
		habitButton.setPreferredSize(new Dimension(150, 50));
		habitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VHABITACION, null);
				setVisible(false);
			}
		});
		botonesPanel.add(habitButton);

		// RECEPCIONISTA BUTTON
		recepcionistaButton = new JButton("RECEPCIONISTA");
		recepcionistaButton.setPreferredSize(new Dimension(150, 50));
		recepcionistaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VRECEPCIONISTA, null);
				setVisible(false);
			}
		});
		botonesPanel.add(recepcionistaButton);
		// SERVICIO BUTTON
		recepcionistaButton = new JButton("SERVICIO");
		recepcionistaButton.setPreferredSize(new Dimension(150, 50));
		recepcionistaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VSERVICIO, null);
				setVisible(false);
			}
		});
		botonesPanel.add(recepcionistaButton);

		// CLIENTE BUTTON
		clienteButton = new JButton("CLIENTE");
		clienteButton.setPreferredSize(new Dimension(150, 50));
		clienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VCLIENTE, null);
				setVisible(false);
			}
		});
		botonesPanel.add(clienteButton);
		
		// RESERVA BUTTON
		reservaButton = new JButton("RESERVA");
		reservaButton.setPreferredSize(new Dimension(150, 50));
		reservaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VRESERVA, null);
				setVisible(false);
			}
		});
		botonesPanel.add(reservaButton);
		
		// TURNO BUTTON
		turnoButton = new JButton("TURNO");
		turnoButton.setPreferredSize(new Dimension(150, 50));
		turnoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VTURNO, null);
				setVisible(false);
			}
		});
		botonesPanel.add(turnoButton);

		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 350,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 100);
		setVisible(true);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void update(Object data) {
		// TODO Auto-generated method stub

	}

}