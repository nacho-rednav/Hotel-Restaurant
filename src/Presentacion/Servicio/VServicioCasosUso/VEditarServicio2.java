package Presentacion.Servicio.VServicioCasosUso;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Servicio.TServicio;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;

public class VEditarServicio2 extends JFrame implements IGUI {
	private JPanel panel;
	private JTextField id, tipo, descripcion, activo, precio;

	public VEditarServicio2() {
		super("Editar Servicio");
		panel = new JPanel(new GridLayout(6, 2));
		this.setContentPane(panel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		id = new JTextField();
		tipo = new JTextField();
		descripcion = new JTextField();
		precio = new JTextField();
		activo = new JTextField();

	}

	public void update(Object data) {
		TServicio tRec = (TServicio) data;
		// ID
		JLabel lId = new JLabel("Id: ");
		id.setText(Integer.toString(tRec.getId()));
		id.setEditable(false);
		panel.add(lId);
		panel.add(id);
		// Tipo
		JLabel lTipo = new JLabel("Tipo: ");
		tipo.setText(tRec.getTipo());
		tipo.setEditable(true);
		panel.add(lTipo);
		panel.add(tipo);
		// Descripcion
		JLabel lDescripcion = new JLabel("Descripcion: ");
		descripcion.setText((tRec.getDescripcion()));
		descripcion.setEditable(true);
		panel.add(lDescripcion);
		panel.add(descripcion);
		// Precio
		JLabel lnprecio = new JLabel("Precio: ");
		precio.setText(Float.toString(tRec.getPrecio()));
		precio.setEditable(true);
		panel.add(lnprecio);
		panel.add(precio);
		// Disponible
		JLabel lAct = new JLabel("Activo: ");
		activo.setText(Boolean.toString(tRec.getActivo()));
		activo.setEditable(false);
		panel.add(lAct);
		panel.add(activo);
		// Boton OK
		JButton okB = new JButton("OK");
		panel.add(okB);

		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int _id = tRec.getId();
					String _tipo = tipo.getText();
					float _precio = Float.parseFloat(precio.getText());
					String _desc = descripcion.getText();
					Controller.obtenerInstancia().accion(Events.MODIFICAR_SERVICIO2,
							new TServicio(_id, _tipo, _desc, _precio, true));
					setVisible(false);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(panel, "Introduzca datos válidos");
					setVisible(true);
				}
			}
		});

		// Boton Cancelar
		JButton cancelB = new JButton("Cancel");
		panel.add(cancelB);

		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Controller.obtenerInstancia().accion(Events.ABRIR_VMODIFICAR_SERVICIO, null);
			}
		});
		this.pack();
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}
