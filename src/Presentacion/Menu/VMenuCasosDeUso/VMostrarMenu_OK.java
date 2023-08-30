package Presentacion.Menu.VMenuCasosDeUso;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import Negocio.Menu.TMenu;
import Negocio.Menu.TMenuProds;
import Negocio.Producto.TProducto;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VMostrarMenu_OK extends JFrame implements IGUI{
	private Controller ctrl;
	private Integer id;
	private MenuLabel menuInfo;
	private TablaProductos prods;
	private JButton returnButton;
	
	public static void main(String[] args) {
		new VMostrarMenu_OK();
	}
	
	public VMostrarMenu_OK() {
		super("Mostrar Un Menu");
		ctrl = Controller.obtenerInstancia();
		initGUI();
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//			}
//		});
	}
	
	public void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);
		
		JPanel takingInfoPanel = new JPanel();
		mainPanel.add(takingInfoPanel);
		
		
		menuInfo = new MenuLabel();
		JPanel showingInfoPanel = new JPanel();
		mainPanel.add(showingInfoPanel);
		showingInfoPanel.add(menuInfo);
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);
		buttonsPanel.add(cancelButton());
		
		prods = new TablaProductos();
		mainPanel.add(prods.toPanel("Productos"));

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	
	
	
	private JButton cancelButton(){
		JButton cancelB = new JButton("Cancel");
		
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VMENU, null);
			}
		});
		return cancelB;
	}
	
	class MenuLabel extends JLabel{
		final String ID = "ID: ";
		final String NOMBRE = "Nombre: ";
		final String PRECIO = "Precio: ";
		final String STOCK = "Stock: ";
		
		MenuLabel(){
			setText(ID + "; " + 
					NOMBRE + "; " + 
					PRECIO + "; " + 
					STOCK + "; ");
		}
		
		public void update(TMenu tMenu){
			setText(ID + tMenu.getId() + "; " +
					NOMBRE + tMenu.getNombre() + "; " +
					PRECIO + tMenu.getPrecio() + "; " + 
					STOCK + tMenu.getStock());
		}
	}
	
	class TablaProductos extends AbstractObjectInfo<TProducto>{
		final String[] FIELDS = {"id", "nombre"};
		
		public TablaProductos() {
			fields = FIELDS;
			data = new ArrayList<TProducto>();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getNombre();
			default:
				return null;
			}
		}
	}

	@Override
	public void update(Object datos) {
		TMenuProds tMenuProds = (TMenuProds) datos;
			menuInfo.update(tMenuProds.getMenu());
			prods.setData(tMenuProds.getProds());
	}
}
