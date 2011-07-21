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

/** Similar a la Tarea5A excepto que las tareas que esperan, lo hacen por maximo medio segundo.
 * 
 * @author Enrique Zamudio
 */
public class Tarea6B extends Tarea {

	public Tarea6B(Owner creador, int indice, int limite) {
		super(creador, indice, limite);
	}

	public void run() {
		while (avance < lim) {
			synchronized(owner) {
				if (getIndice() == 9) {
					avance++;
					owner.notify();
				} else {
					avance += 10;
					long t0 = System.currentTimeMillis();
					try {
						owner.wait(500);
						long t1 = System.currentTimeMillis();
						if (t1 - t0 < 500) {
							System.out.printf("Parece que me avisaron a mi!!! %d%n", getIndice());
						}
					} catch (InterruptedException ex) {
						System.out.printf("Me interrumpieron esperando! %d%n", getIndice());
						ex.printStackTrace();
						return;
					}
				}
				hazAlgo();
			}
		}
	}

}
