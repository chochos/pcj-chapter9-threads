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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.javamexico.util.Cajero;
import org.javamexico.util.Cliente;

/** Este programa corre dos hilos que terminan eventualmente en deadlock.
 * Es un programa de consola, no usa la GUI de barras de los otros ejercicios.
 * 
 * @author Enrique Zamudio
 */
public class Ejemplo5 {

	public static void main(String[] args) {
		//Creamos los objetos que necesitamos
		final Cajero cajero = new Cajero();
		final Cliente cliente = new Cliente();
		final Procesador proc = new ProcImpl();
		cajero.incrementa(1000000);
		cliente.deposita(100000);

		//Creamos un proceso para retiros y otro para depositos
		Runnable gastador = proc.creaGastador(cliente, cajero);
		Runnable ahorrador = proc.creaDepositador(cliente, cajero);
		Thread t1 = new Thread(gastador, "gasta");
		Thread t2 = new Thread(ahorrador, "ahorra");

		//Y un timer para imprimir periodicamente el saldo.
		ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
		timer.scheduleWithFixedDelay(new Runnable(){
			public void run() {
				System.out.printf("***************** Saldo del cliente: %06d%n", cliente.getSaldo());
			}
		}, 1, 5, TimeUnit.SECONDS);
		t1.start();
		t2.start();
	}

}
