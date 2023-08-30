package Presentacion.Servicio.VServicioCasosUso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Habitacion.THabitacion;
import Negocio.Recepcionista.TRecepcionista;
import Negocio.Servicio.TServicio;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarServicio_OK extends JFrame implements IGUI{
	
	JPanel main;
	
	public VMostrarServicio_OK(){
		main = new JPanel();
		this.setContentPane(main);
	}
	@Override
	public void update(Object data) {
		TServicio servicio = (TServicio)data;
		String[] columnNames = {"ID", "Tipo", "Descripcion", "Precio", "Activo"};
		JTable table = new JTable();
		
		table.setModel(new DefaultTableModel() {
			public boolean isCellEditable(int row, int col) {
				return false;
			}

			public String getColumnName(int index) {
				return columnNames[index];
			}

			public int getColumnCount() {
				return 5;
			}

			public int getRowCount() {
				return 1;
			}
		});
		
		table.setValueAt(servicio.getId(), 0, 0);
		table.setValueAt(servicio.getTipo(), 0, 1);
		table.setValueAt(servicio.getDescripcion(), 0, 2);
		table.setValueAt(servicio.getPrecio(), 0, 3);
		table.setValueAt(servicio.getActivo() ? "SI" : "NO", 0, 4);
		
		JScrollPane scroll = new JScrollPane(table);
		main.add(scroll);
		//CANCEL BUTTON
		JButton cancelButton= new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						Controller.obtenerInstancia().accion(Events.ABRIR_VSERVICIO, null);
								
					}
				});
		main.add(cancelButton);
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

}
