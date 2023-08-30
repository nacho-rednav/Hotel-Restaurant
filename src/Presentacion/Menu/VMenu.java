package Presentacion.Menu;

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
public class VMenu extends JFrame implements IGUI {
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
	private final Dimension FrameDim = new Dimension(600, 230);
	private JButton returnButton;
	private Controller ctrl;

	public VMenu() {
		super("Vista Menu");
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
		botonesPanel.add(mostRangoButton());
		botonesPanel.add(anyiadirProdButton());
		botonesPanel.add(quitarProdButton());

		pack();
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	//CREAR BUTTON
	JButton crearButton() {
		JButton crearB = new JButton("CREAR");
		crearB.setPreferredSize(ButtDim);
		crearB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VALTA_MENU, null);
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
				ctrl.accion(Events.ABRIR_VBAJA_MENU, null);
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
				ctrl.accion(Events.ABRIR_VMODIFICAR_MENU, null);
				setVisible(false);
			}
		});
		return editarB;
	}

	//MOSTRAR UNO BUTTON
	JButton mostUnoButton() {
		JButton mostUnoB = new JButton("MOSTRAR UNA");
		mostUnoB.setPreferredSize(ButtDim);
		mostUnoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_UN_MENU, null);
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
				ctrl.accion(Events.ABRIR_VMOSTRAR_MENU_TODOS, null);
				setVisible(false);
			}
		});
		return mostTodosB;
	}

	//MOSTRAR RANGO_PRECIO BUTTON
	JButton mostRangoButton() {
		JButton mostRangoB = new JButton("MOSTRAR POR RANGO DE PRECIO");
		mostRangoB.setPreferredSize(new Dimension(240, 50));
		mostRangoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_MENU_RANGO, null);
				setVisible(false);
			}
		});
		return mostRangoB;
	}

	//AÑADIR PRODUCTO A MENU BUTTON
	JButton anyiadirProdButton() {
		JButton anyadirIngrB = new JButton("ANYADIR PRODUCTO");
		anyadirIngrB.setPreferredSize(new Dimension(200, 50));
		anyadirIngrB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VVINCULAR_PRODUCTO, null);
				setVisible(false);
			}
		});
		return anyadirIngrB;
	}

	//QUITAR PRODUCTO DE MENU BUTTON
	JButton quitarProdButton() {
		JButton quitarIngrB = new JButton("QUITAR PRODUCTO");
		quitarIngrB.setPreferredSize(ButtDim);
		quitarIngrB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VDESVINCULAR_PRODUCTO, null);
				setVisible(false);
			}
		});
		return quitarIngrB;
	}

	@Override
	public void update(Object datos) {
		// TODO Auto-generated method stub
	}
}