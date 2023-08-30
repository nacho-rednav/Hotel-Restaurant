package Presentacion.Reserva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Habitacion.THabitacion;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VDesvincularHabitacionReserva_OK extends JFrame implements IGUI{
	public void update(Object data) {
		JOptionPane.showMessageDialog(this, "Habitacion desvinculada correctamente");
		Controller.obtenerInstancia().accion(Events.ABRIR_VRESERVA, null);
	}
}
