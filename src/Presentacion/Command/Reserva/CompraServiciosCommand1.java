package Presentacion.Command.Reserva;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.Pair;
import Negocio.Reserva.SAReserva;
import Negocio.Reserva.TLineaPedidoServicios;
import Negocio.Reserva.TReserva;
import Negocio.Servicio.SAServicio;
import Negocio.Servicio.TServicio;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class CompraServiciosCommand1 implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAReserva sar = fsa.generarSAReserva();
		SAServicio sas = fsa.generarSAServicio();
		TReserva res = sar.mostrarReserva((Integer)data);
		
		Context resContext = null;
		if(res == null){
			resContext = new Context(Events.COMPRA_SERVICIO_RESERVA_KO, "Error:No existe esta reserva");
		}
		else{
			Collection<TServicio> serviciosAll = sas.mostrarTodos();
			ArrayList<Pair<TServicio, Date>> servicios = new ArrayList<Pair<TServicio, Date>>();
			for(TServicio ser : serviciosAll){
				if(ser.getActivo())
					servicios.add(new Pair<TServicio, Date> (ser, null));
			}
			TLineaPedidoServicios reserva = new TLineaPedidoServicios((Integer) data, servicios);
			resContext = new Context(Events.ABRIR_VCOMPRA_SERVICIO2, reserva);
		}
		return resContext;
	}
}