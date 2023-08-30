package Presentacion.Command.Mesa;


import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Mesa.SAMesa;
import Negocio.Mesa.TMesa;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarMesa1Command implements Command {

	@Override
	public Context executeCommand(Object data) {
		FactoriaSA factoria = FactoriaSA.getInstance();
        SAMesa saMesa = factoria.generarSAMesa();
        TMesa res = saMesa.mostrarUno((Integer)data);

        Context resContext = null; //Si llega null algo va mal

        if(res == null){
            resContext = new Context(Events.MODIFICAR_MESA_KO, null);
        }
        else {
            resContext = new Context(Events.ABRIR_VMODIFICAR_MESA2, res);
        }

        return resContext;
	}
}