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

/** Esta clase encapsula algunas rutinas de calculo que se usan en los
 * diversos ejemplos.
 * 
 * @author Enrique Zamudio
 */
public class Utils {

	public static boolean esFibo(long value) {
		long fibo = 1, fibo2 = 1;
		long i = 1;
		for (; i <= value; i = fibo + fibo2) {
			fibo2 = fibo;
			fibo = i;
			if (value == i) {
				return true;
			}
		}
		return i == value;
	}

	/** Devuelve el numero Fibonacci siguiente mas cercano al indicado. */
	public static long fiboCercano(long numero) {
		long fibo = 0;
		long prev1 = 1;
		long prev2 = 1;
		while (fibo < numero) {
			fibo = prev1 + prev2;
			prev1 = prev2;
			prev2 = fibo;
		}
		return fibo;
	}

	/** Calcula PI por aproximaciones sucesivas, empezando en 1/3
	 * e incrementando hasta llegar al denominador especificado. */
	public static double aproximaPi(long denominador) {
		double denom = 3.0;
		double decs = 1.0;
		boolean signo = false;
		while (denom < denominador) {
			if (signo) {
				decs += 1 / denom;
			} else {
				decs -= 1 / denom;
			}
			signo = !signo;
			denom += 2.0;
		}
		return 4*decs;
	}

}
