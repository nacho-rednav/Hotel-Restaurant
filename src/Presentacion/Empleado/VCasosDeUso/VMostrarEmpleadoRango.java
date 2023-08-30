package Presentacion.Empleado.VCasosDeUso;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Negocio.Queries.TQuery;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VMostrarEmpleadoRango extends JFrame implements IGUI{
	private Controller ctrl;
	private TQuery rango;
	private JTextField min, max;
	
	public VMostrarEmpleadoRango() {
		super("Mostrar Empleados Despedidos en Rango Salarial");
		ctrl = Controller.obtenerInstancia();
		initGUI();
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				
//			}
//		});
	}
	
	public void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);
		
		JPanel takingInfoPanel = new JPanel();
		mainPanel.add(takingInfoPanel);
		
		takingInfoPanel.add(new JLabel("Salario minimo: "));
		min = textField();
		takingInfoPanel.add(min);
		takingInfoPanel.add(new JLabel("Salario maximo: "));
		max = textField();
		takingInfoPanel.add(max);
		
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);
		buttonsPanel.add(okButton());
		buttonsPanel.add(cancelButton());

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	JTextField textField() {
		JTextField _textField = new JTextField();
		_textField.setPreferredSize(new Dimension(50, 20));
		_textField.setEditable(true);
		return _textField;
	}
	
	private JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rango = new TQuery(Float.parseFloat(min.getText()),
										Float.parseFloat(max.getText()));
					setVisible(false);
					ctrl.accion(Events.MOSTRAR_EMPLEADO_RANGO, rango);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(okB, "Introduzca datos válidos");
					setVisible(true);
					
					}
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
				ctrl.accion(Events.ABRIR_VEMPLEADO, null);
			}
		});
		
		return cancelB;
	}
	

	@Override
	public void update(Object data) {
	}
}
