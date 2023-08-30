package Presentacion.Mesa.VMesaCasosUso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Mesa.TMesa;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarMesa_OK extends JFrame implements IGUI{
	
	JPanel main;
	
	public VMostrarMesa_OK(){
		main = new JPanel();
		this.setContentPane(main);
	}
	@Override
	public void update(Object data) {
		TMesa mesa = (TMesa)data;
		String[] columnNames = {"ID", "Numero mesa", "Capacidad", "Disponibilidad", "Activo"};
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
		
		table.setValueAt(mesa.getId(), 0, 0);
		table.setValueAt(mesa.getNumMesa(), 0, 1);
		table.setValueAt(mesa.getCapacidad(), 0, 2);
		table.setValueAt(mesa.getDisponibilidad(), 0, 3);
		table.setValueAt(mesa.getActivo() ? "SI" : "NO", 0, 4);
		
		JScrollPane scroll = new JScrollPane(table);
		main.add(scroll);
		//CANCEL BUTTON
		JButton cancelButton= new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						Controller.obtenerInstancia().accion(Events.ABRIR_VMESA, null);
								
					}
				});
		main.add(cancelButton);
		pack();
		setVisible(true);
	}

}
