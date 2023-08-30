package Presentacion.Menu.VMenuCasosDeUso;

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
import Negocio.Menu.TMenuProducto;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VQuitarProducto extends JFrame implements IGUI {
	private int _idProd;
	private int _idMenu;
	private Controller ctrl;

	public VQuitarProducto() {
		super("Quitar Producto a Menu");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	public void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
        
		JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(infoPanel);
		//IDING
		JPanel idMenuSpinner =new JPanel();
		infoPanel.add(idMenuSpinner, BorderLayout.PAGE_END);
		idMenuSpinner.add(new JLabel("Id_menu: "));
		idMenuSpinner.add(idMenuSpinner());
		
		//IDPROD
		JPanel idProdInfo =new JPanel();
		infoPanel.add(idProdInfo, BorderLayout.PAGE_END);
		idProdInfo.add(new JLabel("Id_producto: "));
		idProdInfo.add(idProdSpinner());
		//Metemos los botones
		JPanel buttonPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonPanel);		
		buttonPanel.add(okButton());
		buttonPanel.add(cancelButton());

		pack();	
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	JSpinner idMenuSpinner(){
		JSpinner idMenuSpin =new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idMenuSpin.setPreferredSize(new Dimension(50, 20));
		_idMenu = (Integer) idMenuSpin.getValue();
		idMenuSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_idMenu = (Integer) idMenuSpin.getValue();
			}
		});
		return idMenuSpin;
	}
	JSpinner idProdSpinner(){
		JSpinner idProdSpin =new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idProdSpin.setPreferredSize(new Dimension(50, 20));
		_idProd = (Integer) idProdSpin.getValue();
		idProdSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_idProd = (Integer) idProdSpin.getValue();
			}
		});
		return idProdSpin;
	}
	JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ctrl.accion(Events.DESVINCULAR_PRODUCTO, new TMenuProducto(_idMenu, _idProd));
			}
		});
		
		return okB;
	}
	JButton cancelButton(){
		JButton cancelB = new JButton("Cancel");
		
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VMENU, null);
			}
		});
		
		return cancelB;
	}
	
	@Override
	public void update(Object datos) {

	}
	
}

