package Presentacion.Servicio.VServicioCasosUso;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Recepcionista.VRecepcionistaCasosUso.VCrearRecepcionista;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Negocio.Recepcionista.TRecepcionista;
import Negocio.Reserva.TReserva;
import Negocio.Servicio.TServicio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VCrearServicio extends JFrame implements IGUI {
	public Controller ctrl;
	private int _id;
	private String tipo;
	private String descripcion;
	private String precio; 

	//atributos ayudantes para métodos
	private Boolean hayLetras;
	private Float floatVal;
	
	public VCrearServicio() {
		super("Alta servicio");
		ctrl = Controller.obtenerInstancia();
		initIGUI();
	}

	public void initIGUI() {
		//Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);

		// Panel de datos
		JPanel idInfoPanel = new JPanel();
		mainPanel.add(idInfoPanel);

		// Tipo
		JPanel tipoInfo = new JPanel();
		idInfoPanel.add(tipoInfo, BorderLayout.PAGE_START);
		tipoInfo.add(new JLabel("Tipo: "));
		tipoInfo.add(tipoField());

		// Descripción
		JPanel DescripcionInfo = new JPanel();
		idInfoPanel.add(DescripcionInfo, BorderLayout.PAGE_START);
		DescripcionInfo.add(new JLabel("Descripción: "));
		DescripcionInfo.add(descripcionField());
		
		//Precio
		JPanel precioInfo =new JPanel();
		idInfoPanel.add(precioInfo);
		precioInfo.add(new JLabel("Precio: "));
		precioInfo.add(precioField());
		
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
					JOptionPane.showMessageDialog(VCrearServicio.this, "Informacion invalida");
					ctrl.accion(Events.ABRIR_VRECEPCIONISTA, null);
				}
				else{
					ctrl.accion(Events.ALTA_SERVICIO, new TServicio(_id, tipo, descripcion, floatVal, true));
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
				ctrl.accion(Events.ABRIR_VSERVICIO, null);
				setVisible(false);
			}
		});
		
		return cancelB;
	}

	private JTextField tipoField(){
	    JTextField tipoField = new JTextField(10);
	    tipo= new String();
	    tipoField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	tipo= tipoField.getText();
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	tipo= tipoField.getText();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	tipo= tipoField.getText();
	        }
	    });
	    return tipoField;
	}
	
	private JTextField descripcionField(){
	    JTextField descripcionField = new JTextField(10);
	    descripcion = new String();
	    descripcionField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	descripcion= descripcionField.getText();
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	descripcion= descripcionField.getText();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	descripcion= descripcionField.getText();
	        }
	    });
	    return descripcionField;
	}
	
	JTextField precioField(){
	    JTextField precioTextField = new JTextField(10);
	    hayLetras = false;
	   
	    precioTextField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	try{
		            precio = precioTextField.getText();
		            floatVal = Float.valueOf(precio).floatValue();
		            hayLetras = false;
	        	}
	        	catch(NumberFormatException exc){
	        		hayLetras = true;
	        	}
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	try{
	        		precio = precioTextField.getText();
		            floatVal = Float.valueOf(precio).floatValue();
		            hayLetras = false;
	        	}
	        	catch(NumberFormatException exc){
	        		hayLetras = true;
	        	}
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	try{
	        		precio = precioTextField.getText();
		            floatVal = Float.valueOf(precio).floatValue();
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