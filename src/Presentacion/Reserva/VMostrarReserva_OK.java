package Presentacion.Reserva;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Negocio.Habitacion.THabitacion;
import Negocio.Reserva.TReservaCompleta;
import Negocio.ReservaServicio.TCompraServicio;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarReserva_OK extends JFrame implements IGUI {
	JPanel main;
	private ArrayList<Integer> habitaciones;
	private ArrayList<Integer> servicios;
	
	public VMostrarReserva_OK(){
		main = new JPanel(new FlowLayout());
		this.setContentPane(main);
	}
	@Override
	public void update(Object data) {
		TReservaCompleta reserva = (TReservaCompleta)data;
		String[] columnNames = {"ID", "Codigo", "NumDias", "ID Cliente", "ID Recepcionista", "FechaIni", "Activo"};
		JTable table = new JTable();
		
		table.setModel(new DefaultTableModel() {
			public boolean isCellEditable(int row, int col) {
				return false;
			}

			public String getColumnName(int index) {
				return columnNames[index];
			}

			public int getColumnCount() {
				return 7;
			}

			public int getRowCount() {
				return 1;
			}
		});
		
		table.setValueAt(reserva.getReserva().getId(), 0, 0);
		table.setValueAt(reserva.getReserva().getCodigo(), 0, 1);
		table.setValueAt(reserva.getReserva().getNumDias(), 0, 2);
		table.setValueAt(reserva.getReserva().getId_Cliente(), 0, 3);
		table.setValueAt(reserva.getReserva().getId_Recepcionista(), 0, 4);
		table.setValueAt(reserva.getReserva().getFechaInicio(), 0, 5);
		table.setValueAt(reserva.getReserva().getActivo() ? "SI" : "NO", 0, 6);
		
		habitaciones = new ArrayList<Integer>();
		for (THabitacion h : reserva.getHabitaciones()) {
			habitaciones.add(h.getId());
		}
		
		
		servicios = new ArrayList<Integer>();
		for (TCompraServicio s : reserva.getServicios()) {
			servicios.add(s.getId_Servicio());
		}
		
		JPanel listas = new JPanel(new GridLayout(4, 2));
		JLabel habs = new JLabel("Habitaciones");
		JTextField thabs = new JTextField();
		thabs.setText(habitaciones.toString());
		thabs.setEditable(false);
		JLabel servs = new JLabel("Servicios comprados");
		JTextField tservs = new JTextField();
		tservs.setText(servicios.toString());
		tservs.setEditable(false);
		listas.add(habs);
		listas.add(thabs);
		listas.add(servs);
		listas.add(tservs);
		
		JScrollPane scroll = new JScrollPane(table);
		main.add(scroll);
		main.add(listas);
		//CANCEL BUTTON
		JButton cancelButton= new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						Controller.obtenerInstancia().accion(Events.ABRIR_VRESERVA, null);
								
					}
				});
		main.add(cancelButton);
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}
