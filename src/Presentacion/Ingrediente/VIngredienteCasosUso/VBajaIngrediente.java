package Presentacion.Ingrediente.VIngredienteCasosUso;

import javax.swing.JFrame;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Ingrediente.VIngrediente;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VBajaIngrediente extends JFrame implements IGUI {
	public Controller ctrl;
	private int _id;
	private JButton returnButton;
	public VBajaIngrediente() {
		super("Baja de ingrediente");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initIGUI();
			}
		});	
	}
	
	
	
	
	public void initIGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);

		JPanel idInfoPanel = new JPanel();
		mainPanel.add(idInfoPanel);
		
		idInfoPanel.add(new JLabel("ID: "));
		idInfoPanel.add(idSpinner());
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);
		buttonsPanel.add(okButton());
		buttonsPanel.add(cancelButton());

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.BAJA_INGREDIENTE, _id);
				setVisible(false);
				ctrl.accion(Events.ABRIR_VINGREDIENTE, null);
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
				ctrl.accion(Events.ABRIR_VINGREDIENTE, null);
			}
		});
		
		return cancelB;
	}
	
	JSpinner idSpinner(){
		JSpinner idSpin =new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idSpin.setPreferredSize(new Dimension(50, 20));
		_id = (Integer) idSpin.getValue();
		idSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_id = (Integer) idSpin.getValue();
			}
		});
		return idSpin;
	}
	
	/*@Override
	public void update(int event, Object datos) {	
		if (event == Events.BAJA_INGREDIENTE_OK) 
			JOptionPane.showMessageDialog(null, "El ingrediente de id " + (Integer) datos + " ha sido dado de baja");
		else if (event == Events.BAJA_INGREDIENTE_KO)
			JOptionPane.showMessageDialog(this, "ERROR");
		else if(event == Events.BAJA_INGREDIENTE_ID_INEXISTENTE)
			JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos + " no esta registrado");
		else if(event == Events.BAJA_INGREDIENTE_YA_INACTIVO)
			JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos + " ya estaba dado de baja");
		else if(event == Events.BAJA_INGREDIENTE_VINCULADO_A_UN_PRODUCTO)
			JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos + " ya esta vinculado a un producto, no se puede dar de baja");
	}
	*/
	@Override
	public void update(Object data) {
		// TODO Auto-generated method stub
		
	}
}