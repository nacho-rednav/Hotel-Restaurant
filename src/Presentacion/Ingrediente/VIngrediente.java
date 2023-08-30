package Presentacion.Ingrediente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

//import Presentacion.launcher.VComidas;

@SuppressWarnings("serial")
public class VIngrediente extends JFrame implements IGUI {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML vers JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void _new() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	private final Dimension ButtDim = new Dimension(150, 50);
	private final Dimension FrameDim = new Dimension(750, 250);
	private JButton returnButton;
	private Controller ctrl;

	public VIngrediente() {
		super("Vista Ingrediente");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
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
				ctrl.accion(Events.ABRIR_VCOMIDA, null);
			}
		});
		botonesPanel.add(returnButton);
		botonesPanel.add(crearButton());
		botonesPanel.add(editarButton());
		botonesPanel.add(eliminarButton());
		botonesPanel.add(mostUnoButton());
		botonesPanel.add(mostTodosButton());
		botonesPanel.add(mostUnoPorNombreButton());

		pack();
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	JButton crearButton() {
		JButton crearB = new JButton("CREAR");
		crearB.setPreferredSize(ButtDim);
		crearB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VALTA_INGREDIENTE, null);
				setVisible(false);
			}
		});
		return crearB;
	}

	JButton eliminarButton() {
		JButton eliminarB = new JButton("ELIMINAR");
		eliminarB.setPreferredSize(ButtDim);
		eliminarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VBAJA_INGREDIENTE, null);
				setVisible(false);
			}
		});
		return eliminarB;
	}

	JButton editarButton() {
		JButton editarB = new JButton("EDITAR");
		editarB.setPreferredSize(ButtDim);
		editarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMODIFICAR_INGREDIENTE, null);
				setVisible(false);
			}
		});
		return editarB;
	}

	JButton mostUnoButton() {
		JButton mostUnoB = new JButton("MOSTRAR UNO");
		mostUnoB.setPreferredSize(ButtDim);
		mostUnoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_UN_INGREDIENTE, null);
				setVisible(false);
			}
		});
		return mostUnoB;
	}

	JButton mostUnoPorNombreButton() {
		JButton mostUnoB = new JButton("MOSTRAR UNO POR NOMBRE");
		mostUnoB.setPreferredSize(new Dimension(210, 50));
		mostUnoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_UN_INGREDIENTE_POR_NOMBRE, null);
				setVisible(false);
			}
		});
		return mostUnoB;
	}

	JButton mostTodosButton() {
		JButton mostTodosB = new JButton("MOSTRAR TODOS");
		mostTodosB.setPreferredSize(ButtDim);
		mostTodosB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_INGREDIENTE_TODOS, null);
				setVisible(false);
			}
		});
		return mostTodosB;
	}

	@Override
	public void update(Object data) {
	}
}