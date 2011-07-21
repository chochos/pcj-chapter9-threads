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
package org.javamexico.bt1;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.javamexico.threads5.ProcFix;
import org.javamexico.threads5.Procesador;
import org.javamexico.util.Cajero;
import org.javamexico.util.Cliente;
import org.javamexico.util.Fibonacci;
import org.javamexico.util.Pi;

/** Bonus Track 1: Thread Pools
 * 
 * @author Enrique Zamudio
 */
public class Pools implements ThreadFactory {

	static Random rng = new Random(System.currentTimeMillis());
	private String prefix;
	private int count = 1;

	private Pools(String threadNamePrefix) {
		prefix = threadNamePrefix;
	}

	/** Interfaz ThreadFactory */
	@Override
	public Thread newThread(Runnable target) {
		return new Thread(target, String.format("%s-%d", prefix, count++));
	}

	/** Encola varios procesos de calculo de Fibonacci en el thread pool especificado. */
	public static void queueFibos(ExecutorService tp) {
		for (int i = 0; i < 20; i++) {
			tp.execute(new Fibonacci(rng.nextInt(10000000) + 5000000));
		}
	}

	/** Encola varios procesos de calculo de PI en el thread pool especificado. */
	public static void queuePis(ExecutorService tp) {
		for (int i = 0; i < 10; i++) {
			tp.execute(new Pi(i*100000000));
		}
	}

	/** Prueba un Thread Pool enviandole varias tareas a realizar. */
	public static void testPool(ExecutorService tp) {
		queueFibos(tp);
		queuePis(tp);
		Cliente cliente = new Cliente();
		Cajero cajero = new Cajero();
		Procesador proc = new ProcFix();

		//Esto solo se puede hacer con threadpools de mas de uno porque la tarea no termina
		//AVISO: No es recomendable usar thread pools para esto
		tp.execute(proc.creaDepositador(cliente, cajero));
		tp.execute(proc.creaGastador(cliente, cajero));
	}

	public static void main(String[] args) {
		//Probando con un thread pool de un solo thread
		ExecutorService pool = null;
		//pool = Executors.newSingleThreadExecutor(new Pools("Single Thread"));
		//testPool(pool);

		//Probando un thread pool fijo
		//pool = Executors.newFixedThreadPool(10, new Pools("Fixed"));
		//testPool(pool);

		//Probando un thread pool con cache
		//pool = Executors.newCachedThreadPool(new Pools("Cached"));
		//testPool(pool);
	}

}
