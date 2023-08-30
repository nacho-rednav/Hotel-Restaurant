package Presentacion.Producto.VProductoCasosDeUso;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Producto.TProducto;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Producto.VProducto;

@SuppressWarnings("serial")
public class VMostrarTodosProductos extends JFrame implements IGUI {
	private Controller ctrl;
	private JButton cancelButton;
	
	public VMostrarTodosProductos() {
		super("Mostrar todos los productos");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	
	private void initGUI(){
		JPanel main = new JPanel();
		setContentPane(main);
		
		TablaProductos prodtos = new TablaProductos(FactoriaSA.getInstance().generarSAProducto().mostrarTodos());
		main.add(prodtos.toPanel("Productos"));
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		main.add(buttonsPanel);
		
		//CANCEL BUTTON
		cancelButton= new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					ctrl.accion(Events.ABRIR_VPRODUCTO, null);
				}
		});
		main.add(cancelButton);

		pack();		
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	class TablaProductos extends AbstractObjectInfo<TProducto> {
		final String[] FIELDS = {"id", "nombre"};

		TablaProductos(Collection<TProducto> c) {
			fields = FIELDS;
			setData(c);
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
	public void update(Object data) {
	}
}
