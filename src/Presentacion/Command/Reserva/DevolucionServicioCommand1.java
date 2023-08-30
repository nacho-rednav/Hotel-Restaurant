package Presentacion.Command.Reserva;

import java.util.Date;
import java.util.ArrayList;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Reserva.Pair;
import Negocio.Reserva.SAReserva;
import Negocio.Reserva.TLineaPedidoServicios;
import Negocio.Reserva.TReservaCompleta;
import Negocio.ReservaServicio.TCompraServicio;
import Negocio.Servicio.SAServicio;
import Negocio.Servicio.TServicio;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;


public class DevolucionServicioCommand1 implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA fsa = FactoriaSA.getInstance();
		SAReserva sar = fsa.generarSAReserva();
		SAServicio sas = fsa.generarSAServicio();
		TReservaCompleta res = sar.mostrarReservaCompleta((Integer)data);
		
		Context resContext = null;
		if(res == null){
			resContext = new Context(Events.DEVOLUCION_SERVICIO_RESERVA_KO, "Error:No existe esta reserva");
		}
		else{
			ArrayList<Pair<TServicio, Date>> servicios = new ArrayList<Pair<TServicio, Date>>();
			for(TCompraServicio ser : res.getServicios()){
				TServicio s = sas.mostrarPorId(ser.getId_Servicio());
				if(s.getActivo())
					servicios.add(new Pair<TServicio, Date> (s, ser.getFecha()));
			}
			TLineaPedidoServicios reserva = new TLineaPedidoServicios((Integer) data, servicios);
			resContext = new Context(Events.ABRIR_VDEVOLUCION_SERVICIO_RESERVA2, reserva);
		}
		return resContext;
	}
}