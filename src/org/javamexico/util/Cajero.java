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
package org.javamexico.util;

/** Esta clase se usa en el ejemplo de deadlocks y Thread Pools.
 * Simplemente lleva un saldo y tiene metodos para modificarlo.
 * 
 * @author Enrique Zamudio
 */
public class Cajero {

	private int saldo;

	public void incrementa(int monto) {
		saldo += monto;
	}

	public void decrementa(int monto) {
		saldo -= monto;
	}

}
