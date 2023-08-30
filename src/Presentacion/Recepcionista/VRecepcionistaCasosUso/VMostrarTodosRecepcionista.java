/**
 * 
 */
package Presentacion.Recepcionista.VRecepcionistaCasosUso;

import javax.swing.JFrame;

import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

import java.util.Collection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import Negocio.Recepcionista.TRecepcionista;


public class VMostrarTodosRecepcionista extends JFrame implements IGUI {
	private Controller ctrl;
	private TablaRecepcionistas recepcionistasTable;
	private JButton cancelButton;
	
	public VMostrarTodosRecepcionista() {
		super("Mostrar todos los recepcionistas");
		ctrl = Controller.obtenerInstancia();
		initIGUI();
	}
	
	public static void main(String[] args) {
		new VMostrarTodosRecepcionista();
	}
	
	public void initIGUI() {
		JPanel main = new JPanel();
		setContentPane(main);
		//CANCEL BUTTON
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VRECEPCIONISTA, null);

			}
		});
		main.add(cancelButton);
		recepcionistasTable = new TablaRecepcionistas();
		main.add(recepcionistasTable.toPanel("Recepcionistas"));

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	class TablaRecepcionistas extends AbstractObjectInfo<TRecepcionista> {
		final String[] FIELDS = { "id", "nombre", "numero", "salario", "activo" };

		TablaRecepcionistas() {
			fields = FIELDS;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getNombre();
			case 2:
				return data.get(rowIndex).getNumRecepcionista();
			case 3:
				return data.get(rowIndex).getSalario();
			case 4:
				return data.get(rowIndex).getActivo();
			default:
				return null;
			}
		}
	}

	public void update(Object data) {
		recepcionistasTable.setData((Collection<TRecepcionista>) data);
	}
}