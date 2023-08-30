package Presentacion.Turno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Negocio.Turno.TTurno;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;


public class VMostrarTodosTurno extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	
	private Controller ctrl;
	private JButton cancelButton;
	private TablaTurnos tablaTurnos;
	
	public VMostrarTodosTurno() {
		super("Mostrar todos los turnos");
		ctrl = Controller.obtenerInstancia();
		initIGUI();
	}
	
	public static void main(String[] args) {
		new VMostrarTodosTurno();
    }
	
	public void initIGUI() {
		JPanel main = new JPanel();
		setContentPane(main);
		//CANCEL BUTTON
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VTURNO, null);

			}
		});
		main.add(cancelButton);
		tablaTurnos = new TablaTurnos();
		main.add(tablaTurnos.toPanel("Turnos"));

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	class TablaTurnos extends AbstractObjectInfo<TTurno> {
		final String[] FIELDS = { "id", "dia", "capacidad", "precio", "costeServicio", "menuDia", "tipo", "complementoCafe", "costeComplemento",
				"complementoZumo", "complementoFruta"};

		TablaTurnos() {
			fields = FIELDS;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getDia();
			case 2:
				return data.get(rowIndex).getCapacidad();
			case 3:
				return data.get(rowIndex).getPrecio();
			case 4:
				return data.get(rowIndex).getCosteServicio();
			case 5:
				return data.get(rowIndex).getMenuDia();
			case 6:
				return data.get(rowIndex).getTipo();
			case 7:
				return data.get(rowIndex).getComplementoCafe();
			case 8:
				return data.get(rowIndex).getCosteComplemento();
			case 9:
				return data.get(rowIndex).getComplementoZumo();
			case 10:
				return data.get(rowIndex).getComplementoFruta();
			default:
				return null;
			}
		}
	}

	public void update(Object data) {
		tablaTurnos.setData((Collection<TTurno>) data);
	}
}