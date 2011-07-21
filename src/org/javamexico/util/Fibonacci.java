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

/** Una tarea que busca un numero de Fibonacci con la rutina definida en Utils.
 * 
 * @author Enrique Zamudio
 */
public class Fibonacci implements Runnable {

	private long lim;
	private long queue;

	public Fibonacci(long limite) {
		lim = limite;
		queue = System.currentTimeMillis();
	}

	public void run() {
		long start = System.currentTimeMillis();
		long fibo = Utils.fiboCercano(lim);
		long end = System.currentTimeMillis();
		System.out.printf("[%s] FIBO mas cercano a %10d es %10d - encolado %5d milis, corre en %5d milis%n",
				Thread.currentThread().getName(), lim, fibo, start-queue, end-start);
	}

}