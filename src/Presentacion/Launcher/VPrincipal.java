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

public class VPrincipal extends JFrame implements IGUI {
	private JButton hotelButton;
	private JButton restauranteButton;

	private Controller ctrl;
	public VPrincipal() {
		super("Principal");
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

		// HOTEL BUTTON
		hotelButton = new JButton();
		ImageIcon iconH = new ImageIcon("icons/hotel.png");
		Image newImgH = iconH.getImage().getScaledInstance(160, 140, java.awt.Image.SCALE_SMOOTH);
		iconH = new ImageIcon(newImgH);
		hotelButton.setIcon(iconH);
		hotelButton.setPreferredSize(new Dimension(160, 140));
		hotelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VHOTEL, null);
				setVisible(false);
			}
		});
		botonesPanel.add(hotelButton);
		// RESTAURANTE BUTTON
		restauranteButton = new JButton();
		ImageIcon iconR = new ImageIcon("icons/rest.png");
		Image newImgR = iconR.getImage().getScaledInstance(160, 140, java.awt.Image.SCALE_SMOOTH);
		iconR = new ImageIcon(newImgR);
		restauranteButton.setIcon(iconR);
		restauranteButton.setPreferredSize(new Dimension(160, 140));
		restauranteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VRESTAURANTE, null);
				setVisible(false);
			}
		});
		botonesPanel.add(restauranteButton);
				

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