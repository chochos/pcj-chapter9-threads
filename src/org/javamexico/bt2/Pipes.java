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

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/** Ejemplo de comunicacion entre threads usando PipedStreams.
 * 
 * @author Enrique Zamudio
 */
public class Pipes {

	public static void main(String[] args) throws IOException {
		PipedOutputStream pout = new PipedOutputStream();
		PipedInputStream pin = new PipedInputStream(pout);
		ProductorPipe prod = new ProductorPipe(pout);
		ConsumidorPipe cons = new ConsumidorPipe(pin);
		Thread tc = new Thread(cons, "cons");
		tc.start();
		prod.run();
	}

}
