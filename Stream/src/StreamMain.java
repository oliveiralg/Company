public class StreamMain {

  /**
   * Caractere vazio
   */
  static final char CHAR_VAZIO = 0;

  /**
   * Vogais
   */
  static final char[] VOGAIS = {'a', 'e', 'i', 'o', 'u'};

  /**
   * M�todo de execu��o Main
   * 
   * @param args - The first index will be the input chars list as String
   */
  public static void main(String[] args) {
    char retorno = primeiroCaracter(args[0]);
    System.out.println(retorno == CHAR_VAZIO ? "N�o foi encontrado a vogal no input fornecido."
        : "A primeira vogal, ap�s uma consoante, onde a mesma � antecessora a uma vogal � "
            + retorno);
  }

  /**
   * Esse m�todo retorno a primeira vogal que n�o se repete ap�s uma consoante, onde a mesma �
   * antecessora a uma vogal
   * 
   * @param input chars input stream list
   * @return
   */
  public static char primeiroCaracter(String stream) {

    StreamImpl input = new StreamImpl(stream);
    int[] vogaisNum = new int[5];

    while (input.hasNext()) {

      char charAtual = Character.toLowerCase(input.getNext());
      switch (charAtual) {
        case 'a':
          vogaisNum[0] = vogaisNum[0] + 1;
          break;
        case 'e':
          vogaisNum[1] = vogaisNum[1] + 1;
          break;
        case 'i':
          vogaisNum[2] = vogaisNum[2] + 1;
          break;
        case 'o':
          vogaisNum[3] = vogaisNum[3] + 1;
          break;
        case 'u':
          vogaisNum[4] = vogaisNum[4] + 1;
          break;
      }
    }
    int menorIndex = Integer.MAX_VALUE;
    for (int i = 0; i <= 4; i++) {
      if (vogaisNum[i] == 1) { // A Vogal � �nica no imput
        int indexAtual = stream.toLowerCase().indexOf(VOGAIS[i]);
        if (indexAtual < menorIndex && indexAtual > 1
            && ((stream.charAt(indexAtual - 1) != VOGAIS[0]
                && stream.charAt(indexAtual - 1) != VOGAIS[1]
                && stream.charAt(indexAtual - 1) != VOGAIS[2]
                && stream.charAt(indexAtual - 1) != VOGAIS[3]
                && stream.charAt(indexAtual - 1) != VOGAIS[4]) &&
            // N�o � vogal
                ((stream.charAt(indexAtual - 1) >= 'a' && stream.charAt(indexAtual - 1) <= 'z')
                    || (stream.charAt(indexAtual - 1) >= 'A'
                        && stream.charAt(indexAtual - 1) <= 'Z')))
            // Anterior consoante
            && (stream.charAt(indexAtual - 2) == VOGAIS[0]
                || stream.charAt(indexAtual - 2) == VOGAIS[1]
                || stream.charAt(indexAtual - 2) == VOGAIS[2]
                || stream.charAt(indexAtual - 2) == VOGAIS[3]
                || stream.charAt(indexAtual - 2) == VOGAIS[4])) // Anterior a consoante � vogal
          menorIndex = stream.toLowerCase().indexOf(VOGAIS[i]);
      }
    }
    if (menorIndex != Integer.MAX_VALUE)
      return stream.charAt(menorIndex);
    else
      return CHAR_VAZIO;

  }
}
