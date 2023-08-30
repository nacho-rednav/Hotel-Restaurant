package Presentacion.Servicio;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VServicio extends JFrame implements IGUI {

	private Controller ctrl;
	private final Dimension FrameDim = new Dimension(500, 230);
	private final Dimension ButtDim = new Dimension(150, 50);
	
	private JButton returnButton;
	private JButton crearButton;
	private JButton eliminarButton;
	private JButton editarButton;
	private JButton mostrarPorIdButton;
	private JButton mostrarPorNombreButton;
	private JButton mostrarReservasButton;
	private JButton mostrarTodosButton;


	public void _new() {
	}
	
	public VServicio() { 
		super("Vista Servicio");
		ctrl = Controller.obtenerInstancia();
		initGUI();
		setLocationRelativeTo(getParent());
	}

	public void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(FrameDim);
		setContentPane(mainPanel);

		JPanel botonesPanel = new JPanel();
		botonesPanel.setPreferredSize(ButtDim);
		mainPanel.add(botonesPanel, BorderLayout.CENTER);
		
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
		
		// CREAR BUTTON
		crearButton = new JButton("CREAR");
		crearButton.setPreferredSize(ButtDim);
		crearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VALTA_SERVICIO, null);
				setVisible(false);
			}
		});
		botonesPanel.add(crearButton);
		
		// ELIMINAR BUTTON
		eliminarButton = new JButton("ELIMINAR");
		eliminarButton.setPreferredSize(ButtDim);
		eliminarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VBAJA_SERVICIO, null);
				setVisible(false);
			}
		});
		
		botonesPanel.add(eliminarButton);
		
		// EDITAR BUTTON
		editarButton = new JButton("EDITAR");
		editarButton.setPreferredSize(ButtDim);
		editarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMODIFICAR_SERVICIO, null);
				setVisible(false);
			}
		});
		botonesPanel.add(editarButton);
		
		// MOSTERAR_POR_ID BUTTON
		mostrarPorIdButton = new JButton("MOSTRAR POR ID");
		mostrarPorIdButton.setPreferredSize(ButtDim);
		mostrarPorIdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_POR_ID_SERVICIO, null);
				setVisible(false);
			}
		});
		botonesPanel.add(mostrarPorIdButton);
		
		// MOSTERAR_POR_TIPO BUTTON
		mostrarPorNombreButton = new JButton("MOSTRAR POR TIPO");
		mostrarPorNombreButton.setPreferredSize(new Dimension(200, 50));
		mostrarPorNombreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_POR_TIPO_SERVICIO, null);
				setVisible(false);
			}
		});
		botonesPanel.add(mostrarPorNombreButton);
		
		// MOSTERAR_RESERVAS BUTTON
		mostrarReservasButton = new JButton("MOSTRAR RESERVAS");
		mostrarReservasButton.setPreferredSize(new Dimension(200, 50));
		mostrarReservasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMOSTRAR_SERVICIO_RESERVAS, null);
				setVisible(false);
			}
		});
		botonesPanel.add(mostrarReservasButton);
		
		// MOSTERAR_TODOS BUTTON
		mostrarTodosButton = new JButton("MOSTRAR TODOS");
		mostrarTodosButton.setPreferredSize(ButtDim);
		mostrarTodosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MOSTRAR_SERVICIO_TODOS  , null);
				setVisible(false);
			}
		});
		botonesPanel.add(mostrarTodosButton);
		
		setVisible(true);
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 350,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 100);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void update(Object data) {
	}
}