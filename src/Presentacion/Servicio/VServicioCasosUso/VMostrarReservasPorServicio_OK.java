/**
 * 
 */
package Presentacion.Servicio.VServicioCasosUso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Negocio.Reserva.TReserva;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarReservasPorServicio_OK extends JFrame implements IGUI {
	public Controller ctrl;
	private TablaReservas reservaTable;
	private JButton cancelButton;

	public VMostrarReservasPorServicio_OK() {
		super("Mostrar todas las reservas");
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
				ctrl.accion(Events.ABRIR_VSERVICIO, null);

			}
		});
		main.add(cancelButton);
		reservaTable = new TablaReservas();
		main.add(reservaTable.toPanel("Reservas"));

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	class TablaReservas extends AbstractObjectInfo<TReserva> {
		final String[] FIELDS = { "id", "codigoReserva", "numDias", "fechaInicio", "activo" };

		TablaReservas() {
			fields = FIELDS;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getCodigo();
			case 2:
				return data.get(rowIndex).getNumDias();
			case 3:
				return data.get(rowIndex).getFechaInicio();
			case 4:
				return data.get(rowIndex).getActivo();
			default:
				return null;
			}
		}
	}

	@Override
	public void update(Object data) {
		reservaTable.setData((Collection<TReserva>) data);
	}
}