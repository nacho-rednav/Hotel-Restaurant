package Presentacion.Habitacion;

import javax.swing.JFrame;

import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

import java.util.Collection;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import Negocio.Habitacion.THabitacion;

public class VMostrarTodasHabitaciones extends JFrame implements IGUI {

	public Controller ctrl;
	private TablaHabitaciones habitacionTable;
	private JButton cancelButton;

	public VMostrarTodasHabitaciones() {
		super("Mostrar todas las habitaciones");
		ctrl = Controller.obtenerInstancia();

		initIGUI();
	}

	public static void main(String[] args) {
		new VMostrarTodasHabitaciones();
	}

	public void initIGUI() {
		JPanel main = new JPanel();
		setContentPane(main);
		//CANCEL BUTTON
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VHABITACION, null);

			}
		});
		main.add(cancelButton);
		habitacionTable = new TablaHabitaciones();
		main.add(habitacionTable.toPanel("Habitaciones"));

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	class TablaHabitaciones extends AbstractObjectInfo<THabitacion> {
		final String[] FIELDS = { "id", "numero", "planta",  "activo" };

		TablaHabitaciones() {
			fields = FIELDS;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getNumero();
			case 2:
				return data.get(rowIndex).getPlanta();
			case 3:
				return data.get(rowIndex).getActivo();
			default:
				return null;
			}
		}
	}

	@Override
	public void update(Object data) {
		habitacionTable.setData((Collection<THabitacion>) data);
	}
}