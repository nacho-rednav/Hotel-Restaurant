
package Presentacion.Cliente;

import Presentacion.Controller.Controller;
import Presentacion.Controller.IGUI;
import java.util.Set;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Negocio.Cliente.TCliente;
import Presentacion.Controller.Events;

public class VCrearCliente extends JFrame implements IGUI {

	private Set<JPanel> jPanel;
	private ActionListener actionListener;
	private Set<JButton> jButton;
	private JLabel jLabel;
	private Set<JFrame> jFrame;
	private Set<JTextField> jTextField;

	private Controller ctrl;
	private String _nombre, _email;
	public static void main(String[] args) {
	new VCrearCliente();
	
    }
	public VCrearCliente() {
		super("Alta cliente");
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
		JPanel emailPanel = new JPanel();
		mainPanel.add(nombrePanel);
		mainPanel.add(emailPanel);

		nombrePanel.add(new JLabel("Nombre: "));
		nombrePanel.add(nombreField());

		nombrePanel.add(new JLabel("Email: "));
		nombrePanel.add(emailField());

		// Metemos los botones
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonPanel);
		buttonPanel.add(crearButton());
		buttonPanel.add(cancelButton());

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
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

	JTextField emailField() {
		JTextField emailField = new JTextField(10);

		emailField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				_email = emailField.getText();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				_email = emailField.getText();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				_email = emailField.getText();
			}
		});
		return emailField;
	}

	JButton crearButton() {
		JButton crearB = new JButton("OK");

		crearB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TCliente tProducto = new TCliente(null, true, _nombre,_email);
				ctrl.accion(Events.ALTA_CLIENTE, tProducto);
				setVisible(false);
			}
		});

		return crearB;
	}

	JButton cancelButton() {
		JButton cancelB = new JButton("Cancel");

		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ABRIR_VCLIENTE, null);
				setVisible(false);
			}
		});

		return cancelB;
	}

	

	public void update(Object data) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}