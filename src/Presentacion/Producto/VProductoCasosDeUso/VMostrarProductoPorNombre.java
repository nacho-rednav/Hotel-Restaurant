package Presentacion.Producto.VProductoCasosDeUso;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VMostrarProductoPorNombre extends JFrame implements IGUI{
	private String nombreProd;
	private Controller ctrl;
	private Integer id;
	
	public VMostrarProductoPorNombre() {
		super("Mostrar Un Producto");
		ctrl = Controller.obtenerInstancia();
		initGUI();
	}
	
	public void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);
		
		JPanel takingInfoPanel = new JPanel();
		mainPanel.add(takingInfoPanel);
		
		takingInfoPanel.add(new JLabel("Nombre Producto: "));
		takingInfoPanel.add(nombreField());
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);
		buttonsPanel.add(okButton());
		buttonsPanel.add(cancelButton());
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	JTextField nombreField(){
		JTextField nombreTextField = new JTextField(10);
		
		nombreTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				nombreProd = nombreTextField.getText();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				nombreProd = nombreTextField.getText();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				nombreProd = nombreTextField.getText();
			}
		});
		return nombreTextField;
	}
	
	private JButton okButton() {
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MOSTRAR_UN_PRODUCTO_POR_NOMBRE, nombreProd);
				ctrl.accion(Events.MOSTRAR_INGREDIENTES_DE_UN_PRODUCTO, id);
				setVisible(false);
			}
		});
		
		return okB;
	}
	
	private JButton cancelButton(){
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