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
package org.javamexico.threads5;

import java.util.Random;

import org.javamexico.util.Cajero;
import org.javamexico.util.Cliente;

/** Esta clase abstract define el codigo minimo necesario para el ejemplo,
 * el que no va a variar entre distintas versiones.
 * 
 * @author Enrique Zamudio
 */
public abstract class Procesador {

	public static final Random prng = new Random(System.currentTimeMillis());

	/** Las subclases deben implementar este metodo, transfiriendo el monto
	 * indicado del cliente al cajero. */
	public abstract void deposita(Cliente cliente, int monto, Cajero cajero);

	/** Las subclases deben implementar este metodo, transfiriendo el monto
	 * indicado del cajero al cliente. */
	public abstract void retira(Cliente cliente, int monto, Cajero cajero);

	/** Este metodo crea y devuelve un Runnable que va a retirar una cantidad aleatoria
	 * (entre 1 y 10) en un ciclo infinito. */
	public Runnable creaGastador(final Cliente cliente, final Cajero cajero) {
		return new Runnable(){
			public void run() {
				while (true) {
					System.out.printf("[%s] Retirando%n", Thread.currentThread().getName());
					retira(cliente, prng.nextInt(10) + 1, cajero);
				}
			}
		};
	}

	/** Este metodo crea y devuelve un Runnable que hace depositos aleatorios
	 * (entre 1 y 10) en un ciclo infinito. */
	public Runnable creaDepositador(final Cliente cliente, final Cajero cajero) {
		return new Runnable(){
			public void run() {
				while (true) {
					System.out.printf("[%s] Depositando%n", Thread.currentThread().getName());
					deposita(cliente, prng.nextInt(10) + 1, cajero);
				}
			}
		};
	}

}
