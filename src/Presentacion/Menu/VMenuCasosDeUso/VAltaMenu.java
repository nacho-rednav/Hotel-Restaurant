package Presentacion.Menu.VMenuCasosDeUso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.NumericShaper;

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
import Negocio.Menu.TMenu;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Menu.VMenu;

@SuppressWarnings("serial")
public class VAltaMenu extends JFrame implements IGUI {
	private String nombreMenu, precioMenu;
	private Float floatVal;
	int precio;
	int stock;
	private Controller ctrl;
	private JButton returnButton;
	boolean hayLetras = false;
	
	public VAltaMenu(){
		super("Alta de menu");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initIGUI();
			}
		});
	}
	public void initIGUI() {
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		
		//ATRIBUTOS
		//NOMBREMENU
		JPanel infoPanel = new JPanel(new BorderLayout());
		mainPanel.add(infoPanel);
		
		JPanel nombreInfo =new JPanel();
		infoPanel.add(nombreInfo, BorderLayout.PAGE_START);
		nombreInfo.add(new JLabel("Nombre: "));
		nombreInfo.add(nombreField());
		
		
		//STOCK
		JPanel stockInfo =new JPanel();
		infoPanel.add(stockInfo);
		stockInfo.add(new JLabel("Stock: "));
		stockInfo.add(stockSpinner());
		
		//PRECIO
	  
		JPanel precioInfo =new JPanel();
		infoPanel.add(precioInfo, BorderLayout.PAGE_END);
		precioInfo.add(new JLabel("Precio: "));
		precioInfo.add(precioField());
		
		//Metemos los botones
		JPanel buttonPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonPanel);
		buttonPanel.add(okButton());
		buttonPanel.add(cancelButton());

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!hayLetras){
					TMenu tMenu = new TMenu(null,true,nombreMenu,floatVal, stock);
					setVisible(false);
					ctrl.accion(Events.ALTA_MENU, tMenu);
				}
				else
					JOptionPane.showMessageDialog(VAltaMenu.this, "Informacion invalida en el precio");
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

	JSpinner stockSpinner(){
		JSpinner stockSpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		stockSpin.setPreferredSize(new Dimension(50, 20));
		stock = (Integer) stockSpin.getValue();
		stockSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				stock = (Integer) stockSpin.getValue();
			}
		});
		return stockSpin;
	}
	
	JTextField nombreField(){
		JTextField nombreTextField = new JTextField(10);
		nombreMenu = new String();
		nombreTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				nombreMenu = nombreTextField.getText();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				nombreMenu = nombreTextField.getText();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				nombreMenu = nombreTextField.getText();
			}
		});
		return nombreTextField;
	}
	
	JTextField precioField(){
		JTextField precioTextField = new JTextField(10);
		precioTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				try{
					precioMenu = precioTextField.getText();
					floatVal = Float.parseFloat(precioMenu);
					hayLetras = false;
				} catch(NumberFormatException ex){
					hayLetras = true;
				}
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				try{
					precioMenu = precioTextField.getText();
					floatVal = Float.parseFloat(precioMenu);
					hayLetras = false;
				} catch(NumberFormatException ex){
					hayLetras = true;
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				try{
					precioMenu = precioTextField.getText();
					floatVal = Float.parseFloat(precioMenu);
					hayLetras = false;
				} catch(NumberFormatException ex){
					hayLetras = true;
				}
			}
		});
		return precioTextField;
	}

	@Override
	public void update(Object datos) {
		/*
		if (event == Events.ALTA_MENU_OK) 
			JOptionPane.showMessageDialog(this, "El menu se ha registrado con id " + (Integer) datos);
		else if (event == Events.ALTA_MENU_KO) //Posible error en float->float que admitimos (4,2)
			JOptionPane.showMessageDialog(this, "Error");
		else if(event == Events.ALTA_MENU_NOMBRE_MENU_YA_EXISTE)
			JOptionPane.showMessageDialog(this, "ERROR: Ya hay un menu con nombre " + (String) datos);
		else if(event == Events.ALTA_MENU_DATOS_INCORRECTOS)
			JOptionPane.showMessageDialog(this, "ERROR: Datos invalidos");
		*/
	}
}