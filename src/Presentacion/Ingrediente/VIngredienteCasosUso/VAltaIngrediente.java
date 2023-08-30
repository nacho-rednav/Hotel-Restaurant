package Presentacion.Ingrediente.VIngredienteCasosUso;

import javax.swing.JFrame;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Ingrediente.VIngrediente;
import Presentacion.Mesa.VMesaCasosUso.VAltaMesa;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import Negocio.Ingrediente.TIngrediente;
import Negocio.Mesa.TMesa;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@SuppressWarnings("serial")
public class VAltaIngrediente extends JFrame implements IGUI {
	private String nombreIng;
	private Boolean alergeno;
	private Controller ctrl;
	
	
	public VAltaIngrediente(){
		super("Alta de ingrediente");
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
		
		JPanel infoPanel = new JPanel(new BorderLayout());
		mainPanel.add(infoPanel);
		
		
		

		// ID
		
		/*
		 * JPanel idInfoPanel = new JPanel();
		infoPanel.add(idInfoPanel);
		idInfoPanel.add(new JLabel("ID: "));
		JSpinner idIngrdienteSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idInfoPanel.add(idIngrdienteSpin);
		idIngrdienteSpin.setPreferredSize(new Dimension(50, 20));
		_id = (Integer) idIngrdienteSpin.getValue();
		idIngrdienteSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_id = (Integer) idIngrdienteSpin.getValue();
			}
		});*/
		//ATRIBUTOS
		//NOMBRE
		JPanel nombrePanel = new JPanel();
		mainPanel.add(nombrePanel);
		
		nombrePanel.add(new JLabel("Nombre: "));
		nombrePanel.add(nombreField());
		
		//ALERGENO INGREDIENTE
		JPanel alergenoPanel = new JPanel();
		mainPanel.add(alergenoPanel);
		
		alergenoPanel.add(new JLabel("Alergeno: "));
		alergenoPanel.add(alergenoBox());
		
		//BOTONES OK CANCEL
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonPanel);
		buttonPanel.add(okButton());
		buttonPanel.add(cancelButton());

		
		setLocationRelativeTo(getParent());
		setVisible(true);
		pack();
	}
	
	JTextField nombreField(){
		JTextField nombreTextField = new JTextField(10);
		
		nombreTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				nombreIng = nombreTextField.getText();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				nombreIng = nombreTextField.getText();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				nombreIng = nombreTextField.getText();
			}
		});
		return nombreTextField;
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

	JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ctrl.accion(Events.ALTA_MESA, new TIngrediente( nombreIng,alergeno, true));
				//setVisible(false);
				TIngrediente tIng = new TIngrediente(null, alergeno, nombreIng, true);
				ctrl.accion(Events.ALTA_INGREDIENTE, tIng);
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
	
	/*@Override
	public void update(int event, Object datos) {
		if(event == Events.ALTA_INGREDIENTE_OK)
			JOptionPane.showMessageDialog(this, "El ingrediente se ha registrado con id " + (Integer) datos);
		else if(event == Events.ALTA_INGREDIENTE_KO)
			JOptionPane.showMessageDialog(this, "Error");
		else if(event == Events.ALTA_INGREDIENTE_YA_EXISTENTE)
			JOptionPane.showMessageDialog(this, "ERROR: Ya hay un ingrediente con nombre " + (String) datos);
	}
	
	
	*/
	@Override
	public void update(Object data) {
		// TODO Auto-generated method stub
		
	}
}