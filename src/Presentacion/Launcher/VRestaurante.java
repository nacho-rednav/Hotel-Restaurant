package Presentacion.Launcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VRestaurante extends JFrame implements IGUI {
	private JButton factButton;
	private JButton mesaButton;
	private JButton comidaButton;
	private JButton emplButton;
	private JButton returnButton;

	private Controller ctrl;
	public VRestaurante() {
		super("Restaurante");
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

		// FACTURA BUTTON
		factButton = new JButton("FACTURAS");
		factButton.setPreferredSize(new Dimension(100, 50));
		factButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  				ctrl.accion(Events.ABRIR_VFACTURA, null);
				setVisible(false);
			}
		});
		botonesPanel.add(factButton);

		// MESA BUTTON
		mesaButton = new JButton("MESAS");
		mesaButton.setPreferredSize(new Dimension(100, 50));
		mesaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
  				ctrl.accion(Events.ABRIR_VMESA, null);
				setVisible(false);
			}
		});
		botonesPanel.add(mesaButton);

		// COMIDA BUTTON
		comidaButton = new JButton("COMIDAS");
		comidaButton.setPreferredSize(new Dimension(100, 50));
		comidaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VCOMIDA, null);
				setVisible(false);
			}
		});
		botonesPanel.add(comidaButton);
		

		// EMPL BUTTON
		emplButton = new JButton("EMPLEADOS");
		emplButton.setPreferredSize(new Dimension(150, 50));
		emplButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VEMPLEADO, null);
				setVisible(false);
			}
		});
		botonesPanel.add(emplButton);
		
		setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - 350,Toolkit.getDefaultToolkit().getScreenSize().height/2 - 100);
		setVisible(true);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


	@Override
	public void update(Object data) {
		// TODO Auto-generated method stub
		
	}


}