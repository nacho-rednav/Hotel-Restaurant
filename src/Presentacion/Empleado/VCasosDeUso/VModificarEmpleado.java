package Presentacion.Empleado.VCasosDeUso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Negocio.Empleado.TCamarero;
import Negocio.Empleado.TCocinero;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VModificarEmpleado extends JFrame implements IGUI {
	private Controller ctrl;
	private Float floatVal;
	private String _nombre;
	private String _sueldo;
	private String _dni;
	private int _id;
	private String _titulo;
	private String _horario;
	private String _idioma;
	Boolean hayLetras;
	String tipoEmpleado = "Camarero";// 0Camarero 1Cocinero
	private JComboBox<String> comboHorario;
	private JComboBox<String> comboIdioma;
	private JComboBox<Boolean> comboTitulo;
	private JComboBox<String> comboTipo;
	private JButton returnButton;

	public VModificarEmpleado() {
		super("Modificar un empleado");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}

	void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(infoPanel);
		// IDEmpleado
		JPanel idInfo = new JPanel();
		infoPanel.add(idInfo);
		idInfo.add(new JLabel("Id del empleado a modificar: "));
		idInfo.add(idSpinner());

		// Metemos los botones
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonPanel);
		buttonPanel.add(okButton());
		buttonPanel.add(cancelButton());

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	JButton okButton() {
		JButton okB = new JButton("OK");

		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MODIFICAR_EMPLEADO, _id); 
			}

		});

		return okB;
	}

	JSpinner idSpinner() {
		JSpinner capacitySpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		capacitySpin.setPreferredSize(new Dimension(50, 20));
		_id = (Integer) capacitySpin.getValue();
		capacitySpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_id = (Integer) capacitySpin.getValue();
			}
		});
		return capacitySpin;
	}

	JButton cancelButton() {
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

		// if (event == Events.MODIFICAR_EMPLEADO_OK)
		// JOptionPane.showMessageDialog(this, "El empleado de id " + (Integer)
		// datos + " ha sido modificado");
		// else if (event == Events.MODIFICAR_EMPLEADO_ID_INEXISTENTE)
		// JOptionPane.showMessageDialog(this, "ERROR: El id " + (Integer) datos
		// + " no esta registrado");
		// else if(event == Events.ALTA_EMPLEADO_DNI_YA_EXISTE)
		// JOptionPane.showMessageDialog(this, "ERROR: DNI de empleado " +
		// (String) datos + " ya registrado");
		// else if(event == Events.MODIFICAR_EMPLEADO_DATOS_INCORRECTOS)
		// JOptionPane.showMessageDialog(this, "ERROR: Datos invalidos");
	}
}