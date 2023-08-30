package Presentacion.Producto.VProductoCasosDeUso;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Producto.TProducto;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VModificarProducto2 extends JFrame implements IGUI {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JTextField id, nombre, activo;
	
	
	public VModificarProducto2() {
		super("Modificar Producto"); 
		panel = new JPanel(new GridLayout(6, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		nombre = new JTextField();
		activo = new JTextField();
		setLocationRelativeTo(getParent());
	}
	
	
	public void update(Object data) {
		TProducto tProd = (TProducto) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tProd.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		//Nombre
		JLabel lNom = new JLabel("Nombre: ");
		nombre.setText(tProd.getNombre());
		nombre.setEditable(true);
		panel.add(lNom);
		panel.add(nombre);
		//Activo
		JLabel lAct = new JLabel("Activo: ");
		activo.setText(Boolean.toString(tProd.getActivo()));
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
				int _id = tProd.getId();
				String _nombre = nombre.getText();
				Boolean _activo = tProd.getActivo();
				TProducto tProdNew;

				tProdNew = new TProducto(_id, _activo, _nombre);
					
				Controller.obtenerInstancia().accion(Events.MODIFICAR_PRODUCTO2, tProdNew);
				setVisible(false);
				Controller.obtenerInstancia().accion(Events.ABRIR_VPRODUCTO, null);
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
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_PRODUCTO, null);
			}
		});
		this.pack();
		setVisible(true);
	}
}
