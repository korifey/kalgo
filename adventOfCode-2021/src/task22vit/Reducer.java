package task22vit;

import java.util.LinkedList;
import java.util.List;

class Reducer {
  private static Node<Cube> listToCustomList(List<Cube> cubes) {
    Node<Cube> ptr = null, head = null;
    
    // Create custom list
    for (Cube c : cubes) {
      if (head == null) {
        head = Node.of(c);
        ptr = head;
      } else {
        Node<Cube> next = Node.of(c, ptr, null);
        ptr.next = next;
        ptr = next;
      }
    }
    
    return head;
  }
  
  private static LinkedList<Cube> customListToList(Node<Cube> head) {
    LinkedList<Cube> res = new LinkedList<>();
    for (Node<Cube> ptr = head; ptr != null; ptr = ptr.next) {
      res.push(ptr.value);
    }
    
    return res;
  }
  
  private final LinkedList<Cube> cubes;
  
  public Reducer(LinkedList<Cube> cubes) {
    this.cubes = cubes;
  }
  
  private boolean reduced; // if custom list lost in size
  private boolean pointersModified; // if i1 and i2 were changed custom way (i.e. not i1 = i1.next in the end of the while body)
  private Node<Cube> head, i1, i2;
  
  public LinkedList<Cube> reduce() {
    head = listToCustomList(cubes);
    
    // Reduce custom list
    do {
      reduced = false;
      i1 = head;
      
      outer: while (i1 != null) {
        i2 = i1.next;
        
        while (i2 != null) {
          pointersModified = false;
          Cube c1 = i1.value;
          Cube c2 = i2.value;
          
          if (c1.equals(c2)) {
            Node<Cube> tmp = i2.next;
            i2.remove();
            i2 = tmp;
            pointersModified = true;
            reduced = true;
          } else {
            if (c1.y1 == c2.y1 && c1.y2 == c2.y2 && c1.z1 == c2.z1 && c1.z2 == c2.z2 //
                && (c1.x2 == c2.x1 || c2.x2 == c1.x1)) {
              if (c1.x2 == c2.x1) {
                combine(Cube.of(c1.x1, c2.x2, c1.y1, c1.y2, c1.z1, c1.z2));
              } else {
                combine(Cube.of(c2.x1, c1.x2, c1.y1, c1.y2, c1.z1, c1.z2));
              }
            } else if (c1.x1 == c2.x1 && c1.x2 == c2.x2 && c1.z1 == c2.z1 && c1.z2 == c2.z2 //
                && (c1.y2 == c2.y1 || c2.y2 == c1.y1)) {
              if (c1.y2 == c2.y1) {
                combine(Cube.of(c1.x1, c1.x2, c1.y1, c2.y2, c1.z1, c1.z2));
              } else {
                combine(Cube.of(c1.x1, c1.x2, c2.y1, c1.y2, c1.z1, c1.z2));
              }
            } else if (c1.x1 == c2.x1 && c1.x2 == c2.x2 && c1.y1 == c2.y1 && c1.y2 == c2.y2 //
                && (c1.z2 == c2.z1 || c2.z2 == c1.z1)) {
              if (c1.z2 == c2.z1) {
                combine(Cube.of(c1.x1, c1.x2, c1.y1, c1.y2, c1.z1, c2.z2));
              } else {
                combine(Cube.of(c1.x1, c1.x2, c1.y1, c1.y2, c2.z1, c1.z2));
              }
            }
          }
          
          if (i1 == null) {
            break outer;
          }
          
          if (pointersModified) {
            continue;
          }
          
          i2 = i2.next;
        }
        
        i1 = i1.next;
      }
    } while (reduced);
    
    // Copy custom list
    LinkedList<Cube> res = customListToList(head);
    
    return res;
  }
  
  private void combine(Cube c) {
    Node<Cube> ii1 = i1, ii2 = i2;
    
    do {
      ii1 = ii1.next;
    } while (ii1 == ii2);
    
    do {
      ii2 = ii2.next;
    } while (ii1 == ii2 && ii2 != null);
    
    i1.remove();
    i2.remove();
    
    if (head == i1) {
      head = ii1;
    }
    
    Node<Cube> node = Node.of(c, null, head);
    
    if (head != null) {
      head.prev = node;
    }

    head = node;
    
    i1 = ii1;
    i2 = ii2;
    pointersModified = true;
    
    reduced = true;
  }
}
