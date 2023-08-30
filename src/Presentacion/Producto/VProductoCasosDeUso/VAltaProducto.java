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
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Negocio.Producto.TProducto;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VAltaProducto extends JFrame implements IGUI {
	
	private Controller ctrl;
	private String _nombre;
	
	public VAltaProducto(){
		super("Crear producto");
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
		
		JPanel nombrePanel = new JPanel();
		mainPanel.add(nombrePanel);
		
		nombrePanel.add(new JLabel("Nombre: "));
		nombrePanel.add(nombreField());
		
		//Metemos los botones
		JPanel buttonPanel =new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonPanel);
		buttonPanel.add(crearButton());
		buttonPanel.add(cancelButton());

		setLocationRelativeTo(getParent());
		setVisible(true);
		pack();
	}
	
	JTextField nombreField() {
	    JTextField nombreField = new JTextField(10);

	    nombreField.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            _nombre = nombreField.getText();
	        }
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	        	  _nombre = nombreField.getText();
	        }
	        @Override
	        public void changedUpdate(DocumentEvent e) {
	        	  _nombre = nombreField.getText();
	        }
	    });
	    return nombreField;
	}	
	
	JButton crearButton() {
		JButton crearB = new JButton("CREAR");
		
		crearB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TProducto tProducto = new TProducto(null, true, _nombre);
				ctrl.accion(Events.ALTA_PRODUCTO , tProducto);
				setVisible(false);
				ctrl.accion(Events.ABRIR_VPRODUCTO, null);
			}
		});
		
		return crearB;
	}
	
	JButton cancelButton() {
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
		/*if (event == Events.ALTA_PRODUCTO_OK) 
		JOptionPane.showMessageDialog(this, "El producto se ha registrado con id: " + (Integer) datos);
	else if(event == Events.ALTA_PRODUCTO_NOMBRE_YA_EXISTE)
		JOptionPane.showMessageDialog(this, "ERROR: Ya hay un producto con nombre " + (String) datos);
	else if (event == Events.ALTA_PRODUCTO_KO)
		JOptionPane.showMessageDialog(this, "Error");
	else if (event == Events.ALTA_PRODUCTO_DATOS_INVALIDOS)
		JOptionPane.showMessageDialog(this, "ERROR: Datos invalidos");*/
		
	}
	

}
