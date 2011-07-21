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
package org.javamexico.threads4;

import org.javamexico.Owner;
import org.javamexico.Tarea;

/** Esta tarea simplemente avanza pero sincroniza sobre el objeto que la crea.
 * 
 * @author Enrique Zamudio
 */
public class Tarea4A extends Tarea {

	public Tarea4A(Owner creador, int indice, int limite) {
		super(creador, indice, limite);
	}

	public void run() {
		while (avance < lim) {
			//Con esto aseguramos que solamente un Thread corre el codigo a la vez
			synchronized(owner) {
				avance++;
				hazAlgo();
				hazAlgo();
				hazAlgo();
				hazAlgo();
			}
		}
	}

}
