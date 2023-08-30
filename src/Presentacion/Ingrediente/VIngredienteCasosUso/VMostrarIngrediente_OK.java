package Presentacion.Ingrediente.VIngredienteCasosUso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Negocio.Ingrediente.TIngrediente;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VMostrarIngrediente_OK extends JFrame implements IGUI{
	private Controller ctrl;
	private TablaIngredientes ingredientesTable;
	private JButton cancelButton;
	
	public VMostrarIngrediente_OK() {
		super("Mostrar un ingrediente");
		ctrl = Controller.obtenerInstancia();
		initGUI();
	}
	
	private void initGUI() {
		JPanel main = new JPanel();
		setContentPane(main);
		//CANCEL BUTTON
		cancelButton= new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						ctrl.accion(Events.ABRIR_VINGREDIENTE, null);
						
					}
				});
				main.add(cancelButton);
		ingredientesTable = new TablaIngredientes();
		main.add(ingredientesTable.toPanel("Ingrediente"));
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	class TablaIngredientes extends AbstractObjectInfo<TIngrediente>{
		final String[] FIELDS = {"idIngrediente", "alergeno", "nombre"};

		TablaIngredientes(){
			fields = FIELDS;
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getAlergeno();
			case 2:
				return data.get(rowIndex).getNombre();
			default:
				return null;
			}
		}
	}
	
	// ESTO ESTA MAL, A SIMPLE VISTA SE VE BIEN EN LA VISTA PERO NO TIENE SENTIDO USAR UNA TABLA DE INGREDIENTES PARA MOSTRAR SOLO 1.
	@Override
	public void update(Object data) {
		Collection<TIngrediente> ingrediente = new ArrayList<TIngrediente>();
		ingrediente.add((TIngrediente)data);
			ingredientesTable.setData(ingrediente);
	}

}
