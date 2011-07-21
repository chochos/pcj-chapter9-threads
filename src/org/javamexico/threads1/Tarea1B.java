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
package org.javamexico.threads1;

import org.javamexico.Owner;
import org.javamexico.Tarea;

/** Similar a Tarea1A pero hace yield() y revisa si ha sido interrumpida.
 * 
 * @author Enrique Zamudio
 */
public class Tarea1B extends Tarea {

	public Tarea1B(Owner creador, int indice, int limite) {
		super(creador, indice, limite);
	}

	public void run() {
		//Ademas de yield, vamos a detectar si el thread fue interrumpido
		while (avance < lim && !Thread.currentThread().isInterrupted()) {
			avance++;
			hazAlgo();
			Thread.yield();
		}
	}

}
