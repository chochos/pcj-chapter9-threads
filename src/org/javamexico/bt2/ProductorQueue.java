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

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

import org.javamexico.util.Utils;

/** Ejemplo de un proceso productor, que agrega sus resultados a una cola de java.util.concurrent
 * 
 * @author Enrique Zamudio
 */
public class ProductorQueue implements Runnable {

	private LinkedBlockingQueue<Integer> cola;

	public ProductorQueue(LinkedBlockingQueue<Integer> queue) {
		cola = queue;
	}

	public void run() {
		Random rng = new Random(System.currentTimeMillis());
		while (true) {
			int x = rng.nextInt(10000);
			double d = Utils.aproximaPi(10000000 * rng.nextInt(50));
			System.out.printf("PROD Calculamos PI %.12f%n", d);
			//Aqui escribimos a la cola
			cola.add(x);
		}
	}

}
