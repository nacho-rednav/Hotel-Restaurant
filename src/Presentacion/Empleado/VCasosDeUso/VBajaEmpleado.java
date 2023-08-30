package Presentacion.Empleado.VCasosDeUso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Empleado.VEmpleado;

@SuppressWarnings("serial")
public class VBajaEmpleado extends JFrame implements IGUI {
	private JTextField nombreIng;
	private JComboBox<Boolean> comboAlerg;
	private int spinValue;
	public Controller ctrl;
	private JButton returnButton;
	
	public VBajaEmpleado() {
		super("Baja de Empleado");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});	
	}
	
	public void initGUI(){
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		
		//ATRIBUTOS
		//IDEMPLEADO
		JPanel capacityInfo =new JPanel();
		mainPanel.add(capacityInfo, BorderLayout.PAGE_START);
		capacityInfo.add(new JLabel("Identificador: "));
		capacityInfo.add(idSpinner());
        		
		//BOTONES OK CANCEL
		JPanel panelButton = new JPanel(new FlowLayout());
		JPanel infPanel = new JPanel(new BorderLayout());
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VEMPLEADO, null);
			}
		});
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				
				ctrl.accion(Events.BAJA_EMPLEADO, spinValue);
			}
		});
		 
		panelButton.add(ok, BorderLayout.PAGE_END);
		panelButton.add(cancel, BorderLayout.PAGE_END);
		infPanel.add(panelButton);
		mainPanel.add(infPanel);
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);	
	}
	
	JSpinner idSpinner(){
		JSpinner idSpin =new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idSpin.setPreferredSize(new Dimension(50, 20));
		spinValue = (Integer) idSpin.getValue();
		idSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				spinValue = (Integer) idSpin.getValue();
			}
		});
		return idSpin;
	}
	
	@Override
	public void update(Object data) {
//		if (event == Events.BAJA_EMPLEADO_OK) 
//			JOptionPane.showMessageDialog(null, "El empleado de id " + (Integer) datos + " ha sido dado de baja");
//		else if (event == Events.BAJA_EMPLEADO_KO)
//			JOptionPane.showMessageDialog(this, "ERROR");
//		else if(event == Events.BAJA_EMPLEADO_ID_INEXISTENTE)
//			JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos + " no esta registrado");
//		else if(event == Events.BAJA_EMPLEADO_YA_INACTIVO)
//			JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos + " ya estaba dado de baja");
		
	}
}