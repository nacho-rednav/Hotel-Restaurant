
package Presentacion.Cliente;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFrame;


public class VCalcularPrecioTurnos extends JFrame implements IGUI {
	
	private Set<JPanel> jPanel;
	
	private Set<JButton> jButton;
	
	private Set<JLabel> jLabel;
	
	private Set<JTextField> jTextField;
	
	private Set<JFrame> jFrame;
	
	private Controller controller;
	private int _id;	
	/*public static void main(String[] args) {
		new VCalcularPrecioTurnos();
		
	}*/
	public VCalcularPrecioTurnos() {
		super("Calcular precio turnos");
		controller = Controller.obtenerInstancia();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initIGUI();
			}
		});
	}
	public void initIGUI() {
		//Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);

		//Panel de datos
		JPanel idInfoPanel = new JPanel();
		mainPanel.add(idInfoPanel);
		
		//ID
		idInfoPanel.add(new JLabel("ID: "));
		JSpinner idSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(idSpin);
		idSpin.setPreferredSize(new Dimension(50, 20));
		_id = (Integer) idSpin.getValue();
		idSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_id = (Integer) idSpin.getValue();
			}
		});		
		
		//Panel de botones
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);

		//Boton OK
		JButton okB = new JButton("OK");
		buttonsPanel.add(okB);

		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.accion(Events.CALCULAR_PRECIO_TURNOS, _id);
				setVisible(false);
			}
		});

		//Boton Cancelar
		JButton cancelB = new JButton("Cancel");
		buttonsPanel.add(cancelB);

		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controller.accion(Events.ABRIR_VCLIENTE, null);
			}
		});

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	public void update(Object data) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}