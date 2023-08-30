package Presentacion.Factura.VFacturaCasosUsos;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Factura.TFactura;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEditarFactura2 extends JFrame implements IGUI {
	private JPanel panel;
	private JTextField id, id_mesa, id_empleado, importe, hora_cobro, activo;
	
	
	public VEditarFactura2() {
		super("Modificar factura");
		panel = new JPanel(new GridLayout(7, 1));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		id = new JTextField();
		id_mesa = new JTextField();
		id_empleado = new JTextField();
		importe = new JTextField();
		hora_cobro = new JTextField();
		activo = new JTextField();
		
	}
	
	
	public void update(Object data) {
		TFactura tf = (TFactura) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tf.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		// ID mesa
		JLabel lIdMesa = new JLabel("Id mesa: ");
		id_mesa.setText(Integer.toString(tf.getIdMesa()));
		id_mesa.setEditable(true);
		panel.add(lIdMesa);
		panel.add(id_mesa);
		// ID empleado
		JLabel lIdEmpleado = new JLabel("Id empleado: ");
		id_empleado.setText(Integer.toString(tf.getIdEmpleado()));
		id_empleado.setEditable(true);
		panel.add(lIdEmpleado);
		panel.add(id_empleado);
		// Importe
		JLabel lImp = new JLabel("Importe: ");
		importe.setText(Float.toString(tf.getImporte()));
		importe.setEditable(false);
		panel.add(lImp);
		panel.add(importe);
		// Hora cobro
		JLabel lHo = new JLabel("Hora cobro: ");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		if(tf.getHoraCobro() != null){
			hora_cobro.setText(formato.format(tf.getHoraCobro()));
		}
		else{
			hora_cobro.setText("Sin cerrar");
		}
		hora_cobro.setEditable(false);
		panel.add(lHo);
		panel.add(hora_cobro);
		//Disponible
		JLabel lAct = new JLabel("Abierta: ");
		activo.setText(Boolean.toString(tf.getActivo()));
		activo.setEditable(false);
		panel.add(lAct);
		panel.add(activo);
		//Boton OK
		JButton okB = new JButton("OK");
		panel.add(okB);
				
		
		okB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				int _id = tf.getId();
				int _id_mesa = Integer.parseInt(id_mesa.getText());
				int _id_empleado = Integer.parseInt(id_empleado.getText());
				Controller.obtenerInstancia().accion(Events.MODIFICAR_FACTURA2, new TFactura(_id, _id_mesa, _id_empleado, tf.getImporte(), tf.getHoraCobro(), tf.getActivo()));
				setVisible(false);
			}
			catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(panel, "Introduzca datos válidos");
				setVisible(true);
			}
		}
	});
				
				
		//Boton Cancelar
		JButton cancelB = new JButton("Cancel");
		panel.add(cancelB);
				
		cancelB.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_FACTURA, null);
			}
		});
		this.pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}
