
package Presentacion.Cliente;

import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JPanel;
import javax.swing.JButton;

import Negocio.Cliente.TCliente;

import javax.swing.JFrame;


public class VMostrarTodosCliente extends JFrame implements IGUI {
	
	private Controller ctrl;
	private JButton cancelButton;
	private TablaCliente tablaClientes;

	public VMostrarTodosCliente() {
		super("Mostrar todos los clientes");
		ctrl = Controller.obtenerInstancia();

		initIGUI();
	}
	public void initIGUI() {
		JPanel main = new JPanel();
		setContentPane(main);
		//CANCEL BUTTON
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VCLIENTE, null);

			}
		});
		main.add(cancelButton);
		tablaClientes = new TablaCliente();
		main.add(tablaClientes.toPanel("Clientes"));

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	class TablaCliente extends AbstractObjectInfo<TCliente> {
		final String[] FIELDS = { "id", "nombre", "email", "activo" };

		TablaCliente() {
			fields = FIELDS;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return data.get(rowIndex).getID();
			case 1:
				return data.get(rowIndex).getNombre();
			case 2:
				return data.get(rowIndex).getEmail();
			case 3:
				return data.get(rowIndex).getActivo();
			default:
				return null;
			}
		}
	}

	@Override
	public void update(Object data) {
		tablaClientes.setData((Collection<TCliente>) data);
	}
	
}