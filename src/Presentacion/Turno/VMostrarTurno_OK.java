package Presentacion.Turno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Turno.TTurno;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Turno.VMostrarTodosTurno.TablaTurnos;

public class VMostrarTurno_OK extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	
	private Controller ctrl;
	private TablaTurnos turnosTable;
	private JButton cancelButton;
	
	JPanel main;
	
	public VMostrarTurno_OK() {
		super("Mostrar un turno");
		ctrl = Controller.obtenerInstancia();
		initGUI();
	}
	
	private void initGUI() {
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
		turnosTable = new TablaTurnos();
		main.add(turnosTable.toPanel("Turnos"));
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

	class TablaTurnos extends AbstractObjectInfo<TTurno> {
		private static final long serialVersionUID = 1L;

		final String[] FIELDS = {"id", "dia", "capacidad", "precio", "costeServicio", "menuDia", "tipo", "complementoCafe", "costeComplemento",
				"complementoZumo", "complementoFruta"};
		
		TablaTurnos() {
			fields = FIELDS;
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex) {
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
	
	@Override
	public void update(Object data) {
		Collection<TTurno> turnos = new ArrayList<TTurno>();
		turnos.add((TTurno) data);
		turnosTable.setData(turnos);
	}
}
