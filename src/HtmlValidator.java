import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		// creamos la pila
		Stack<HtmlTag> stack = new Stack<>();
		// leemos cada etiqueta para verificar si es de auto cierre
		for (HtmlTag tag : tags) {
			if (tag.isSelfClosing()) {  // si la etiqueta es de auto-cierre, salta al final, porque no requiere hacerle seguimiento
				continue;				// y verificar que tenga otra de cierre

			} else if (tag.isOpenTag()) { // además verificamos si es de apertura
				stack.push(tag); // de ser así la agregamos a la pila
			} else if (!tag.isOpenTag()) { // si no es de apertura
				if (!stack.isEmpty()) { // revisamos que la pila tenga etiquetas
					if (stack.peek().matches(tag)) { // y si tiene elementos, trae la etiqueta de arriba y los compara con la etiqueta de iteración
						stack.pop(); // si es así la sacamos, porque ya han sido verificadas
					} else {
						return stack; // si no coiciden se retorna la pila como está actualmente.
					}
				} else {
					return null; // si está vacia la pila se retorna nulo
				}
			}
		}
		return stack; // al finalizar la iteración se retorna la pila vacía para que sea válido.
	}
}
