/**
 * 
 */
package Presentacion.Command.Mesa;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Mesa.SAMesa;
import Negocio.Mesa.TMesa;
import Presentacion.Command.Command;
import Presentacion.Command.Context;
import Presentacion.Controller.Events;

public class EditarMesa2Command implements Command {
	
	@Override
	public Context executeCommand(Object data) {
		SAMesa msa = FactoriaSA.getInstance().generarSAMesa();
		
		int res = msa.editar((TMesa)data);
		
		Context resContext = null; 

        if(res == -1){
            resContext = new Context(Events.MODIFICAR_MESA_KO, res);
        }
        else {
            resContext = new Context(Events.MODIFICAR_MESA_OK, res);
        }

        return resContext;
	}
}