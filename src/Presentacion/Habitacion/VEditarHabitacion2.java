package Presentacion.Habitacion;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Negocio.Habitacion.*;

public class VEditarHabitacion2 extends JFrame implements IGUI {
	private JPanel panel;
	private JTextField id, numero, planta, activo, clase, descuento, sobrecoste;
	
	
	public VEditarHabitacion2() {
		super("Modificar habitacion"); 
		panel = new JPanel(new GridLayout(7, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		numero = new JTextField();
		planta = new JTextField();
		activo = new JTextField();
		clase = new JTextField();
		descuento = new JTextField();
		sobrecoste = new JTextField();
	}
	
	public void update(Object data) {
		THabitacion tm = (THabitacion) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tm.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		//Numero
		JLabel lNum = new JLabel("Numero: ");
		numero.setText(Integer.toString(tm.getNumero()));
		numero.setEditable(true);
		panel.add(lNum);
		panel.add(numero);
		//Planta
		JLabel lCap = new JLabel("Planta: ");
		planta.setText(Integer.toString(tm.getPlanta()));
		planta.setEditable(true);
		panel.add(lCap);
		panel.add(planta);
		//Activo
		JLabel lAct = new JLabel("Activo: ");
		activo.setText(Boolean.toString(tm.getActivo()));
		activo.setEditable(false);
		panel.add(lAct);
		panel.add(activo);
		
		//Clase
		JLabel lCls = new JLabel("Clase: ");
		panel.add(lCls);
		if (tm.getClass().equals((TBasica.class))) {
			TBasica bs = (TBasica) tm;
			clase.setText("Basica");
			clase.setEditable(false);
			panel.add(clase);
			
			JLabel lDsc = new JLabel("Descuento: ");
			descuento.setText(Float.toString(bs.getDescuento()));
			descuento.setEditable(true);
			panel.add(lDsc);
			panel.add(descuento);
		}
		else {
			TSuite st = (TSuite) tm;
			clase.setText("Suite");
			clase.setEditable(false);
			panel.add(clase);
			
			JLabel lSb = new JLabel("Sobrecoste: ");
			sobrecoste.setText(Float.toString(st.getSobrecoste()));
			sobrecoste.setEditable(true);
			panel.add(lSb);
			panel.add(sobrecoste);
		}
		
		
		//Boton OK
		JButton okB = new JButton("OK");
		panel.add(okB);
				
		
		okB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				int _id = tm.getId();
				int numHabitacion = Integer.parseInt(numero.getText());
				int plant = Integer.parseInt(planta.getText());
				if (clase.getText().equals("Basica")) {
					float desc = Float.parseFloat(descuento.getText());
					Controller.obtenerInstancia().accion(Events.MODIFICAR_HABITACION2, new TBasica(_id, plant, numHabitacion, true, desc));
				}
				else {
					float sobr = Float.parseFloat(sobrecoste.getText());
					Controller.obtenerInstancia().accion(Events.MODIFICAR_HABITACION2, new TSuite(_id, plant, numHabitacion, true, sobr));
				}
				
				setVisible(false);
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(panel, "Introduzca datos v�lidos");
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
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_HABITACION, null);
			}
		});
		this.pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}