package Presentacion.Factura.VFacturaCasosUsos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Negocio.Empleado.TEmpleado;
import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaCompleta;
import Negocio.Factura.TLineaDePedido;
import Negocio.Mesa.TMesa;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarFactura_OK extends JFrame implements IGUI {
	JPanel panel;
	
	public VMostrarFactura_OK(){
		panel = new JPanel(new GridLayout(12, 1));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	@Override
	public void update(Object data) {
		TFacturaCompleta fc = (TFacturaCompleta) data;
		TFactura factura = fc.getTfactura();
		TMesa mesa = fc.getTmesa();
		TEmpleado empleado = fc.getTempleado();
		
		
		// Panel de datos
		JPanel idInfoPanel = new JPanel();
		idInfoPanel.setLayout(new GridLayout(6, 2));
		panel.add(idInfoPanel);
		
		//Factura
		JLabel lId = new JLabel("Id: ");
		JTextField id = new JTextField(); 
		id.setText(Integer.toString(factura.getId()));
		id.setEditable(false);
		idInfoPanel.add(lId);
		idInfoPanel.add(id);
		JLabel limp = new JLabel("Importe: ");
		JTextField imp = new JTextField();
		String importe = "Sin cerrar";
		if(!factura.getActivo()) importe = Float.toString(factura.getImporte());
		imp.setText(importe);
		imp.setEditable(false);
		idInfoPanel.add(limp);
		idInfoPanel.add(imp);
		
		
		//Mesa
		JLabel lIdM = new JLabel("Id Mesa: ");
		JTextField idM = new JTextField();
		idM.setText(Integer.toString(mesa.getId()));
		idM.setEditable(false);
		idInfoPanel.add(lIdM);
		idInfoPanel.add(idM);
		JLabel lNum = new JLabel("Num Mesa: ");
		JTextField num = new JTextField();
		num.setText(Integer.toString(mesa.getNumMesa()));
		num.setEditable(false);
		idInfoPanel.add(lNum);
		idInfoPanel.add(num);
		
		//Empleado
		JLabel lIdE = new JLabel("Id Empleado: ");
		JTextField idE = new JTextField();
		idE.setText(Integer.toString(empleado.getId()));
		idE.setEditable(false);
		idInfoPanel.add(lIdE);
		idInfoPanel.add(idE);
		JLabel ldni = new JLabel("DNI Empleado: ");
		JTextField dni = new JTextField();
		dni.setText(empleado.getDNI());
		dni.setEditable(false);
		idInfoPanel.add(ldni);
		idInfoPanel.add(dni);
		
		// Panel de tabla
		JPanel tablePanel = new JPanel();
		panel.add(tablePanel);
		
		String[] nombreColumnas = { "id menu", "cantidad" };
		JTable tabla = createTable(fc.getListaLineas().size(), 2, nombreColumnas);
		int i = 0;
		for (TLineaDePedido t : fc.getListaLineas()) {
			tabla.setValueAt(t.getIdMenu(), i, 0);
			tabla.setValueAt(t.getCantidad(), i, 1);
			i++;
		}
		JScrollPane scroll = new JScrollPane(tabla);
		tablePanel.add(scroll);
		
		
		// Panel de botones
		JPanel buttonsPanel = new JPanel();
		panel.add(buttonsPanel);
				
		// CANCEL BUTTON
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setSize(1000, 1000);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controller.obtenerInstancia().accion(Events.ABRIR_VFACTURA, null);

			}
		});
		buttonsPanel.add(cancelButton);
		
		this.pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
		
	}
	
	public JTable createTable(int filas, int columnas, String[] columns) {
		JTable table = new JTable();
		table.setModel(new DefaultTableModel() {

			public boolean isCellEditable(int row, int col) {
				return false;
			}

			public String getColumnName(int index) {
				return columns[index];
			}

			public int getColumnCount() {
				return columnas;
			}

			public int getRowCount() {
				return filas;
			}
		});
		return table;
	}
}
