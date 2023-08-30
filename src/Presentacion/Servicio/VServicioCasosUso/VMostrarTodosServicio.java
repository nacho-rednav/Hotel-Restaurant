package Presentacion.Servicio.VServicioCasosUso;

import javax.swing.JFrame;

import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

import java.util.Collection;

import javax.swing.JPanel;

import Negocio.Servicio.TServicio;

import javax.swing.JButton;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VMostrarTodosServicio extends JFrame implements IGUI {
	private Controller ctrl;
	private TablaServicios serviciosTable;
	private JButton cancelButton;
	
	public VMostrarTodosServicio() {
		super("Mostrar todos los servicios");
		ctrl = Controller.obtenerInstancia();
		initIGUI();
	}
	
	public static void main(String[] args) {
		new VMostrarTodosServicio();
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
		serviciosTable = new TablaServicios();
		main.add(serviciosTable.toPanel("Servicios"));

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	class TablaServicios extends AbstractObjectInfo<TServicio> {
		final String[] FIELDS = { "id", "tipo", "descripcion", "precio", "activo" };

		TablaServicios() {
			fields = FIELDS;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getTipo();
			case 2:
				return data.get(rowIndex).getDescripcion();
			case 3:
				return data.get(rowIndex).getPrecio();
			case 4:
				return data.get(rowIndex).getActivo();
			default:
				return null;
			}
		}
	}

	public void update(Object data) {
		serviciosTable.setData((Collection<TServicio>) data); 
	}
}