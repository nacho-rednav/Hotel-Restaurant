package Presentacion.Ingrediente.VIngredienteCasosUso;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Ingrediente.TIngrediente;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VMostrarTodosIngredientes extends JFrame implements IGUI{
	private Controller ctrl;
	private JButton cancelButton;
	
	public VMostrarTodosIngredientes(){
		super("Mostrar todos los ingredientes");
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
		
		TablaIngredientes ings = new TablaIngredientes(FactoriaSA.getInstance().generarSAIngrediente().mostrarTodos());
		main.add(ings.toPanel("Ingredientes"));
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		main.add(buttonsPanel);
		//CANCEL BUTTON
		cancelButton= new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VINGREDIENTE, null);
			}
		});
		buttonsPanel.add(cancelButton);
				
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	class TablaIngredientes extends AbstractObjectInfo<TIngrediente>{
		final String[] FIELDS = {"id", "nombre", "alergeno"};
		
		TablaIngredientes(Collection<TIngrediente> c) {
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
				return data.get(rowIndex).getAlergeno();
			default:
				return null;
			}
		}
	}

//	@Override
//	public void update(int event, Object datos) {
//		if(event == Events.MOSTRAR_INGREDIENTE_TODOS_KO)
//			JOptionPane.showMessageDialog(this, "Error al mostrar todos los ingredientes");
//		else if(event == Events.MOSTRAR_INGREDIENTE_TODOS_OK)
//			ingrTable.setData((Collection<TIngrediente>)datos);
//	}

	@Override
	public void update(Object data) {		
	}
}