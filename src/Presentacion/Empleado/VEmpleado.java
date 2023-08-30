package Presentacion.Empleado;

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

@SuppressWarnings("serial")
public class VEmpleado extends JFrame implements IGUI {
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
	private final Dimension FrameDim = new Dimension(500, 220);
	private Controller ctrl;
	private JButton returnButton;

	public VEmpleado() {
		super("Vista Empleado");
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
				ctrl.accion(Events.ABRIR_VRESTAURANTE, null);
			}
		});
		botonesPanel.add(returnButton);
		botonesPanel.add(crearButton());
		botonesPanel.add(editarButton());
		botonesPanel.add(eliminarButton());
		botonesPanel.add(mostUnoButton());
		botonesPanel.add(mostTodosButton());
		botonesPanel.add(mostRangoButton());

		pack();
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	//CREAR BUTTON
	JButton crearButton() {
		JButton crearButton = new JButton("CREAR");
		crearButton.setPreferredSize(ButtDim);
		crearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VALTA_EMPLEADO, null);
				setVisible(false);
			}
		});
		return crearButton;
	}

	//EDITAR BUTTON
	JButton editarButton() {
		JButton editarButton = new JButton("EDITAR");
		editarButton.setPreferredSize(ButtDim);
		editarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMODIFICAR_EMPLEADO, null);
				setVisible(false);
			}
		});
		return editarButton;
	}

	//ELMINAR BUTTON
	JButton eliminarButton() {
		JButton eliminarButton = new JButton("ELIMINAR");
		eliminarButton.setPreferredSize(ButtDim);
		eliminarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VBAJA_EMPLEADO, null);
				setVisible(false);
			}
		});
		return eliminarButton;
	}

	//MOSTARUNO BUTTON
	JButton mostUnoButton() {
		JButton mostUnoButton = new JButton("MOSTRAR UNO");
		mostUnoButton.setPreferredSize(ButtDim);
		mostUnoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_UN_EMPLEADO, null);
				setVisible(false);
			}
		});
		return mostUnoButton;
	}

	//MOSTAR TODOS BUTTON
	JButton mostTodosButton() {
		JButton mostTodosButton = new JButton("MOSTRAR TODOS");
		mostTodosButton.setPreferredSize(ButtDim);
		mostTodosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MOSTRAR_EMPLEADO_TODOS, null);
				setVisible(false);
			}
		});
		return mostTodosButton;
	}

	//MOSTAR TODOS BUTTON
	JButton mostRangoButton() {
		JButton mostRangoButton = new JButton("MOSTRAR DESPEDIDOS POR RANGO SALARIAL");
		mostRangoButton.setPreferredSize(new Dimension(320, 50));
		mostRangoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_EMPLEADO_RANGO, null);
				setVisible(false);
			}
		});
		return mostRangoButton;
	}

	@Override
	public void update(Object data) {
		// TODO Auto-generated method stub

	}
}