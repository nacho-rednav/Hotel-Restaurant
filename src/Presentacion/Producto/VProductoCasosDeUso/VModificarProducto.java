package Presentacion.Producto.VProductoCasosDeUso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Negocio.Producto.TProducto;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VModificarProducto extends JFrame implements IGUI {
	private Controller ctrl;
	private int _id;
	private String _nombre;

	public VModificarProducto() {
		super("Modificar Un Producto");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		mainPanel.add(infoPanel);
		
		//ID PRODUCTO
		JPanel idInfo = new JPanel();
		infoPanel.add(idInfo);
		idInfo.add(new JLabel("Id del producto a modificar: "));
		idInfo.add(idSpinner());
		
		//botones ok y cancelar
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonPanel);
		buttonPanel.add(okButton());
		buttonPanel.add(cancelButton());
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	JButton okButton() {
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MODIFICAR_PRODUCTO, _id);
				setVisible(false);
			}
		});
		
		return okB;
	}
	
	JSpinner idSpinner() {
		JSpinner capacitySpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		capacitySpin.setPreferredSize(new Dimension(50, 20));
		_id = (Integer) capacitySpin.getValue();
		capacitySpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_id = (Integer) capacitySpin.getValue();
			}
		});
		return capacitySpin;
	}
	
	JButton cancelButton() {
		JButton cancelB = new JButton("Cancel");
		
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VPRODUCTO, null);
			}
		});
		
		return cancelB;
	}

	@Override
	public void update(Object data) {
		// TODO Auto-generated method stub
		/*if(event == Events.MODIFICAR_PRODUCTO_ID_INEXISTENTE)
			JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos + " no esta registrado, o esta inactivo");
		else if(event == Events.MODIFICAR_PRODUCTO_NOMBRE_YA_EXISTE)
			JOptionPane.showMessageDialog(this, "ERROR: El producto de nombre " + (String) datos + " ya esta registrado");
		else if(event == Events.MODIFICAR_PRODUCTO_DATOS_INVALIDOS)
			JOptionPane.showMessageDialog(this, "ERROR: Datos invalidos");
		else if(event == Events.MODIFICAR_PRODUCTO_OK)
			JOptionPane.showMessageDialog(this, "El producto de id " + (Integer) datos + " ha sido modificado");*/
	}

}
