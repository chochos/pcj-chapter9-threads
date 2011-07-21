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

/** Esta tarea simplemente avanza y luego se distrae con otra accion.
 * 
 * @author Enrique Zamudio
 */
public class Tarea1A extends Tarea {

	public Tarea1A(Owner creador, int indice, int limite) {
		super(creador, indice, limite);
	}

	public void run() {
		while (avance < lim) {
			avance++;
			hazAlgo();
		}
	}

}
