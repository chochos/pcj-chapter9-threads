/*
Este archivo es parte de PCJ (Proyecto Certificate en Java).

PCJ es software libre: lo puedes redistribuir y/o modificar
bajo los terminos de la GNU General Public License como fue
publicada por la Free Software Foundation, ya sea la version
3 o (a tu eleccion) una version mas reciente.

Este codigo se distribuye con la esperanza de que sea util,
pero SIN NINGUNA GARANTIA; ni siquiera bajo la garantia de
que sea comercializable o apto para un proposito en
particular. Para mas detalles, ver la GPL en el sitio

http://www.gnu.org/licenses

(C)Enrique Zamudio Lopez 2010
*/
package org.javamexico.threads5;

import org.javamexico.util.Cajero;
import org.javamexico.util.Cliente;

/** Esta es la version corregida del Ejemplo4 para evitar el deadlock.
 * 
 * @author Enrique Zamudio
 */
public class ProcFix extends Procesador {

	public void deposita(Cliente cliente, int monto, Cajero cajero) {
		synchronized (cliente) {
			cliente.deposita(monto);
			synchronized (cajero) {
				cajero.decrementa(monto);
				try {
					//Simulamos que se tarda haciendo algo importante
					Thread.sleep(50);
				} catch (InterruptedException ex) {
				}
			}
		}
	}

	public void retira(Cliente cliente, int monto, Cajero cajero) {
		synchronized (cliente) {
			cliente.retira(monto);
			synchronized (cajero) {
				cajero.incrementa(monto);
				//Simulamos que se tarda haciendo algo importante
				try {
					Thread.sleep(50);
				} catch (InterruptedException ex) {
				}
			}
		}
	}

}
