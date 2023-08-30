package Presentacion.Reserva;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VReserva extends JFrame implements IGUI {
	
	private Set<JPanel> jPanel;
	
	private Set<JButton> jButton;
	
	private Set<JLabel> jLabel;
	
	private Set<JTextField> jTextField;
	
	private Set<ActionListener> actionListener;

	private final Dimension ButtDim = new Dimension(150, 50);
	private final Dimension FrameDim = new Dimension(600, 230);
	private JButton returnButton;
	private Controller ctrl;

	public VReserva() {
		super("Vista Reserva");
		ctrl = Controller.obtenerInstancia();
		initGUI();
	}

	public void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setPreferredSize(FrameDim);
		setContentPane(mainPanel);

		JPanel botonesPanel = new JPanel();
		mainPanel.add(botonesPanel);
		//RETURN BUTTON
		returnButton = new JButton();
		ImageIcon icon = new ImageIcon("icons/back.png");
		Image newImg = icon.getImage().getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		returnButton.setIcon(icon);
		returnButton.setPreferredSize(new Dimension(55, 50));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VHOTEL, null);
			}
		});
		botonesPanel.add(returnButton);
		botonesPanel.add(crearButton());
		botonesPanel.add(editarButton());
		botonesPanel.add(eliminarButton());
		botonesPanel.add(mostButton());
		botonesPanel.add(mostTodosButton());
		botonesPanel.add(compServButton());
		botonesPanel.add(desvHabReButton());
		botonesPanel.add(devServButton());

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	//CREAR BUTTON
	JButton crearButton() {
		JButton crearB = new JButton("CREAR");
		crearB.setPreferredSize(ButtDim);
		crearB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VCREAR_RESERVA, null);
				setVisible(false);
			}
		});
		return crearB;
	}

	//ELIMINAR BUTTON
	JButton eliminarButton() {
		JButton eliminarB = new JButton("ELIMINAR");
		eliminarB.setPreferredSize(ButtDim);
		eliminarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VBAJA_RESERVA, null);
				setVisible(false);
			}
		});
		return eliminarB;
	}

	//EDITAR BUTTON
	JButton editarButton() {
		JButton editarB = new JButton("EDITAR");
		editarB.setPreferredSize(ButtDim);
		editarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMODIFICAR_RESERVA, null);
				setVisible(false);
			}
		});
		return editarB;
	}

	//MOSTRAR POR ID BUTTON
	JButton mostButton() {
		JButton mostUnoB = new JButton("MOSTRAR RESERVA");
		mostUnoB.setPreferredSize(ButtDim);
		mostUnoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_RESERVA_ID, null);
				setVisible(false);
			}
		});
		return mostUnoB;
	}

	//MOSTRAR TODOS BUTTON
	JButton mostTodosButton() {
		JButton mostTodosB = new JButton("MOSTRAR TODAS");
		mostTodosB.setPreferredSize(ButtDim);
		mostTodosB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MOSTRAR_RESERVA_TODOS, null);
				setVisible(false);
			}
		});
		return mostTodosB;
	}
	
	// COMPRAR SERVICIO BUTTON
	JButton compServButton() {
		JButton compServButton = new JButton("COMPRAR SERVICIO");
		compServButton.setPreferredSize(ButtDim);
		compServButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VCOMPRA_SERVICIO, null);
				setVisible(false);
			}
		});
		return compServButton;
	}
	
	// DESVINCULAR HABITACION RESERVA BUTTON
	JButton desvHabReButton() {
		JButton desvHabReButton = new JButton("DESVINCULAR HABITACION RESERVA");
		desvHabReButton.setPreferredSize(new Dimension(250, 50));
		desvHabReButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VDESVINCULAR_HABITACION_RESERVA, null);
				setVisible(false);
			}
		});
		return desvHabReButton;
	}
	
	// DEVOLUCION SERVICIO BUTTON
	JButton devServButton() {
		JButton devServButton = new JButton("DEVOLUCION SERVICIO");
		devServButton.setPreferredSize(new Dimension(200, 50));
		devServButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VDEVOLUCION_SERVICIO_RESERVA, null);
				setVisible(false);
			}
		});
		return devServButton;
	}

	public void update(Object data) {

	}
}