/**
 * Implementação da Interface Stram
 * 
 */
public class StreamImpl implements IStream {

  /**
   * Stream de entrada
   */
  private String stream;

  /**
   * Último index lido
   */
  private int lastIndex = -1;

  /**
   * Construtor
   * 
   * @param stream input
   */
  public StreamImpl(String stream) {
    this.stream = stream;
  }

  /**
   * @see IStream
   */
  public char getNext() {
    char next = stream.charAt(++lastIndex);
    return next;
  }

  /**
   * @see IStream
   */
  public boolean hasNext() {

    try {
      stream.charAt(lastIndex + 1);
      return true;
    } catch (IndexOutOfBoundsException ex) {
    }

    return false;
  }

}
