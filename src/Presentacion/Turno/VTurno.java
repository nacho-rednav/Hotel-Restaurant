package Presentacion.Turno;

import javax.swing.JFrame;

import Presentacion.Controller.Controller;
import Presentacion.Controller.Events;
import Presentacion.Controller.IGUI;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VTurno extends JFrame implements IGUI {
	private static final long serialVersionUID = 1L;
	
	private final Dimension ButtDim = new Dimension(150, 50);
	private final Dimension FrameDim = new Dimension(600, 230);
	private JButton returnButton;
	private Controller ctrl;
	
	public VTurno() {
		super("Vista Turno");
		ctrl = Controller.obtenerInstancia();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initGUI();
			}
		});
	}
	public void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setPreferredSize(FrameDim);
		setContentPane(mainPanel);

		JPanel botonesPanel = new JPanel();
		mainPanel.add(botonesPanel);
		// RETURN BUTTON
		returnButton = new JButton();
		ImageIcon icon = new ImageIcon("icons/back.png");
		Image newImg = icon.getImage().getScaledInstance(55, 50, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImg);
		returnButton.setIcon(icon);
		returnButton.setPreferredSize(new Dimension(55, 50));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ctrl.accion(Events.ABRIR_VHOTEL, null);
			}
		});
		botonesPanel.add(returnButton);
		botonesPanel.add(crearButton());
		botonesPanel.add(editarButton());
		botonesPanel.add(eliminarButton());
		botonesPanel.add(mostUnoButton());
		botonesPanel.add(mostTodosButton());
		botonesPanel.add(mostTodosPorClienteButton());
		botonesPanel.add(AnyadirClienteTurnoButton());
		botonesPanel.add(EliminarClienteTurnoButton());
		
		pack();
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	// CREAR BUTTON
		JButton crearButton() {
			JButton crearB = new JButton("CREAR");
			crearB.setPreferredSize(ButtDim);
			crearB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.accion(Events.ABRIR_VALTA_TURNO, null);
					setVisible(false);
				}
			});
			return crearB;
		}

		// ELIMINAR BUTTON
		JButton eliminarButton() {
			JButton eliminarB = new JButton("ELIMINAR");
			eliminarB.setPreferredSize(ButtDim);
			eliminarB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.accion(Events.ABRIR_VBAJA_TURNO, null);
					setVisible(false);
				}
			});
			return eliminarB;
		}

		// EDITAR BUTTON
		JButton editarButton() {
			JButton editarB = new JButton("EDITAR");
			editarB.setPreferredSize(ButtDim);
			editarB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.accion(Events.ABRIR_VMODIFICAR_TURNO, null);
					setVisible(false);
				}
			});
			return editarB;
		}

		// MOSTRAR UNO BUTTON
		JButton mostUnoButton() {
			JButton mostUnoB = new JButton("MOSTRAR UNO");
			mostUnoB.setPreferredSize(ButtDim);
			mostUnoB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.accion(Events.ABRIR_VMOSTRAR_TURNO_ID, null);
					setVisible(false);
				}
			});
			return mostUnoB;
		}

		// MOSTRAR TODOS BUTTON
		JButton mostTodosButton() {
			JButton mostTodosB = new JButton("MOSTRAR TODOS");
			mostTodosB.setPreferredSize(ButtDim);
			mostTodosB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.accion(Events.MOSTRAR_TODOS_TURNO, null);
					setVisible(false);
				}
			});
			return mostTodosB;
		}
		JButton mostTodosPorClienteButton() {
			JButton mostTodosPorClienteB = new JButton("MOSTRAR CLIENTES DE TURNO");
			mostTodosPorClienteB.setPreferredSize(new Dimension(220, 50));
			mostTodosPorClienteB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.accion(Events.ABRIR_VMOSTRAR_TURNO_CLIENTES, null);
					setVisible(false);
				}
			});
			return mostTodosPorClienteB;
		}
		
		//VINCULAR CLIENTE TURNO
		JButton AnyadirClienteTurnoButton() {
			JButton anyadirClienteTurnoB = new JButton("ANYADIR CLIENTE TURNO");
			anyadirClienteTurnoB.setPreferredSize(new Dimension(200, 50));
			anyadirClienteTurnoB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.accion(Events.ABRIR_VANYADIR_CLIENTE_TURNO_, null);
					setVisible(false);
				}
			});
			return anyadirClienteTurnoB;
		}
		
		//DESVINCULAR CLIENTE TURNO
		JButton EliminarClienteTurnoButton() {
			JButton eliminarClienteTurnoB = new JButton("ELIMINAR CLIENTE TURNO");
			eliminarClienteTurnoB.setPreferredSize(new Dimension(200, 50));
			eliminarClienteTurnoB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ctrl.accion(Events.ABRIR_VELIMINAR_CLIENTE_TURNO_, null);
					setVisible(false);
				}
			});
			return eliminarClienteTurnoB;
		}

	public void update(Object data) {
	}
}