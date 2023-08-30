package Presentacion.Empleado.VCasosDeUso;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import Presentacion.Empleado.VEmpleado;

@SuppressWarnings("serial")
public class VMostrarEmpleado extends JFrame implements IGUI{
	private Controller ctrl;
	private Integer id;
//	private EmpleadoLabel empInfo;
	
	public VMostrarEmpleado() {
		super("Mostrar Un Empleado");
		ctrl = Controller.obtenerInstancia();
		initGUI();
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				
//			}
//		});
	}
	
	public void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,  BoxLayout.PAGE_AXIS));
		setContentPane(mainPanel);
		
		JPanel takingInfoPanel = new JPanel();
		mainPanel.add(takingInfoPanel);
		
		takingInfoPanel.add(new JLabel("ID Empleado: "));
		takingInfoPanel.add(idSpinner());
		
//		empInfo = new EmpleadoLabel();
//		JPanel showingInfoPanel = new JPanel();
//		mainPanel.add(showingInfoPanel);
//		showingInfoPanel.add(empInfo);
		
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(buttonsPanel);
		buttonsPanel.add(okButton());
		buttonsPanel.add(cancelButton());

		pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	JSpinner idSpinner() {
		JSpinner idSpin =new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		idSpin.setPreferredSize(new Dimension(50, 20));
		id = (Integer) idSpin.getValue();
		idSpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				id = (Integer) idSpin.getValue();
			}
		});
		return idSpin;
	}
	
	private JButton okButton(){
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.MOSTRAR_UN_EMPLEADO, id);
				setVisible(false);
				ctrl.accion(Events.ABRIR_VMOSTRAR_UN_EMPLEADO, id);
				
			}
		});
		
		return okB;
	}
	
	private JButton cancelButton(){
		JButton cancelB = new JButton("Cancel");
		
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VEMPLEADO, null);
			}
		});
		
		return cancelB;
	}
	
//	class EmpleadoLabel extends JLabel{
//		final String ID = "ID: ";
//		final String DNI = "DNI: ";
//		final String NOMBRE = "Nombre: ";
//		final String SUELDO = "Sueldo: ";
//		final String HORARIO = "Horario: ";
//		final String IDIOMA = "Idioma: ";
//		final String TITULO = "Titulo: ";
//		
//		EmpleadoLabel(){
////			setText(ID + "; " +
////					DNI +"; " + 
////					NOMBRE +"; " +
////					SUELDO +"; " +
////					HORARIO +"; ");
//		}
//		
//		public void update(TEmpleado tEmp){
//			boolean esCamarero = tEmp.getIdioma() != null;
//			String newTitle = "Mostrar un " + (esCamarero ? "Camarero" : "Cocinero");
//			VMostrarEmpleado.this.setTitle(newTitle);
//			setText(ID + tEmp.getId() + "; " +
//					DNI + tEmp.getDNI() + "; " + 
//					NOMBRE + tEmp.getNombre() + "; " + 
//					SUELDO + tEmp.getSueldo() + "; " + 
//					HORARIO + tEmp.getHorario() + "; " + 
//					
//					(esCamarero ? 
//					IDIOMA + " " + tEmp.getIdioma() : 
//					TITULO + " " + tEmp.getTitulo()));
//		}
//	}

	@Override
	public void update(Object data) {
//			empInfo.update((TEmpleado) data);
	}
}
