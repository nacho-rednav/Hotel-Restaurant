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
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Producto.TProducto;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Producto.VProducto;

@SuppressWarnings("serial")
public class VBajaProducto extends JFrame implements IGUI{

	private Controller ctrl;
	int _id;
	
	public VBajaProducto() {
		super("Baja de Producto");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initIGUI();
			}
		});
	}
	
	private void initIGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		//Panel con el spinner del id
		JPanel infoPanel = new JPanel(new BorderLayout());
		mainPanel.add(infoPanel);
		
		JPanel idInfo =new JPanel();
		infoPanel.add(idInfo, BorderLayout.PAGE_START);
		
		idInfo.add(new JLabel("ID: "));
		idInfo.add(idSpinner());
		
		//Panel con botones de ok y cancel
		JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(panelButton);
		panelButton.add(okButton());
		panelButton.add(cancelButton());

		pack();		
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	JButton okButton() {
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.BAJA_PRODUCTO, _id);
				ctrl.accion(Events.ABRIR_VPRODUCTO, null);
			}
		});
		
		return okB;
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


	@Override
	public void update(Object data) {
		// TODO Auto-generated method stub
		/*if (event == Events.BAJA_PRODUCTO_OK) 
		JOptionPane.showMessageDialog(null, "El producto de id " + (Integer) datos + " ha sido dado de baja");
	else if (event == Events.BAJA_PRODUCTO_KO) 
		JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos + " no esta registrado");
	else if(event == Events.BAJA_PRODUCTO_ID_INEXISTENTE)
		JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos + " no esta registrado");
	else if(event == Events.BAJA_PRODUCTO_YA_INACTIVO)
		JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos + " ya estaba dado de baja");
	else if(event == Events.BAJA_PRODUCTO_VINCULADO_A_UN_MENU)
		JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos + " esta vinculado a un menu, no se puede dar de baja");*/
	}
}