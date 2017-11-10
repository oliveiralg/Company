public interface IStream {

	/**
	 * Pega o pr�ximo caractere
	 * 
	 * @return pr�ximo caracter dispon�vel
	 */
	char getNext();

	/**
	 * Verifica se h� outro caractere 
	 * 
	 * @return h� ou n�o mais caractere
	 */
	boolean hasNext();
}

