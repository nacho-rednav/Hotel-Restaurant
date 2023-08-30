/**
 * 
 */
package Presentacion.Servicio.VServicioCasosUso;

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

public class VMostrarServicioPorTipo extends JFrame implements IGUI {
	private Controller ctrl;
	private String _tipo;
	
	public VMostrarServicioPorTipo() {
		super("Mostrar Servicio");
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
		idInfoPanel.add(new JLabel("Tipo: "));
		JTextField tipoText= new JTextField();
		idInfoPanel.add(tipoText);
		tipoText.setPreferredSize(new Dimension(100, 20));
		_tipo = (String) tipoText.getText();
		tipoText.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            _tipo = tipoText.getText();
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	_tipo= tipoText.getText();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	_tipo= tipoText.getText();
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
				ctrl.accion(Events.MOSTRAR_SERVICIO_TIPO, _tipo);
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
				ctrl.accion(Events.ABRIR_VSERVICIO, null);
			}
		});

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	public void update(Object data) {

	}
}