package Presentacion.Turno;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Turno.TTurnoCliente;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEliminarClienteATurno extends JFrame implements IGUI {
	private int _idCliente;
	private int _idTurno;
	private Controller controller;

	public VEliminarClienteATurno() {
		super("Eliminar Cliente a Turno");
		controller = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
        
		JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(infoPanel);
		
		//IDCliente
		JPanel idClienteInfo = new JPanel();
		infoPanel.add(idClienteInfo, BorderLayout.PAGE_END);
		idClienteInfo.add(new JLabel("ID Cliente: "));
		idClienteInfo.add(idClienteSpinner());
		
		//IDTurno
		JPanel idTurnoInfo = new JPanel();
		infoPanel.add(idTurnoInfo, BorderLayout.PAGE_END);
		idTurnoInfo.add(new JLabel("ID Turno: "));
		idTurnoInfo.add(idTurnoSpinner());
		
		//Metemos los botones
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonPanel);
		buttonPanel.add(okButton());
		buttonPanel.add(cancelButton());
				
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	JSpinner idClienteSpinner() {
		JSpinner idClienteSpin =new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idClienteSpin.setPreferredSize(new Dimension(50, 20));
		_idCliente = (Integer) idClienteSpin.getValue();
		idClienteSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_idCliente = (Integer) idClienteSpin.getValue();
			}
		});
		return idClienteSpin;
	}
	
	JSpinner idTurnoSpinner() {
		JSpinner idTurnoSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idTurnoSpin.setPreferredSize(new Dimension(50, 20));
		_idTurno = (Integer) idTurnoSpin.getValue();
		idTurnoSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_idTurno = (Integer) idTurnoSpin.getValue();
			}
		});
		return idTurnoSpin;
	}
	
	JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controller.accion(Events.DESVINCULAR_TURNO_CLIENTE, new TTurnoCliente(_idTurno, _idCliente));
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
				controller.accion(Events.ABRIR_VTURNO, null);
			}
		});
		
		return cancelB;
	}
	
	public void update(Object data) {
	}

}
