package Presentacion.Habitacion;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class VHabitacion extends JFrame implements IGUI {

	private final Dimension ButtDim = new Dimension(150, 50);
	private final Dimension FrameDim = new Dimension(500, 230);
	private JButton returnButton;
	private Controller ctrl;

	public VHabitacion() {
		super("Vista Habitacion");
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
		botonesPanel.add(mostIdButton());
		botonesPanel.add(mostNumButton());
		botonesPanel.add(mostTodosButton());

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
				ctrl.accion(Events.ABRIR_VCREAR_HABITACION, null);
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
				ctrl.accion(Events.ABRIR_VBAJA_HABITACION, null);
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
				ctrl.accion(Events.ABRIR_VMODIFICAR_HABITACION, null);
				setVisible(false);
			}
		});
		return editarB;
	}

	//MOSTRAR POR ID BUTTON
	JButton mostIdButton() {
		JButton mostUnoB = new JButton("MOSTRAR POR ID");
		mostUnoB.setPreferredSize(ButtDim);
		mostUnoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_HABITACION_ID, null);
				setVisible(false);
			}
		});
		return mostUnoB;
	}
	
	// MOSTRAR POR NUM BUTTON
	JButton mostNumButton() {
		JButton mostUnoB = new JButton("MOSTRAR POR NUMERO");
		mostUnoB.setPreferredSize(new Dimension(200, 50));
		mostUnoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_HABITACION_NUMERO, null);
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
				ctrl.accion(Events.MOSTRAR_HABITACION_TODOS, null);
				setVisible(false);
			}
		});
		return mostTodosB;
	}

	public void update(Object data) {

	}
}