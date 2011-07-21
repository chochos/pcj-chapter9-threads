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
package org.javamexico.threads1;

import org.javamexico.Controlador;
import org.javamexico.Tarea;

/** Primer ejemplo de Threads, muy simple, solamente para ver la diferencia entre usar o no usar yield().
 * 
 * @author Enrique Zamudio
 */
public class Threads1 extends Controlador {

	public Threads1(String titulo, int hijos) {
		super(titulo, hijos);
	}

	@Override
	protected Thread crearHilo(int idx) {
		Tarea tarea = new Tarea1A(this, idx, Controlador.LIMITE);
		Thread t = new Thread(tarea);
		return t;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		int hijos = args.length > 0 ? Integer.parseInt(args[0]) : 10;
		new Threads1("Threads Ejemplo 1: Simple", hijos);
	}

}
