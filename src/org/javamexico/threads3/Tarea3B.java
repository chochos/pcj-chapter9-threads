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

/** Similar a Tarea4A pero maneja un timeout, despues del cual ya se sigue sin esperar
 * 
 * @author Enrique Zamudio
 */
public class Tarea3B extends Tarea {

	private int empiezo;
	private Tarea otra;

	public Tarea3B(Owner creador, int indice, int limite, int start, Tarea prev) {
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
			//Aqui verificamos el tiempo transcurrido
			//Manejamos un mecanismo de timeout
			long now = System.currentTimeMillis();
			int timeout = 1000 * (getIndice() + 1);
			while (otra.getAvance() < lim && System.currentTimeMillis() - now < timeout) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ex) {
					System.out.printf("Me interrumpieron! %d%n", getIndice());
					ex.printStackTrace();
					return;
				}
			}
			if (otra.getAvance() < lim) {
				System.out.printf("Timeout esperando a tarea %d%n", otra.getIndice());
			}
		}
		while (avance < lim) {
			avance++;
			hazAlgo();
			Thread.yield();
		}
	}

}
