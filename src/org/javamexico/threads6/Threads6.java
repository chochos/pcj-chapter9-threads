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
package org.javamexico.threads6;

import org.javamexico.Controlador;
import org.javamexico.Tarea;

/** Corre las tareas que usan wait y notify para coordinarse.
 * 
 * @author Enrique Zamudio
 */
public class Threads6 extends Controlador {

	public Threads6(String titulo, int hijos) {
		super(titulo, hijos);
	}

	@Override
	protected Thread crearHilo(int idx) {
		Tarea h = new Tarea6A(this, idx, Controlador.LIMITE);
		return new Thread(h, String.format("Hilo-%d", idx));
	}

	public static void main(String[] args) {
		int hijos = args.length > 0 ? Integer.parseInt(args[0]) : 10;
		new Threads6("Ejemplo 6: wait / notify", hijos);
	}

}
