public interface IStream {

	/**
	 * Pega o próximo caractere
	 * 
	 * @return próximo caracter disponível
	 */
	char getNext();

	/**
	 * Verifica se há outro caractere 
	 * 
	 * @return há ou não mais caractere
	 */
	boolean hasNext();
}

