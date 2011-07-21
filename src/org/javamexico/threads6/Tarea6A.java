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
package org.javamexico.threads6;

import org.javamexico.Owner;
import org.javamexico.Tarea;

/** Estas tareas van a sincronizar todas sobre el controlador, y la ultima de ellas les avisara
 * cada que avanza para que todas avancen.
 * 
 * @author Enrique Zamudio
 */
public class Tarea6A extends Tarea {

	public Tarea6A(Owner creador, int indice, int limite) {
		super(creador, indice, limite);
	}

	public void run() {
		while (avance < lim) {
			synchronized(owner) {
				if (getIndice() == 9) {
					owner.notify();
				} else {
					try {
						owner.wait();
					} catch (InterruptedException ex) {
						System.out.printf("Me interrumpieron esperando! %d%n", getIndice());
						ex.printStackTrace();
						return;
					}
				}
				avance++;
				hazAlgo();
			}
		}
	}

}
