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
package org.javamexico.bt2;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/** Ejemplo de un consumidor, usando colas de java.util.concurrent
 * 
 * @author Enrique Zamudio
 */
public class ConsumidorQueue implements Runnable {

	private LinkedBlockingQueue<Integer> cola;

	public ConsumidorQueue(LinkedBlockingQueue<Integer> queue) {
		cola = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Integer x = cola.take();
				System.out.printf("CONS tiempo para calcular fue %1$5d %2$TT.%2$TL%n", x, new Date());
			} catch (InterruptedException e) {
				System.out.println("Interrumpido esperando de la cola");
			}
		}
	}

}
