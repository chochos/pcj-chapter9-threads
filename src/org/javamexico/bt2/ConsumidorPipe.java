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
import java.util.Date;

/** Ejemplo de un consumidor, un proceso que espera a que haya algo en un PipedInputStream para continuar.
 * 
 * @author Enrique Zamudio
 */
public class ConsumidorPipe implements Runnable {

	private PipedInputStream in;

	ConsumidorPipe(PipedInputStream pin) throws IOException {
		in = pin;
	}

	public void run() {
		byte[] b = new byte[2];
		while (true) {
			try {
				in.read(b);
				int x = ((b[0] & 0xff) << 8) | (b[1] & 0xff);
				System.out.printf("CONS tiempo para calcular fue %1$5d %2$TT.%2$TL%n", x, new Date());
			} catch (IOException ex) {
				System.out.println("CONS no puedo leer del stream");
			}
		}
	}

}
