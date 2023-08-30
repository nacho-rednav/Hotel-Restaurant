package Presentacion.Launcher;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Presentacion.Controller.IGUI;

public class VDialogo extends JFrame implements IGUI {

	private int messageType;
	private String titulo;
	
	public VDialogo(int messageType, String titulo) {
		this.messageType = messageType;
		this.titulo = titulo;
	}
	
	@Override
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, data, titulo, messageType);
	}

}
