package Presentacion.Habitacion;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Habitacion.*;


public class VCrearHabitacion extends JFrame implements IGUI {
	
	public Controller ctrl;
	private int planta;
	private int numero;
	private String selectedClase = "Basica";
	private float descuento;
	private float sobrecoste;
	

	public VCrearHabitacion() {
		super("Alta habitacion");
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

		// Planta
		idInfoPanel.add(new JLabel("Planta: "));
		JSpinner plantaSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(plantaSpin);
		plantaSpin.setPreferredSize(new Dimension(50, 20));
		planta = (Integer) plantaSpin.getValue();
		plantaSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				planta = (Integer) plantaSpin.getValue();
			}
		});

		// Numero
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
		
		
		
		JPanel HabBasica = new JPanel();
		JPanel HabSuite = new JPanel();
		
		idInfoPanel.add(new JLabel("Clase:"));
		JComboBox<String> claseBox = new JComboBox<String>();
		
		claseBox.addItem("Basica");
		claseBox.addItem("Suite");
		idInfoPanel.add(claseBox);
		
		
		claseBox.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					selectedClase = (String) e.getItem();
					if(selectedClase.equals("Basica")){
						HabBasica.setVisible(true);
						HabSuite.setVisible(false);
					} else if(selectedClase.equals("Suite")){
						HabBasica.setVisible(false);
						HabSuite.setVisible(true);
					}	
				}
			}
		});
		
		
		JLabel descLabel = new JLabel("Descuento: ");
		HabBasica.add(descLabel);
		JSpinner descuentoSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		HabBasica.add(descuentoSpin);
		descuentoSpin.setPreferredSize(new Dimension(50, 20));
		descuento = (Integer) descuentoSpin.getValue();
		descuentoSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				descuento = (Integer) descuentoSpin.getValue();
			}
		});
		idInfoPanel.add(HabBasica);
		
		
		
		JLabel sobLabel = new JLabel("Sobrecoste: ");
		HabSuite.add(sobLabel);
		JSpinner sobrecosteSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		HabSuite.add(sobrecosteSpin);
		sobrecosteSpin.setPreferredSize(new Dimension(50, 20));
		sobrecoste = (Integer) sobrecosteSpin.getValue();
		sobrecosteSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				sobrecoste = (Integer) sobrecosteSpin.getValue();
			}
		});HabSuite.setVisible(false);
		idInfoPanel.add(HabSuite);
		
		
		//Panel de botones
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);

		//Boton OK
		JButton okB = new JButton("OK");
		buttonsPanel.add(okB);

		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedClase.equals("Basica")) {
					ctrl.accion(Events.CREAR_HABITACION, new TBasica(planta, numero, true, descuento));
				}
				else if (selectedClase.equals("Suite")) {
					ctrl.accion(Events.CREAR_HABITACION, new TSuite(planta, numero, true, sobrecoste));
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
				ctrl.accion(Events.ABRIR_VHABITACION, null);
			}
		});

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	

	public void update(Object data) {
		setVisible(true);
	}
}