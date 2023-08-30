package Presentacion.Ingrediente.VIngredienteCasosUso;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Negocio.Ingrediente.TIngrediente;
import Presentacion.Command.Context;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Ingrediente.VIngrediente;

@SuppressWarnings("serial")
public class VModificarIngrediente extends JFrame implements IGUI {
	private Controller ctrl;
	
	// Variables de ingrediente.
	private String nombre;
	private int id;
	private boolean alergeno;
	
	private JButton returnButton;
	
	public VModificarIngrediente() {
		super("Modificar Ingrediente");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(infoPanel);
		
		// IDENTIFICADOR
		JPanel idInfo = new JPanel();
		infoPanel.add(idInfo);
		idInfo.add(new JLabel("ID del ingrediente a modificar: "));
		idInfo.add(idSpinner());
		
		// Para los botones
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);
		buttonsPanel.add(okButton());
		buttonsPanel.add(cancelButton());
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	
	JSpinner idSpinner() {
		JSpinner idSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idSpin.setPreferredSize(new Dimension(50, 20));
		id = (Integer) idSpin.getValue();
		idSpin.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				id = (Integer) idSpin.getValue();
			}
			
		});
		return idSpin;
	}
	

	
	JButton okButton() {
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MODIFICAR_INGREDIENTE, id);
			}
		});
		
		return okB;
	}
	
	JButton cancelButton(){
		JButton cancelB = new JButton("Cancel");
		
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VINGREDIENTE, null);
			}
		});
		
		return cancelB;
	}
	




	@Override
	public void update(Object data) {
	}
}