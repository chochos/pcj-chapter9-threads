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
package org.javamexico.util;

/** Calcula PI por aproximaciones sucesivas usando la rutina de Utils.
 * 
 * @author Enrique Zamudio
 */
public class Pi implements Runnable {

	private long lim;
	private long creado;

	public Pi(long limite) {
		lim = limite;
		creado = System.currentTimeMillis();
	}

	public void run() {
		long start = System.currentTimeMillis();
		double decs = Utils.aproximaPi(lim);
		long end = System.currentTimeMillis();
		System.out.printf("[%s] PI lim %d llegamos a %.15f - encolado %6d millis corre por %6d millis%n",
				Thread.currentThread().getName(), lim, decs, start-creado, end-start);
	}

}
