package Presentacion.Command;

import Presentacion.Controller.Events;

public class Context {

	private int evento;

	private Object datos;

	public Object getData() {
		return datos;
	}

	public int getEvento() {
		return evento;
	}

	public Context(Events event, Object data) {

	}

	public Context(int event, Object data) {
		this.evento = event;
		this.datos = data;
	}
}