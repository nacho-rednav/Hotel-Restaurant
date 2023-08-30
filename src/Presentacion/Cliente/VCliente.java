package Presentacion.Cliente;

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
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VCliente extends JFrame implements IGUI {
	private Set<JPanel> jPanel;

	private Set<JButton> jButton;

	private Set<JLabel> jLabel;

	private Set<JTextField> jTextField;

	private Set<ActionListener> actionListener;
	private final Dimension ButtDim = new Dimension(150, 50);
	private final Dimension FrameDim = new Dimension(600, 230);
	private JButton returnButton;
	private Controller ctrl;
	
	/*public static void main(String[] args) {
		new VCliente();
		
	}*/
	public VCliente() {
		super("Vista Cliente");
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
		
		botonesPanel.add(returnButton());
		botonesPanel.add(crearButton());
		botonesPanel.add(editarButton());
		botonesPanel.add(eliminarButton());
		botonesPanel.add(mostUnoButton());
		botonesPanel.add(mostTodosButton());
		botonesPanel.add(calcPrecioTurnosButton());
		botonesPanel.add(mostrarTurnosCliente());

		pack();
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	// RETURN BUTTON
	JButton returnButton() {
		JButton returnA = new JButton();
		ImageIcon icon = new ImageIcon("icons/back.png");
		Image newImg = icon.getImage().getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		returnA.setIcon(icon);
		returnA.setPreferredSize(new Dimension(55, 50));
		returnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VHOTEL, null);
			}
		});
		return returnA;
	}

	// CREAR BUTTON
	JButton crearButton() {
		JButton crearB = new JButton("CREAR");
		crearB.setPreferredSize(ButtDim);
		crearB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VALTA_CLIENTE, null);
				setVisible(false);
			}
		});
		pack();
		return crearB;
	}

	// ELIMINAR BUTTON
	JButton eliminarButton() {
		JButton eliminarB = new JButton("ELIMINAR");
		eliminarB.setPreferredSize(ButtDim);
		eliminarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VBAJA_CLIENTE, null);
				setVisible(false);
			}
		});
		pack();
		return eliminarB;
	}

	// EDITAR BUTTON
	JButton editarButton() {
		JButton editarB = new JButton("EDITAR");
		editarB.setPreferredSize(ButtDim);
		editarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMODIFICAR_CLIENTE, null);
				setVisible(false);
			}
		});
		pack();
		return editarB;
	}

	// MOSTRAR UNO BUTTON
	JButton mostUnoButton() {
		JButton mostUnoB = new JButton("MOSTRAR UNO");
		mostUnoB.setPreferredSize(ButtDim);
		mostUnoB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_UN_CLIENTE, null);
				setVisible(false);
			}
		});
		pack();
		return mostUnoB;
	}

	// MOSTRAR TODOS BUTTON
	JButton mostTodosButton() {
		JButton mostTodosB = new JButton("MOSTRAR TODOS");
		mostTodosB.setPreferredSize(ButtDim);
		mostTodosB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ctrl.accion(Events.ABRIR_VMOSTRAR_CLIENTE_TODOS, null);
				ctrl.accion(Events.MOSTRAR_TODOS_CLIENTE, null);
				setVisible(false);
			}
		});
		pack();
		return mostTodosB;
	}

	
	JButton calcPrecioTurnosButton() {
		JButton calcPrecioB = new JButton("CALCULAR PRECIO TURNOS");
		calcPrecioB.setPreferredSize(new Dimension(240, 50));
		calcPrecioB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VCALCULAR_PRECIO_TURNOS, null);
				setVisible(false);
			}
		});
		pack();
		return calcPrecioB;
	}
	
	
	// MOSTRAR TURNOS DE CLIENTE
	JButton mostrarTurnosCliente() {
		JButton mostrarTurnos = new JButton("MOSTRAR TURNOS DE CLIENTE");
		mostrarTurnos.setPreferredSize(new Dimension(240, 50));
		mostrarTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_TURNOS_DE_CLIENTE, null);
				setVisible(false);
			}
		});
		pack();
		return mostrarTurnos;
	}

	public void update(Object data) {
	}
}