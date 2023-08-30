package Presentacion.Recepcionista.VRecepcionistaCasosUso;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Negocio.Recepcionista.TRecepcionista;

public class VCrearRecepcionista extends JFrame implements IGUI {
	private Controller ctrl;
	private String _nombre;
	private String _sueldo;
	private Integer numero;
	
	//atributos ayudantes para m�todos
	private Boolean hayLetras;
	private Float floatVal;
	
	public VCrearRecepcionista(){
		super("Alta de nuevo recepcionista");
		ctrl = Controller.obtenerInstancia();
		floatVal = (float) 0.0;
		initGUI();
	}
	
	public void initGUI() {
		//Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);

		// Panel de datos
		JPanel idInfoPanel = new JPanel();
		mainPanel.add(idInfoPanel);
		
		//nombre del recepcionista
		JPanel nombreInfo =new JPanel();
		idInfoPanel.add(nombreInfo, BorderLayout.PAGE_START);
		nombreInfo.add(new JLabel("Nombre: "));
		nombreInfo.add(nombreField());
		
		//salario del recepcionista
		JPanel sueldoInfo =new JPanel();
		idInfoPanel.add(sueldoInfo);
		sueldoInfo.add(new JLabel("Sueldo: "));
		sueldoInfo.add(sueldoField());
		
		//numero del recepcionista
		idInfoPanel.add(new JLabel("Numero: "));
		JSpinner numeroSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(numeroSpin);
		numeroSpin.setPreferredSize(new Dimension(50, 20));
		numero = (Integer) numeroSpin.getValue();
		numeroSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				numero = (Integer) numeroSpin.getValue();
			}
		});
		
		
		//Panel de botones
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);

		//Boton OK y cancelar
		buttonsPanel.add(okButton());
		buttonsPanel.add(cancelButton());

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
				
				
	}
	
	JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hayLetras){
					JOptionPane.showMessageDialog(VCrearRecepcionista.this, "Informacion invalida en el sueldo");
					ctrl.accion(Events.ABRIR_VRECEPCIONISTA, null);
				}else if(_nombre.equals("")){
					JOptionPane.showMessageDialog(VCrearRecepcionista.this, "No puede dejar el nombre en blanco");
					ctrl.accion(Events.ABRIR_VRECEPCIONISTA, null);
				}else if(floatVal == 0){
					JOptionPane.showMessageDialog(VCrearRecepcionista.this, "No puede dejar el sueldo en blanco");
					ctrl.accion(Events.ABRIR_VRECEPCIONISTA, null);
				}
				else {
					ctrl.accion(Events.ALTA_RECEPCIONISTA, new TRecepcionista(numero, _nombre, floatVal, true));
				}
				setVisible(false);
			}
		}); 
		
		return okB;
	}
	
	JButton cancelButton(){
		JButton cancelB = new JButton("Cancel");
		
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VRECEPCIONISTA, null);
				setVisible(false);
			}
		});
		
		return cancelB;
	}
	
	private JTextField nombreField(){
	    JTextField nombreField = new JTextField(10);
	    _nombre= new String();
	    nombreField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            _nombre= nombreField.getText();
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	  _nombre= nombreField.getText();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	  _nombre= nombreField.getText();
	        }
	    });
	    return nombreField;
	}
	
	JTextField sueldoField(){
	    JTextField precioTextField = new JTextField(10);
	    hayLetras = false;
	   
	    precioTextField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	try{
		            _sueldo = precioTextField.getText();
		            floatVal = Float.valueOf(_sueldo).floatValue();
		            hayLetras = false;
	        	}
	        	catch(NumberFormatException exc){
	        		hayLetras = true;
	        	}
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	try{
		            _sueldo = precioTextField.getText();
		            floatVal = Float.valueOf(_sueldo).floatValue();
		            hayLetras = false;
	        	}
	        	catch(NumberFormatException exc){
	        		hayLetras = true;
	        	}
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	try{
		            _sueldo = precioTextField.getText();
		            floatVal = Float.valueOf(_sueldo).floatValue();
		            hayLetras = false;
	        	}
	        	catch(NumberFormatException exc){
	        		hayLetras = true;
	        	}
	        }
	    });
	    return precioTextField;
	}

	public void update(Object data) {
		setVisible(true);
	}
}