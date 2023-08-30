package Presentacion.Habitacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Habitacion.THabitacion;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarHabitacion_OK extends JFrame implements IGUI{
	
	JPanel main;
	
	public VMostrarHabitacion_OK(){
		main = new JPanel();
		this.setContentPane(main);
	}
	@Override
	public void update(Object data) {
		THabitacion habitacion = (THabitacion)data;
		String[] columnNames = {"ID", "Numero habitacion", "Planta", "Activo"};
		JTable table = new JTable();
		
		table.setModel(new DefaultTableModel() {
			public boolean isCellEditable(int row, int col) {
				return false;
			}

			public String getColumnName(int index) {
				return columnNames[index];
			}

			public int getColumnCount() {
				return 4;
			}

			public int getRowCount() {
				return 1;
			}
		});
		
		table.setValueAt(habitacion.getId(), 0, 0);
		table.setValueAt(habitacion.getNumero(), 0, 1);
		table.setValueAt(habitacion.getPlanta(), 0, 2);
		table.setValueAt(habitacion.getActivo() ? "SI" : "NO", 0, 3);
		
		JScrollPane scroll = new JScrollPane(table);
		main.add(scroll);
		//CANCEL BUTTON
		JButton cancelButton= new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						Controller.obtenerInstancia().accion(Events.ABRIR_VHABITACION, null);
								
					}
				});
		main.add(cancelButton);
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}

}
