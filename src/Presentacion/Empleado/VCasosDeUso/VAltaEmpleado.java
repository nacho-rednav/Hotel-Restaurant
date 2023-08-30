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
import javax.swing.JTextField;
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
import Presentacion.Empleado.VEmpleado;

@SuppressWarnings("serial")
public class VAltaEmpleado extends JFrame implements IGUI {
	private Controller ctrl;
	private Float floatVal;
	private String _nombre,_sueldo,_dni,_titulo,_horario, _idioma, tipoEmpleado = "Camarero", selectedIdioma, selectedHorario;
	private Boolean hayLetras, selectedTitulo;
	//private JComboBox<String> comboHorario;
	//private JComboBox<String> comboIdioma;
	//private JComboBox<Boolean> comboTitulo;
	private JComboBox<String> comboTipo;
	private JButton returnButton;
	
	public VAltaEmpleado(){
		super("Alta de nuevo empleado");
		ctrl = Controller.obtenerInstancia();
		floatVal = (float) 0.0;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		
		JPanel infoPanel = new JPanel();
		        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		        mainPanel.add(infoPanel);
		//NOMBRE
		JPanel nombreInfo =new JPanel();
		infoPanel.add(nombreInfo, BorderLayout.PAGE_START);
		nombreInfo.add(new JLabel("Nombre: "));
		nombreInfo.add(nombreField());
		//SUELDO
		JPanel sueldoInfo =new JPanel();
		infoPanel.add(sueldoInfo);
		sueldoInfo.add(new JLabel("Sueldo: "));
		sueldoInfo.add(sueldoField());
		//DNI
		JPanel dniInfo =new JPanel();
		infoPanel.add(dniInfo);
		dniInfo.add(new JLabel("DNI: "));
		dniInfo.add(dniField());
		//HORARIO
		JPanel horarioInfo = new JPanel();
		infoPanel.add(horarioInfo);
		/*comboHorario = new JComboBox<String>();
		*/
		horarioInfo.add(new JLabel("Horario:"));
		horarioInfo.add(horarioBox());
		//horarioInfo.add(comboHorario);
		//;
		
		//IDIOMA(SIgue desps)
		JPanel idiomaInfo = new JPanel();
		
		
		//TITULO(SIgue desps)
		JPanel tituloInfo = new JPanel();
		
		//TIPO
		JPanel tipoInfo = new JPanel();
		infoPanel.add(tipoInfo);
		comboTipo = new JComboBox<String>();
		comboTipo.addItem("Camarero");
		comboTipo.addItem("Cocinero");
		
		
		tipoInfo.add(new JLabel("Tipo:"));
		tipoInfo.add(comboTipo);
		comboTipo.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					tipoEmpleado = (String) e.getItem();
					if(tipoEmpleado.equals("Camarero")){
						tituloInfo.setVisible(false);
						idiomaInfo.setVisible(true);
					} else if(tipoEmpleado.equals("Cocinero")){
						tituloInfo.setVisible(true);
						idiomaInfo.setVisible(false);
					}	
				}
			}
		});
			
		infoPanel.add(idiomaInfo);

		idiomaInfo.add(new JLabel("Idioma:"));
		idiomaInfo.add(idiomaBox());
		//idiomaInfo.add(comboIdioma);
		//selectedIdioma = (String) comboIdioma.getSelectedItem();
			
		infoPanel.add(tituloInfo);
		
		tituloInfo.add(new JLabel("Titulo: "));
		tituloInfo.add(tituloBox());
		
	    tituloInfo.setVisible(false);
		
		//Metemos los botones
		JPanel buttonPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonPanel);
		
		buttonPanel.add(okButton());
		buttonPanel.add(cancelButton());
		
		setLocationRelativeTo(getParent());
		setVisible(true);
		pack();
	}
	
	JComboBox<String> horarioBox(){
		JComboBox<String> comboHorario = new JComboBox<String>();
		comboHorario.addItem("Manyana");
		comboHorario.addItem("Mediodia");
		comboHorario.addItem("Tarde");
		comboHorario.addItem("Noche");
		
		selectedHorario = (String) comboHorario.getSelectedItem();
		
		comboHorario.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					selectedHorario = (String) e.getItem();
			}
		});
		
		return comboHorario;
	}
	JComboBox<String> idiomaBox(){
		JComboBox<String> comboIdioma = new JComboBox<String>();
		
		comboIdioma.addItem("Ingles");
		comboIdioma.addItem("Italiano");
		comboIdioma.addItem("Frances");
	
		selectedIdioma = (String) comboIdioma.getSelectedItem();
		
		comboIdioma.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					selectedIdioma = (String) e.getItem();
			}
		});
		
		return comboIdioma;
	}	
	JComboBox<Boolean> tituloBox(){
		
		JComboBox<Boolean> comboTitulo = new JComboBox<Boolean>();
		comboTitulo.addItem(true);
		comboTitulo.addItem(false);

		
		selectedTitulo = (Boolean) comboTitulo.getSelectedItem();
		
		comboTitulo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
					selectedTitulo = (Boolean) e.getItem();
			}
		});
		
		return comboTitulo;
	}	
	
	
	
	
	JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hayLetras)
					JOptionPane.showMessageDialog(VAltaEmpleado.this, "Informacion invalida en el sueldo");
				else{
					if(tipoEmpleado.equals("Camarero")){
						TCamarero tCam = new TCamarero(null,  _nombre,true, floatVal, _dni, selectedHorario,selectedIdioma);
						ctrl.accion(Events.ALTA_EMPLEADO, tCam);
					}else if(tipoEmpleado.equals("Cocinero")){
						TCocinero tCoc = new TCocinero(null,  _nombre,true, floatVal, _dni, selectedHorario,selectedTitulo);
						ctrl.accion(Events.ALTA_EMPLEADO, tCoc);
					}
				}
			}
		}); 
		
		return okB;
	}

	JTextField nombreField(){
	    JTextField nombreField = new JTextField(10);
	    _nombre= new String();
	    nombreField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            _nombre= nombreField.getText();
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	  _nombre= nombreField.getText();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	  _nombre= nombreField.getText();
	        }
	    });
	    return nombreField;
	}

	JTextField dniField(){
	    JTextField dniField = new JTextField(10);
	    _dni= new String();
	    dniField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            _dni= dniField.getText();
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	_dni= dniField.getText();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	_dni= dniField.getText();
	        }
	    });
	    return dniField;
	}

	JTextField sueldoField(){
	    JTextField precioTextField = new JTextField(10);
	    hayLetras = false;
	   
	    precioTextField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	        	try{
		            _sueldo = precioTextField.getText();
		            floatVal = Float.valueOf(_sueldo).floatValue();
		            hayLetras = false;
	        	}
	        	catch(NumberFormatException exc){
	        		hayLetras = true;
	        	}
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	try{
		            _sueldo = precioTextField.getText();
		            floatVal = Float.valueOf(_sueldo).floatValue();
		            hayLetras = false;
	        	}
	        	catch(NumberFormatException exc){
	        		hayLetras = true;
	        	}
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	try{
		            _sueldo = precioTextField.getText();
		            floatVal = Float.valueOf(_sueldo).floatValue();
		            hayLetras = false;
	        	}
	        	catch(NumberFormatException exc){
	        		hayLetras = true;
	        	}
	        }
	    });
	    return precioTextField;
	}

	JButton cancelButton(){
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
//		if (event == Events.ALTA_EMPLEADO_OK) 
//			JOptionPane.showMessageDialog(this, "El empleado se ha registrado con id " + (Integer) datos);
//		else if (event == Events.ALTA_EMPLEADO_KO)
//			JOptionPane.showMessageDialog(this, "Error");
//		else if(event == Events.ALTA_EMPLEADO_DNI_YA_EXISTE)
//			JOptionPane.showMessageDialog(this, "ERROR: Ya hay un empleado con dni " + (String) datos);
//		else if(event == Events.ALTA_EMPLEADO_DATOS_INCORRECTOS)
//			JOptionPane.showMessageDialog(this, "ERROR: Datos invalidos");		
	}
}