package Presentacion.Launcher;

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

public class VComidas extends JFrame implements IGUI{
	private Controller ctrl;
	private final Dimension FrameDim = new Dimension(500, 180);
	private final Dimension ButtDim = new Dimension(150, 50);
	
	private JButton ingButton;
	private JButton prodButton;
	private JButton menuButton;
	private JButton returnButton;


	public VComidas() {
		super("Comidas");
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
				ctrl.accion(Events.ABRIR_VRESTAURANTE, null);
			}
		});
		botonesPanel.add(returnButton);
		// INGREDIENTE BUTTON
		ingButton = new JButton("INGREDIENTES");
		ingButton.setPreferredSize(ButtDim);
		ingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VINGREDIENTE, null);
				setVisible(false);
			}
		});
		botonesPanel.add(ingButton);
		
		// PRODUCTOS BUTTON
		prodButton = new JButton("PRODUCTOS");
		prodButton.setPreferredSize(ButtDim);
		prodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VPRODUCTO, null);
				setVisible(false);
			}
		});
		
		botonesPanel.add(prodButton);
		
		// MENU BUTTON
		menuButton = new JButton("MENUS");
		menuButton.setPreferredSize(ButtDim);
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VMENU, null);
				setVisible(false);
			}
		});
		botonesPanel.add(menuButton);
		
		setVisible(true);
		setLocationRelativeTo(getParent());
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void update(Object datos) {
		// TODO Auto-generated method stub
		
	}
}

