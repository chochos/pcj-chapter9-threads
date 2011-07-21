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
package org.javamexico.threads2;

import org.javamexico.Controlador;

/** Este programa controla varios Threads, donde cada uno avanza un poco y luego espera a que su thread anterior
 * termine para continuar.
 * 
 * @author Enrique Zamudio
 */
public class Threads2 extends Controlador {

	private Thread previa;
	private int fragmento;

	private Threads2(String titulo, int hijos) {
		super(titulo, hijos);
	}

	@Override
	protected Thread crearHilo(int idx) {
		if (fragmento == 0) {
			fragmento = Controlador.LIMITE / getCuantosHijos();
		}
		Tarea2 nuevo = new Tarea2(this, idx, Controlador.LIMITE, idx * fragmento, previa);
		//lo anterior en vez de limite podria ser (i+1)*fragmento);
		previa = new Thread(nuevo, String.format("Hilo-%d", idx));
		return previa;
	}

	public static void main(String[] args) {
		int hijos = args.length > 1 ? Integer.parseInt(args[1]) : 10;
		new Threads2("Ejemplo 2 : Join", hijos);
	}

}
