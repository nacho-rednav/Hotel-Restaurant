/**
 * 
 */
package Presentacion.Recepcionista.VRecepcionistaCasosUso;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VMostrarPorNombreRecepcionista extends JFrame implements IGUI {
	private Controller ctrl;
	private String _nombre;
	
	public VMostrarPorNombreRecepcionista() {
		super("Mostrar recepcionista");
		ctrl = Controller.obtenerInstancia();

		initIGUI();

	}
	
	public void initIGUI() {
		//Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);

		//Panel de datos
		JPanel idInfoPanel = new JPanel();
		mainPanel.add(idInfoPanel);

		//NOMBRE
		idInfoPanel.add(new JLabel("Nombre: "));
		JTextField nombreText= new JTextField();
		idInfoPanel.add(nombreText);
		nombreText.setPreferredSize(new Dimension(100, 20));
		_nombre = (String) nombreText.getText();
		nombreText.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            _nombre= nombreText.getText();
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	  _nombre= nombreText.getText();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	  _nombre= nombreText.getText();
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
				ctrl.accion(Events.MOSTRAR_RECEPCIONISTA_NOMBRE, _nombre);
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
				ctrl.accion(Events.ABRIR_VRECEPCIONISTA, null);
			}
		});

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	public void update(Object data) {

	}
}