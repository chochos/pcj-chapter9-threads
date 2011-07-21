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
package org.javamexico.threads2;

import org.javamexico.Owner;
import org.javamexico.Tarea;

/** Esta tarea puede avanzar un tramo, esperar a que termine otro thread, y luego terminar de avanzar.
 * La interrupcion de una tarea causa que la siguiente (que la esperaba) se siga despues de cachar la excepcion.
 * 
 * @author Enrique Zamudio
 */
public class Tarea2 extends Tarea {

	private int empiezo;
	private Thread otra;

	public Tarea2(Owner creador, int indice, int limite, int start, Thread prev) {
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
			long t0 = System.currentTimeMillis();
			try {
				otra.join();
			} catch (InterruptedException ex) {
				System.out.printf("Tarea2 Me interrumpieron! %d%n", getIndice());
				ex.printStackTrace();
				return;
			}
			long t1 = System.currentTimeMillis();
			System.out.printf("Thread %d espera %d milis%n", getIndice(), t1-t0);
		}
		while (avance < lim) {
			avance++;
			hazAlgo();
			Thread.yield();
		}
	}

}
