package Presentacion.Empleado.VCasosDeUso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import Negocio.Empleado.TEmpleado;
import Presentacion.AbstractObjectInfo;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class VMostrarTodosEmpleados extends JFrame implements IGUI{
	private Controller ctrl;
	private TablaEmpleados empleadosTable;
	private JButton cancelButton;
	
	public VMostrarTodosEmpleados(){
		super("Mostrar todos los empleados");
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
						ctrl.accion(Events.ABRIR_VEMPLEADO, null);
						
					}
				});
				main.add(cancelButton);
		empleadosTable = new TablaEmpleados();
		main.add(empleadosTable.toPanel("Empleados"));

//		Controller.obtenerInstancia().accion(Events.MOSTRAR_EMPLEADO_TODOS, null);
		
		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	class TablaEmpleados extends AbstractObjectInfo<TEmpleado>{
		final String[] FIELDS = {"id", "nombre", "sueldo", "dni","horario", "idioma","titulo"};

		TablaEmpleados(){
			fields = FIELDS;
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			switch(columnIndex){
			case 0:
				return data.get(rowIndex).getId();
			case 1:
				return data.get(rowIndex).getNombre();
			case 2:
				return data.get(rowIndex).getSueldo();
			case 3:
				return data.get(rowIndex).getDNI();
			case 4:
				return data.get(rowIndex).getHorario();
			case 5:
				return data.get(rowIndex).getIdioma();
			case 6:
				return data.get(rowIndex).getTitulo();
			default:
				return null;
			}
		}
	}


	@Override
	public void update(Object data) {
			empleadosTable.setData((Collection<TEmpleado>)data);
	}
}