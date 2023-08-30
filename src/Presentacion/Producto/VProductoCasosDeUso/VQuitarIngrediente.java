package Presentacion.Producto.VProductoCasosDeUso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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

import Negocio.Producto.TProductoIngrediente;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Producto.VProducto;

@SuppressWarnings("serial")
public class VQuitarIngrediente extends JFrame implements IGUI {
	private int _idIng;
	private int _idProd;
	private Controller ctrl;
	
	public VQuitarIngrediente() {
		super("Quitar Ingrediente a Producto");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});	}
	
	public void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(infoPanel);
		//IDPROD
		JPanel idProdInfo = new JPanel();
		infoPanel.add(idProdInfo, BorderLayout.PAGE_END);
		idProdInfo.add(new JLabel("Id_producto: "));
		idProdInfo.add(idProdSpinner());
		//IDING
		JPanel idIngInfo = new JPanel();
		infoPanel.add(idIngInfo, BorderLayout.PAGE_END);
		idIngInfo.add(new JLabel("Id_ingrediente: "));
		idIngInfo.add(idIngSpinner());
		

		//Metemos los botones
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonPanel);	
		buttonPanel.add(okButton());
		buttonPanel.add(cancelButton());
				
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
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
	
	JSpinner idIngSpinner(){
		JSpinner idIngSpin =new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idIngSpin.setPreferredSize(new Dimension(50, 20));
		_idIng = (Integer) idIngSpin.getValue();
		idIngSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_idIng = (Integer) idIngSpin.getValue();
			}
		});
		return idIngSpin;
	}
	
	JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ctrl.accion(Events.DESVINCULAR_INGREDIENTE, new TProductoIngrediente(_idProd, _idIng));
				setVisible(false);
				ctrl.accion(Events.ABRIR_VPRODUCTO, null);
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
				ctrl.accion(Events.ABRIR_VPRODUCTO, null);
			}
		});
		
		return cancelB;
	}

	@Override
	public void update(Object data) {
		// TODO Auto-generated method stub
		/*if(event == Events.DESVINCULAR_INGREDIENTE_OK){
		JOptionPane.showMessageDialog(this, "Se ha quitado el ingrediente deseado");
	}
	else if (event == Events.DESVINCULAR_INGREDIENTE_NO_PROD){
		JOptionPane.showMessageDialog(this, "No existe el Producto");
	}
	else if (event == Events.DESVINCULAR_INGREDIENTE_NO_INGR){
		JOptionPane.showMessageDialog(this, "No existe el Ingrediente");
	}
	else if (event == Events.DESVINCULAR_INGREDIENTE_KO ){
		JOptionPane.showMessageDialog(this, "No se ha podido quitar el ingrediente");*/
	}
}
