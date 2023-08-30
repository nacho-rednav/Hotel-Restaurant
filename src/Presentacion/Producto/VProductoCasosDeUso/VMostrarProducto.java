package Presentacion.Producto.VProductoCasosDeUso;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

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

import Negocio.Ingrediente.TIngrediente;
import Negocio.Producto.TProducto;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Menu.VMenuCasosDeUso.VMostrarMenu;
import Presentacion.Producto.VProducto;

@SuppressWarnings("serial")
public class VMostrarProducto extends JFrame implements IGUI {
	private Controller ctrl;
	private Integer id;
	
	public static void main(String[] args) {
		new VMostrarProducto();
	}
	
	public VMostrarProducto() {
		super("Mostrar Un Producto");
		ctrl = Controller.obtenerInstancia();
		initGUI();
	}
	
	public void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);
		
		JPanel takingInfoPanel = new JPanel();
		mainPanel.add(takingInfoPanel);
		
		takingInfoPanel.add(new JLabel("ID Producto: "));
		takingInfoPanel.add(idSpinner());
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);
		buttonsPanel.add(okButton());
		buttonsPanel.add(cancelButton());
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	JSpinner idSpinner() {
		JSpinner idSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idSpin.setPreferredSize(new Dimension(50, 20));
		id = (Integer) idSpin.getValue();
		idSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				id = (Integer) idSpin.getValue();
			}
		});
		return idSpin;
	}
	
	private JButton okButton() {
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MOSTRAR_UN_PRODUCTO, id);
				ctrl.accion(Events.MOSTRAR_INGREDIENTES_DE_UN_PRODUCTO, id);
				setVisible(false);
				ctrl.accion(Events.ABRIR_VPRODUCTO, null);
			}
		});
		
		return okB;
	}
	
	private JButton cancelButton() {
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
	}
}