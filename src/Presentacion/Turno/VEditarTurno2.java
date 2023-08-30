package Presentacion.Turno;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Turno.TComida;
import Negocio.Turno.TDesayuno;
import Negocio.Turno.TTurno;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEditarTurno2 extends JFrame implements IGUI{
	private JPanel panel;
	private JTextField id, capacidad, menuDia, activo, clase, tipo;
	
	private float precio;
	private Date dia;
	private double precio2;
	
	private float costeServicio;
	private double costeServicio2;
	
	private float costeComplemento;
	private double costeComplemento2;
	private Boolean complementoZumo;
	private Boolean complementoFruta;
	private Boolean complementoCafe;
	
	public VEditarTurno2() { 		
		super("Modificar Turno"); 
		panel = new JPanel(new GridLayout(12, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		capacidad = new JTextField();
		menuDia = new JTextField();
		clase = new JTextField();
		tipo = new JTextField();
		activo = new JTextField();
	}
	
	@Override
	public void update(Object data) {
		TTurno tm = (TTurno) data;
		
	
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tm.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);	
		
		//Capacidad
		JLabel lCap = new JLabel("Capacidad: ");
		capacidad.setText(Integer.toString(tm.getCapacidad()));
		capacidad.setEditable(true);
		panel.add(lCap);
		panel.add(capacidad);
		
		//Precio
		JLabel precioLabel = new JLabel("Precio: ");
		panel.add(precioLabel);
		double auxPrecio = tm.getPrecio();
		JSpinner precioSpin = new JSpinner(new SpinnerNumberModel(auxPrecio, 0, Float.MAX_VALUE, 1));
		panel.add(precioSpin);
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
		panel.add(diaLabel);
		Date auxDia = tm.getDia();
	    JSpinner diaSpin = new JSpinner(new SpinnerDateModel(auxDia, null, null, Calendar.MONTH));
	    JSpinner.DateEditor editor = new JSpinner.DateEditor(diaSpin, "dd/MM/yy");
	    diaSpin.setEditor(editor);
	    panel.add(diaSpin);
		dia = (Date) diaSpin.getValue();
		
		diaSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				dia = (Date) diaSpin.getValue();
			}
		});
		diaSpin.setVisible(true);
		
		//Activo
		JLabel lAct = new JLabel("Activo: ");
		activo.setText(Boolean.toString(tm.getActivo()));
		activo.setEditable(false);
		panel.add(lAct);
		panel.add(activo);	
		
		//Clase
		JLabel lCls = new JLabel("Clase: ");
		panel.add(lCls);
		
		if (tm.getClass().equals((TComida.class))) {
			TComida tc = (TComida) tm;
			clase.setText("Comida");
			clase.setEditable(false);
			panel.add(clase);
			
			//MenuDia
			JLabel lMenuDia = new JLabel("MenuDia: ");
			menuDia.setText(tc.getMenuDia());
			panel.add(lMenuDia);
			panel.add(menuDia);
			
			//CosteServicio
			JLabel costSerLabel = new JLabel("Coste Servicio: ");
			panel.add(costSerLabel);
			double auxCostSer = tc.getCosteServicio();
			JSpinner costSerSpin = new JSpinner(new SpinnerNumberModel(auxCostSer, 1, Float.MAX_VALUE, 1));
			panel.add(costSerSpin);
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
		}
		else{
			TDesayuno td = (TDesayuno) tm;
			clase.setText("Desayuno");
			clase.setEditable(false);
			panel.add(clase);
			
			//Tipo
			JLabel lTipo = new JLabel("Tipo: ");
			tipo.setText(td.getTipo());
			tipo.setEditable(true);
			panel.add(lTipo);
			panel.add(tipo);
			
			//CosteComplemento
			JLabel costCompLabel = new JLabel("Coste Complemento: ");
			panel.add(costCompLabel);
			double auxCostComp = td.getCosteComplemento();
			JSpinner costCompSpin = new JSpinner(new SpinnerNumberModel(auxCostComp, 1, Float.MAX_VALUE, 1));
			panel.add(costCompSpin);
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
			
			//ComplementosDesayuno(Zumo,fruta,cafe)
			JLabel compLabel = new JLabel("Complemento 1: ");
			panel.add(compLabel);
	        JCheckBox zumoCheckbox = new JCheckBox("Zumo");
	        zumoCheckbox.setSelected(td.getComplementoZumo());
	        complementoZumo = td.getComplementoZumo();
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
			panel.add(zumoCheckbox);
			
			JLabel compLabel2 = new JLabel("Complemento 2: ");
			panel.add(compLabel2);
	        JCheckBox frutaCheckbox = new JCheckBox("Fruta");    
	        frutaCheckbox.setSelected(td.getComplementoFruta());
	        complementoFruta = td.getComplementoFruta();
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

			panel.add(frutaCheckbox);
			
			JLabel compLabel3 = new JLabel("Complemento 3: ");
			panel.add(compLabel3);
	        JCheckBox cafeCheckbox = new JCheckBox("Cafe");
	        cafeCheckbox.setSelected(td.getComplementoCafe());
	        complementoCafe = td.getComplementoCafe();
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
			panel.add(cafeCheckbox);
		}
		
		
		//Boton OK
		JButton okB = new JButton("OK");
		panel.add(okB);	
		okB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				
				int _id = tm.getId();
				int _capacidad = Integer.parseInt(capacidad.getText());
				String _menuDia = menuDia.getText();
				if (clase.getText().equals("Comida")) {
					Controller.obtenerInstancia().accion(Events.MODIFICAR_TURNO2, new TComida(_id, dia, _capacidad, precio, true, costeServicio, _menuDia) );
				}
				else {
				String _tipo = tipo.getText();
					Controller.obtenerInstancia().accion(Events.MODIFICAR_TURNO2, new TDesayuno(_id, dia, _capacidad, precio, true, _tipo, complementoCafe, costeComplemento, complementoZumo, complementoFruta));
				}
				setVisible(false);
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(panel, "Introduzca datos válidos");
				setVisible(true);
			}
		}
		});
				
				
		//Boton Cancelar
		JButton cancelB = new JButton("Cancel");
		panel.add(cancelB);
				
		cancelB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_TURNO, null);
			}
		});
		this.pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
				
	}

}
