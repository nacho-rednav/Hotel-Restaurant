package Presentacion.Ingrediente.VIngredienteCasosUso;

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
public class VMostrarIngredientePorNombre extends JFrame implements IGUI{
	private String nombreIng;
	private Controller ctrl;
	private Integer id;
	
	public VMostrarIngredientePorNombre() {
		super("Mostrar Un Ingrediente");
		ctrl = Controller.obtenerInstancia();
		initGUI();
	}
	
	public void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);
		
		JPanel takingInfoPanel = new JPanel();
		mainPanel.add(takingInfoPanel);
		
		takingInfoPanel.add(new JLabel("Nombre Ingrediente: "));
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
	
	private JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				ctrl.accion(Events.MOSTRAR_UN_INGREDIENTE_POR_NOMBRE, nombreIng);
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
				ctrl.accion(Events.ABRIR_VINGREDIENTE, null);
			}
		});
		
		return cancelB;
	}
	
	@Override
	public void update(Object data) {	
	}
}