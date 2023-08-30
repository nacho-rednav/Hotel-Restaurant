package Presentacion.Factura.VFacturaCasosUsos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Negocio.Factura.TFactura;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VMostrarTodasFacturas extends JFrame implements IGUI {
	public Controller ctrl;
	private TablaFactura facturaTable;
	private JButton cancelButton;
	
	
	public VMostrarTodasFacturas() {
		super("Mostrar todas las facturas");
		ctrl = Controller.obtenerInstancia();
		
		initIGUI();

	}
	
	public void initIGUI(){
		JPanel main = new JPanel();
		setContentPane(main);
		// CANCEL BUTTON
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VFACTURA, null);

			}
		});
		main.add(cancelButton);
		facturaTable = new TablaFactura();
		main.add(facturaTable.toPanel("Facturas"));

		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	
	class TablaFactura extends AbstractObjectInfo<TFactura>{
		final String[] FIELDS = {"id factura", "id mesa", "id empleado", "importe", "hora cobro", "activo"};

		TablaFactura(){
			fields = FIELDS;
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getIdMesa();
			case 2:
				return data.get(rowIndex).getIdEmpleado();
			case 3:
				return data.get(rowIndex).getImporte();
			case 4:
				return data.get(rowIndex).getHoraCobro();
			case 5:
				return data.get(rowIndex).getActivo();
			default:
				return null;
			}
		}
	}


	@Override
	public void update(Object data) {
			facturaTable.setData((Collection<TFactura>)data);
	}
}
