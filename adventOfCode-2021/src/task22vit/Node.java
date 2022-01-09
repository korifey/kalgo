package task22vit;

class Node<T> {
  static <T> Node<T> of(T value, Node<T> prev, Node<T> next) {
    return new Node<T>(value, prev, next);
  }
  
  static <T> Node<T> of(T value) {
    return new Node<T>(value, null, null);
  }
  
  T value;
  Node<T> prev;
  Node<T> next;
  
  private Node() {
    // TODO Auto-generated constructor stub
  }
  
  private Node(T value, Node<T> prev, Node<T> next) {
    this.value = value;
    this.prev = prev;
    this.next = next;
  }
  
  // Returns the first element in the list (begin)
  public void remove() {
    if (prev != null) {
      prev.next = next;
    }
    
    if (next != null) {
      next.prev = prev;
    }
    
    next = prev = null;
  }
}
