/**
 * 
 */
package Integracion.FacturaMenu;

import java.util.List;

import Negocio.Factura.TFactura;
import Negocio.Factura.TLineaDePedido;

public interface DAOFacturaMenu {

	public List<TLineaDePedido> leerMenusEnFactura(Integer idFactura);

	public List<TFactura> leerFacturasPorMenu(Integer idMenu);

	public TLineaDePedido leerUno(TLineaDePedido info);

	public void vincular(TLineaDePedido info);

	public void editarCantidadMenu(TLineaDePedido info);

	public void desvincular(TLineaDePedido info);

}