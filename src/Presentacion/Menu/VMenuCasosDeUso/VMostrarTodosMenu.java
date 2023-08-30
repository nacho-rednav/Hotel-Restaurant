package Presentacion.Menu.VMenuCasosDeUso;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Menu.TMenu;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Menu.VMenu;

@SuppressWarnings("serial")
public class VMostrarTodosMenu extends JFrame implements IGUI{
	private TablaMenus menus;
	private Controller ctrl;
	private JButton cancelButton;	
	
	public VMostrarTodosMenu(){
		super("Mostrar todos los menus");
		ctrl = Controller.obtenerInstancia();
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//			}
//		});
		initGUI();
	}
	
	private void initGUI(){
		JPanel main = new JPanel();
		setContentPane(main);
		//CANCEL BUTTON
		cancelButton= new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VMENU, null);
			}
		});
		main.add(cancelButton);
		menus = new TablaMenus(FactoriaSA.getInstance().generarSAMenu().mostrarTodos());
		main.add(menus.toPanel("Menus"));

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	class TablaMenus extends AbstractObjectInfo<TMenu>{
		final String[] FIELDS = {"id", "nombre", "precio", "stock"};

		TablaMenus(Collection<TMenu> c){
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
			case 2:
				return data.get(rowIndex).getPrecio();
			case 3:
				return data.get(rowIndex).getStock();
			default:
				return null;
			}
		}
	}

	@Override
	public void update(Object datos) {
			menus.setData((Collection<TMenu>)datos);
	}
}
