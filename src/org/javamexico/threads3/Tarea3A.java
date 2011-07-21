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
package org.javamexico.threads3;

import org.javamexico.Owner;
import org.javamexico.Tarea;

/** Similar a la Tarea2 pero en vez de join, espera que la tarea anterior llegue al avance esperado
 * y duerme un segundo mientras eso no pase. La interrupcion de la tarea anterior hara que esta nunca se cumpla.
 * 
 *  @author Enrique Zamudio
 */
public class Tarea3A extends Tarea {

	private int empiezo;
	private Tarea otra;

	public Tarea3A(Owner creador, int indice, int limite, int start, Tarea prev) {
		super(creador, indice, limite);
		empiezo = start;
		otra = prev;
	}

	public void run() {
		while (avance < empiezo) {
			avance++;
			hazAlgo();
			Thread.yield();
		}
		if (otra != null) {
			while (otra.getAvance() < lim) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					System.out.printf("Tarea4A Me interrumpieron! %d%n", getIndice());
					ex.printStackTrace();
					return;
				}
			}
		}
		while (avance < lim) {
			avance++;
			hazAlgo();
			Thread.yield();
		}
	}

}
