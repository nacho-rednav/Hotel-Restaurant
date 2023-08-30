package Presentacion.Menu.VMenuCasosDeUso;

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

import Negocio.Empleado.TCamarero;
import Negocio.Empleado.TCocinero;
import Negocio.Empleado.TEmpleado;
import Negocio.Menu.TMenu;
import Negocio.Mesa.TMesa;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;


public class VModificarMenu2 extends JFrame implements IGUI {
	
	private JPanel panel;
	private JTextField id, nombre, precio, stock;
	
	
	public VModificarMenu2() {
		super("Modificar Empleado"); 
		panel = new JPanel(new GridLayout(6, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		nombre = new JTextField();
		precio = new JTextField();
		stock = new JTextField();
		
	}
	
	
	public void update(Object data) {
		TMenu tMenu = (TMenu) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tMenu.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		//Nombre
		JLabel lNom = new JLabel("Nombre: ");
		nombre.setText(tMenu.getNombre());
		nombre.setEditable(true);
		panel.add(lNom);
		panel.add(nombre);
		//Precio
		JLabel lPre = new JLabel("Precio: ");
		precio.setText(Float.toString(tMenu.getPrecio()));
		precio.setEditable(true);
		panel.add(lPre);
		panel.add(precio);
		//Stock
		JLabel lStc = new JLabel("Strock: ");
		stock.setText(Integer.toString(tMenu.getStock()));
		stock.setEditable(true);
		panel.add(lStc);
		panel.add(stock);
		//Boton OK
		JButton okB = new JButton("OK");
		panel.add(okB);
				
		
		okB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				int _id = tMenu.getId();
				String _nombre = nombre.getText();
				Integer _stock = Integer.parseInt(stock.getText());
				Float _precio = Float.parseFloat(precio.getText());
				Boolean _activo = tMenu.getActivo();
				TMenu tMenuNew = new TMenu(_id, _activo, _nombre, _precio, _stock);
				Controller.obtenerInstancia().accion(Events.MODIFICAR_MENU2, tMenuNew);
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
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_MENU2, null);
			}
		});
		this.pack();
		setVisible(true);
	}
}