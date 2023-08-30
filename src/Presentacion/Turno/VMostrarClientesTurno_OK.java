package Presentacion.Turno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Negocio.Cliente.TCliente;
import Negocio.Turno.TTurno;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarClientesTurno_OK extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;

	private Controller ctrl;
	private JButton cancelButton;
	private TablaClientes tablaClientes;
	
	public VMostrarClientesTurno_OK() {
		super("Mostrar los clientes de un turno");
		ctrl = Controller.obtenerInstancia();
		initIGUI();
	}
	
	public void initIGUI() {
		JPanel mainPanel = new JPanel();
		setContentPane(mainPanel);
		
		//CANCEL BUTTON
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VTURNO, null);
			}
		});
		
		mainPanel.add(cancelButton);
		tablaClientes = new TablaClientes();
		mainPanel.add(tablaClientes.toPanel("Clientes"));
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	class TablaClientes extends AbstractObjectInfo<TCliente> {
		private static final long serialVersionUID = 1L;
		
		final String[] FIELDS = { "id", "nombre", "email", "activo" };

		TablaClientes() {
			fields = FIELDS;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex) {
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
