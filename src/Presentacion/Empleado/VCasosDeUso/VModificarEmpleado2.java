package Presentacion.Empleado.VCasosDeUso;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Empleado.TCamarero;
import Negocio.Empleado.TCocinero;
import Negocio.Empleado.TEmpleado;
import Negocio.Mesa.TMesa;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;


public class VModificarEmpleado2 extends JFrame implements IGUI {
	
	private JPanel panel;
	private JTextField id, nombre, dni, activo, sueldo;
	JComboBox<String> horario, tipo, idioma;
	JComboBox<Boolean> titulo;
	
	
	public VModificarEmpleado2() {
		super("Modificar Empleado"); 
		panel = new JPanel(new GridLayout(6, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		nombre = new JTextField();
		dni = new JTextField();
		horario = new JComboBox<String>();
		tipo = new JComboBox<String>();
		idioma = new JComboBox<String>();
		titulo = new JComboBox<Boolean>();
		activo = new JTextField();
		sueldo = new JTextField();
		
	}
	
	
	public void update(Object data) {
		TEmpleado tEmp = (TEmpleado) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tEmp.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		//Nombre
		JLabel lNom = new JLabel("Nombre: ");
		nombre.setText(tEmp.getNombre());
		nombre.setEditable(true);
		panel.add(lNom);
		panel.add(nombre);
		//DNI
		JLabel lDni = new JLabel("DNI: ");
		dni.setText(tEmp.getDNI());
		dni.setEditable(true);
		panel.add(lDni);
		panel.add(dni);
		//Sueldo
		JLabel lSue = new JLabel("Sueldo: ");
		sueldo.setText(Float.toString(tEmp.getSueldo()));
		sueldo.setEditable(true);
		panel.add(lSue);
		panel.add(sueldo);
		//Horario
		JLabel lHor = new JLabel("Horario: ");
		horario.addItem("Manyana");
		horario.addItem("Mediodia");
		horario.addItem("Tarde");
		horario.addItem("Noche");
		horario.setEditable(true);
		panel.add(lHor);
		panel.add(horario);
		//Idioma
		JLabel lIdi = new JLabel("Idioma: ");
		idioma.addItem("Espanyol");
		idioma.addItem("Frances");
		idioma.addItem("Ingles");
		idioma.addItem("Otro");
		idioma.setEditable(true);
		panel.add(lIdi);
		panel.add(idioma);
		//Titulo
		JLabel lTit = new JLabel("Titulo: ");
		titulo.addItem(true);
		titulo.addItem(false);
		titulo.setEditable(true);
		panel.add(lTit);
		panel.add(titulo);
		//Tipo
		JLabel lTipo = new JLabel("Tipo: ");
		tipo.addItem("Camarero");
		tipo.addItem("Cocinero");
		tipo.setEditable(true);
		tipo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String tipoEmpleado = (String) e.getItem();
					if (tipoEmpleado == "Camarero") {
						titulo.setVisible(false);
						idioma.setVisible(true);
					}
					if (tipoEmpleado == "Cocinero") {
						titulo.setVisible(true);
						idioma.setVisible(false);
					}
				}				
			}
		});
		panel.add(lTipo);
		panel.add(tipo);
		//Activo
		JLabel lAct = new JLabel("Activo: ");
		activo.setText(Boolean.toString(tEmp.getActivo()));
		activo.setEditable(false);
		panel.add(lAct);
		panel.add(activo);
		//Boton OK
		JButton okB = new JButton("OK");
		panel.add(okB);
				
		
		okB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				int _id = tEmp.getId();
				String _nombre = nombre.getText();
				Float _sueldo = Float.parseFloat(sueldo.getText());
				String _dni = dni.getText();
				String _horario = (String) horario.getSelectedItem();
				String _idioma = (String) idioma.getSelectedItem();
				Boolean _titulo = (Boolean) titulo.getSelectedItem();
				Boolean _activo = tEmp.getActivo();
				TEmpleado tEmpNew;
				if (tipo.getSelectedItem() == "Camarero") {
					tEmpNew = new TCamarero(_id, _nombre, _activo, _sueldo, _dni, _horario, _idioma);
				}
				else {
					tEmpNew = new TCocinero(_id, _nombre, _activo, _sueldo, _dni, _horario, _titulo);
				}
				Controller.obtenerInstancia().accion(Events.MODIFICAR_EMPLEADO2, tEmpNew);
				setVisible(false);
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(panel, "Introduzca datos válidos");
				setVisible(true);
				
				}
		}
	});
				
				
		//Boton Cancelar
		JButton cancelB = new JButton("Cancel");
		panel.add(cancelB);
				
		cancelB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_EMPLEADO, null);
			}
		});
		this.pack();
		setVisible(true);
	}
}