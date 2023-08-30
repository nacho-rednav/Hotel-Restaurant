package Presentacion.Turno;

import java.awt.event.ActionListener;
import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VEliminarTurno extends JFrame implements IGUI {
	private Controller ctrl;
	int _id;
	
	// Eliminar cuando hayamos acabado
	public static void main(String[] args) {
		new VEliminarTurno();
    }
	
	public VEliminarTurno() {
		super("Baja de Turno");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initIGUI();
			}
		});
	}
	
	private void initIGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		//Panel con el spinner del id
		JPanel infoPanel = new JPanel(new BorderLayout());
		mainPanel.add(infoPanel);
		
		JPanel idInfo =new JPanel();
		infoPanel.add(idInfo, BorderLayout.PAGE_START);
		
		idInfo.add(new JLabel("ID: "));
		idInfo.add(idSpinner());
		
		//Panel con botones de ok y cancel
		JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(panelButton);
		panelButton.add(okButton());
		panelButton.add(cancelButton());

		pack();		
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
	JButton okButton() {
		JButton okB = new JButton("OK");
		
		okB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.accion(Events.ELIMINAR_TURNO, _id);
				ctrl.accion(Events.ABRIR_VTURNO, null);
			}
		});
		
		return okB;
	}
	
	JButton cancelButton() {
		JButton cancelB = new JButton("Cancel");
		
		cancelB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VTURNO, null);
			}
		});
		
		return cancelB;
	}
	
	JSpinner idSpinner() {
		JSpinner capacitySpin = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
		capacitySpin.setPreferredSize(new Dimension(50, 20));
		_id = (Integer) capacitySpin.getValue();
		capacitySpin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				_id = (Integer) capacitySpin.getValue();
			}
		});
		return capacitySpin;
	}
	
	public void update(Object data) {
	}
}