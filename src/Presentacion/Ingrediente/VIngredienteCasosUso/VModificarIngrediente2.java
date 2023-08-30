package Presentacion.Ingrediente.VIngredienteCasosUso;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Ingrediente.TIngrediente;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VModificarIngrediente2 extends JFrame implements IGUI{
private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JTextField id, nombre, activo;
	private Boolean alergeno;
	
	public VModificarIngrediente2() {
		super("Modificar Ingrediente"); 
		panel = new JPanel(new GridLayout(6, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		nombre = new JTextField();
		activo = new JTextField();
		setLocationRelativeTo(getParent());
	}
	
	
	
	public void update(Object data) {
		TIngrediente tIngr = (TIngrediente) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tIngr.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		//Nombre
		JLabel lNom = new JLabel("Nombre: ");
		nombre.setText(tIngr.getNombre());
		nombre.setEditable(true);
		panel.add(lNom);
		panel.add(nombre);
		//ALERGENO INGREDIENTE
		JPanel alergenoPanel = new JPanel();
		panel.add(alergenoPanel);
				
		alergenoPanel.add(new JLabel("Alergeno: "));
		alergenoPanel.add(alergenoBox());
		//Activo
		JLabel lAct = new JLabel("Activo: ");
		activo.setText(Boolean.toString(tIngr.getActivo()));
		activo.setEditable(false);
		panel.add(lAct);
		panel.add(activo);
		
		//Boton OK
		JButton okB = new JButton("OK");
		panel.add(okB);
				
		
		okB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int _id = tIngr.getId();
				String _nombre = nombre.getText();
				Boolean _activo = tIngr.getActivo();
				
				TIngrediente tIngrNew;

				tIngrNew = new TIngrediente(_id, alergeno, _nombre, _activo);
					
				Controller.obtenerInstancia().accion(Events.MODIFICAR_INGREDIENTE2, tIngrNew);
				setVisible(false);
				Controller.obtenerInstancia().accion(Events.ABRIR_VINGREDIENTE, null);
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(panel, "Introduzca datos validos");
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
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_INGREDIENTE, null);
			}
		});
		this.pack();
		setVisible(true);
	}
	
	JComboBox<Boolean> alergenoBox(){
		JComboBox<Boolean> comboAlerg = new JComboBox<Boolean>();
		comboAlerg.addItem(true);
		comboAlerg.addItem(false);
		
		alergeno = (Boolean) comboAlerg.getSelectedItem();
		
		comboAlerg.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					alergeno = (Boolean) e.getItem();
			}
		});
		
		return comboAlerg;
	}
}
