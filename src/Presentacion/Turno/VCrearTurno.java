
package Presentacion.Turno;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import java.util.Calendar;
import java.util.Date;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Time;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Negocio.Turno.TComida;
import Negocio.Turno.TDesayuno;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class VCrearTurno extends JFrame implements IGUI {
	
	private Controller ctrl;
	private String selectedClase;
	
	private float precio;
	private Date dia;
	private int capacidad;
	private double precio2;
	
	private String menuDia;
	private float costeServicio;
	private double costeServicio2;

	private String tipo;
	private float costeComplemento;
	private double costeComplemento2;
	private Boolean complementoZumo;
	private Boolean complementoFruta;
	private Boolean complementoCafe;
	
	public VCrearTurno(){
		super("Alta Turno");
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
		
		// Capacidad
		idInfoPanel.add(new JLabel("Capacidad: "));
		JSpinner capacidadSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(capacidadSpin);
		capacidadSpin.setPreferredSize(new Dimension(50, 20));
		capacidad = (Integer) capacidadSpin.getValue();
		capacidadSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				capacidad = (Integer) capacidadSpin.getValue();
			}
		});	
		capacidadSpin.setVisible(true);
		
		//Precio
		JLabel precioLabel = new JLabel("Precio: ");
		idInfoPanel.add(precioLabel);
		JSpinner precioSpin = new JSpinner(new SpinnerNumberModel(0, 0, Float.MAX_VALUE, 1));
		idInfoPanel.add(precioSpin);
		precioSpin.setPreferredSize(new Dimension(50, 20));
		precio2 = (double) precioSpin.getValue();
		precio = (float) precio2;
		precioSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				precio2 = (double) precioSpin.getValue();
				precio = (float) precio2;
			}
		});
		precioSpin.setVisible(true);
		
		//Dia
		JLabel diaLabel = new JLabel("Dia: ");
		idInfoPanel.add(diaLabel);
		Date today = new Date();
	    JSpinner diaSpin = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
	    JSpinner.DateEditor editor = new JSpinner.DateEditor(diaSpin, "dd/MM/yy");
	    diaSpin.setEditor(editor);
		idInfoPanel.add(diaSpin);
		dia = (Date) diaSpin.getValue();
		
		diaSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				dia = (Date) diaSpin.getValue();
			}
		});
		diaSpin.setVisible(true);
		
		//Tipo
		idInfoPanel.add(new JLabel("Tipo:"));
		JComboBox<String> claseBox = new JComboBox<String>();
		claseBox.addItem("Desayuno");
		claseBox.addItem("Comida");
		idInfoPanel.add(claseBox);
		claseBox.setSelectedItem("Desayuno");
		selectedClase = "Desayuno";
		claseBox.setVisible(true);
		
		//CosteServicio
		JLabel costSerLabel = new JLabel("Coste Servicio: ");
		idInfoPanel.add(costSerLabel);
		JSpinner costSerSpin = new JSpinner(new SpinnerNumberModel(1, 1, Float.MAX_VALUE, 1));
		idInfoPanel.add(costSerSpin);
		costSerSpin.setPreferredSize(new Dimension(50, 20));
		costeServicio2 = (double) costSerSpin.getValue();
		costeServicio = (float)costeServicio2 ;
		costSerSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				costeServicio2 = (double) costSerSpin.getValue();
				costeServicio = (float)costeServicio2 ;
			}
		});
		
		//MenuDia
		JLabel menuDiaLabel = new JLabel("Menu del dia: ");
		idInfoPanel.add(menuDiaLabel);
		JTextField menuDiaTextField = this.MenuField();
		idInfoPanel.add(menuDiaTextField);
		
		//TipoDesayuno
		JLabel tipoDesayLabel = new JLabel("Tipo desayuno: ");
		idInfoPanel.add(tipoDesayLabel);
		JTextField tipoDesayTextField = this.TipoField();
		idInfoPanel.add(tipoDesayTextField);
		
		
		//Esta seleccionado como default Desayuno
		menuDiaLabel.setVisible(false);
		menuDiaTextField.setVisible(false);
		costSerLabel.setVisible(false);
		costSerSpin.setVisible(false);
		
		//ComplementosDesayuno
		JLabel compLabel = new JLabel("Complementos: ");
		idInfoPanel.add(compLabel);
        JCheckBox zumoCheckbox = new JCheckBox("Zumo"); 
        complementoZumo = false;
        zumoCheckbox.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					complementoZumo = true;
				}
				else{
					complementoZumo = false;
				}
			}
		});;
        JCheckBox frutaCheckbox = new JCheckBox("Fruta");
        complementoFruta = false;
        frutaCheckbox.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					complementoFruta = true;
				}
				else{
					complementoFruta = false;
				}
			}
		});;
        JCheckBox cafeCheckbox = new JCheckBox("Cafe");
        complementoCafe = false;
        cafeCheckbox.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					complementoCafe = true;
				}
				else{
					complementoCafe = false;
				}
			}
		});;
		idInfoPanel.add(zumoCheckbox);
		idInfoPanel.add(frutaCheckbox);
		idInfoPanel.add(cafeCheckbox);
		
		//CosteComplemento
		JLabel costCompLabel = new JLabel("Coste Complemento: ");
		idInfoPanel.add(costCompLabel);
		JSpinner costCompSpin = new JSpinner(new SpinnerNumberModel(1, 0, Float.MAX_VALUE, 1));
		idInfoPanel.add(costCompSpin);
		costCompSpin.setPreferredSize(new Dimension(50, 20));
		costeComplemento2 = (double) costCompSpin.getValue();
		costeComplemento = (float)costeComplemento2 ;
		costCompSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				costeComplemento2 = (double) costCompSpin.getValue();
				costeComplemento = (float)costeComplemento2 ;
			}
		});
		
		claseBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					selectedClase = (String) e.getItem();				
					if(selectedClase.equals("Comida")) {
						claseBox.setSelectedItem("Comida");
						costCompLabel.setVisible(false);
						costCompSpin.setVisible(false);
						compLabel.setVisible(false);
						zumoCheckbox.setVisible(false);
						frutaCheckbox.setVisible(false);
						cafeCheckbox.setVisible(false);
						tipoDesayLabel.setVisible(false);
						tipoDesayTextField.setVisible(false);
						menuDiaLabel.setVisible(true);
						menuDiaTextField.setVisible(true);
						costSerLabel.setVisible(true);
						costSerSpin.setVisible(true);
					} else if(selectedClase.equals("Desayuno")) {
						claseBox.setSelectedItem("Desayuno");
						costCompLabel.setVisible(true);
						costCompSpin.setVisible(true);
						compLabel.setVisible(true);
						zumoCheckbox.setVisible(true);
						frutaCheckbox.setVisible(true);
						cafeCheckbox.setVisible(true);
						tipoDesayLabel.setVisible(true);
						tipoDesayTextField.setVisible(true);
						
						menuDiaLabel.setVisible(false);
						menuDiaTextField.setVisible(false);
						costSerLabel.setVisible(false);
						costSerSpin.setVisible(false);
					}	
				}
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
				if (selectedClase.equals("Comida")){
					menuDia = menuDiaTextField.getText();
					ctrl.accion(Events.CREAR_TURNO, new TComida(null, dia, capacidad, precio, true, costeServicio, menuDia));
				}
				else {
					tipo = tipoDesayTextField.getText();
					ctrl.accion(Events.CREAR_TURNO, new TDesayuno(null, dia, capacidad, precio, true, tipo, complementoCafe, costeComplemento, complementoZumo, complementoFruta));
				}
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
				ctrl.accion(Events.ABRIR_VTURNO, null);
			}
		});

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	private JTextField MenuField(){
	    JTextField menuField = new JTextField(10);
	    menuDia= new String();
	    menuField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	menuDia= menuField.getText();
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	menuDia= menuField.getText();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	menuDia= menuField.getText();
	        }
	    });
	    return menuField;
	}
	private JTextField TipoField(){
	    JTextField tipoField = new JTextField(10);
	    menuDia= new String();
	    tipoField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	menuDia= tipoField.getText();
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	menuDia= tipoField.getText();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	menuDia= tipoField.getText();
	        }
	    });
	    return tipoField;
	}
	
	public void update(Object data) {
		setVisible(true);
	}
}