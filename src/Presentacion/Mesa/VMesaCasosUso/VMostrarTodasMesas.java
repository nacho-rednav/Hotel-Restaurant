package Presentacion.Mesa.VMesaCasosUso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Negocio.Mesa.TMesa;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Ingrediente.VIngredienteCasosUso.VMostrarTodosIngredientes;

public class VMostrarTodasMesas extends JFrame implements IGUI {
	public Controller ctrl;
	private TablaMesa mesaTable;
	private JButton cancelButton;

	public VMostrarTodasMesas() {
		super("Mostrar todas las mesas");
		ctrl = Controller.obtenerInstancia();

		initIGUI();
	}

	public static void main(String[] args) {
		new VMostrarTodasMesas();
	}

	public void initIGUI() {
		JPanel main = new JPanel();
		setContentPane(main);
		//CANCEL BUTTON
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VMESA, null);

			}
		});
		main.add(cancelButton);
		mesaTable = new TablaMesa();
		main.add(mesaTable.toPanel("Mesas"));

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	class TablaMesa extends AbstractObjectInfo<TMesa> {
		final String[] FIELDS = { "id", "num mesa", "capacidad", "disponibilidad", "activo" };

		TablaMesa() {
			fields = FIELDS;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getNumMesa();
			case 2:
				return data.get(rowIndex).getCapacidad();
			case 3:
				return data.get(rowIndex).getDisponibilidad();
			case 4:
				return data.get(rowIndex).getActivo();
			default:
				return null;
			}
		}
	}

	@Override
	public void update(Object data) {
		mesaTable.setData((Collection<TMesa>) data);
	}
}