package Presentacion.Factura;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VFactura extends JFrame implements IGUI {

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
	private final Dimension FrameDim = new Dimension(500, 330);
	private JButton returnButton;
	private Controller ctrl;

	public VFactura() {
		super("Vista Factura");
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
				ctrl.accion(Events.ABRIR_VRESTAURANTE, null);
			}
		});

		botonesPanel.add(returnButton);
		botonesPanel.add(crearButton());
		botonesPanel.add(editarButton());
		botonesPanel.add(cerrarButton());
		botonesPanel.add(mostUnoButton());
		botonesPanel.add(mostTodosButton());
		botonesPanel.add(mostMesaButton());
		botonesPanel.add(mostEmpButton());
		botonesPanel.add(anyadirMenuButton());
		botonesPanel.add(eliminarMenuButton());
		botonesPanel.add(devolverMenuButton());

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	JButton crearButton() {
		JButton crearB = new JButton("CREAR");
		crearB.setPreferredSize(ButtDim);
		crearB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VALTA_FACTURA, null);
				setVisible(false);
			}
		});
		return crearB;
	}

	JButton cerrarButton() {
		JButton eliminarB = new JButton("CERRAR");
		eliminarB.setPreferredSize(ButtDim);
		eliminarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VCERRAR_FACTURA, null);
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
				ctrl.accion(Events.ABRIR_VMODIFICAR_FACTURA, null);
				setVisible(false);
			}
		});
		return editarB;
	}

	JButton mostUnoButton() {
		JButton mostUnoB = new JButton("MOSTRAR UNA");
		mostUnoB.setPreferredSize(ButtDim);
		mostUnoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_UNA_FACTURA, null);
				setVisible(false);
			}
		});
		return mostUnoB;
	}

	JButton mostTodosButton() {
		JButton mostTodosB = new JButton("MOSTRAR TODAS");
		mostTodosB.setPreferredSize(ButtDim);
		mostTodosB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MOSTRAR_FACTURA_TODAS, null);
				setVisible(false);
			}
		});
		return mostTodosB;
	}

	JButton mostMesaButton() {
		JButton mostMesaB = new JButton("MOSTRAR POR MESA");
		mostMesaB.setPreferredSize(new Dimension(200, 50));
		mostMesaB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_POR_MESA, null);
				setVisible(false);
			}
		});
		return mostMesaB;
	}

	JButton mostEmpButton() {
		JButton mostEmpB = new JButton("MOSTRAR POR EMPLEADO");
		mostEmpB.setPreferredSize(new Dimension(200, 50));
		mostEmpB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_POR_EMPLEADO, null);
				setVisible(false);
			}
		});
		return mostEmpB;
	}

	JButton anyadirMenuButton() {
		JButton anMenuB = new JButton("ANYADIR MENU FACTURA");
		anMenuB.setPreferredSize(new Dimension(200, 50));
		anMenuB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ELEGIR_MENUS_FACTURA, null);
				setVisible(false);
			}
		});
		return anMenuB;
	}

	JButton eliminarMenuButton() {
		JButton elMenuB = new JButton("ELIMINAR MENU FACTURA");
		elMenuB.setPreferredSize(new Dimension(200, 50));
		elMenuB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VELIMINAR_MENUS_FACTURA, null);
				setVisible(false);
			}
		});
		return elMenuB;
	}

	JButton devolverMenuButton() {
		JButton devMenuB = new JButton("DEVOLVER MENU FACTURA");
		devMenuB.setPreferredSize(new Dimension(200, 50));
		devMenuB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VDEVOLVER_MENU, null);
				setVisible(false);
			}
		});
		return devMenuB;
	}

	public void update(Object data) {

	}
}