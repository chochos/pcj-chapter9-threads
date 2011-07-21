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

/** Esta clase se usa en los ejemplos de deadlock y thread pools.
 * Maneja un saldo, que se puede modificar, y puede devolverlo.
 * 
 * @author Enrique Zamudio
 */
public class Cliente {

	private int saldo;

	public void deposita(int value) {
		saldo += value;
	}

	public void retira(int monto) {
		saldo -= monto;
	}

	public int getSaldo() {
		return saldo;
	}

}
