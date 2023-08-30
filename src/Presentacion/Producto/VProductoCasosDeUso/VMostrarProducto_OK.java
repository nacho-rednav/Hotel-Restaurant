package Presentacion.Producto.VProductoCasosDeUso;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Negocio.Ingrediente.TIngrediente;
import Negocio.Menu.TMenu;
import Negocio.Producto.TProdIngrs;
import Negocio.Producto.TProducto;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Menu.VMenuCasosDeUso.VMostrarMenu_OK;

@SuppressWarnings("serial")
public class VMostrarProducto_OK extends JFrame implements IGUI {
	private Controller ctrl;
	private Integer id;
	private ProductoLabel productoInfo;
	private TablaIngredientes ingrs;
	private JButton cancelButton;
	
	public static void main(String[] args) {
		new VMostrarProducto_OK();
	}
	
	public VMostrarProducto_OK() {
		super("Mostrar un producto");
		ctrl = Controller.obtenerInstancia();
		initGUI();
	}
	private void initGUI() {
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.PAGE_AXIS));
		setContentPane(main);
		
		JPanel takingInfoPanel = new JPanel();
		main.add(takingInfoPanel);
		
		productoInfo = new ProductoLabel();
		JPanel showingInfoPanel = new JPanel();
		main.add(showingInfoPanel);
		showingInfoPanel.add(productoInfo);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		main.add(buttonPanel);
		buttonPanel.add(cancelButton());
		
		ingrs = new TablaIngredientes();
		main.add(ingrs.toPanel("Ingredientes"));
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	private JButton cancelButton() {
		JButton cancelB = new JButton("Cancel");
		
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VPRODUCTO, null);
			}
		});
		return cancelB;
	}
	
	class ProductoLabel extends JLabel {
		final String ID = "ID: ";
		final String NOMBRE = "Nombre: ";
		
		ProductoLabel() {
			setText(ID + "; " + 
					NOMBRE + "; ");
		}
		
		public void update(TProducto tProducto){
			setText(ID + tProducto.getId() + "; " +
					NOMBRE + tProducto.getNombre());
		}
	}
	
	class TablaIngredientes extends AbstractObjectInfo<TIngrediente> {
		final String[] FIELDS = {"idIngrediente", "nombre", "alergeno"};

		public TablaIngredientes() {
			fields = FIELDS;
			data = new ArrayList<TIngrediente>();
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex) {
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getNombre();
			case 2:
				return data.get(rowIndex).getAlergeno();
			default:
				return null;
			}
		}
	}
	
	@Override
	public void update(Object data) {
		TProdIngrs tProdIngrs = (TProdIngrs) data;
		productoInfo.update(tProdIngrs.getProducto());
		ingrs.setData(tProdIngrs.getIngrs());
	}

}
