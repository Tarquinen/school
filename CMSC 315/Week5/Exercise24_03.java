// Look for WRITE YOUR CODE to write your code
import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Collection;

public class Exercise24_03 {
   public static void main(String[] args) {
      new Exercise24_03();
   }
   
   public Exercise24_03() {
      TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();
      System.out.print("Enter five numbers: ");
      Scanner input = new Scanner(System.in);
      double[] v = new double[5];
      for (int i = 0; i < 5; i++) 
      v[i] = input.nextDouble();

      list.add(v[1]);
      list.add(v[2]);
      list.add(v[3]);
      list.add(v[4]);
      list.add(0, v[0]);
      list.add(2, 10.55);
      list.remove(3);

      // list.add(1.0);
      // list.add(2.0);
      // list.add(2, 3.0);
      // list.addFirst(0.5);
      // list.removeLast();

      java.util.ListIterator<Double> iterator1 = list.listIterator();
      System.out.print("The list in forward order: ");
      while (iterator1.hasNext())
         System.out.print(iterator1.next() + " ");

      java.util.ListIterator<Double> iterator2 = list.listIterator(list.size() - 1);
      System.out.print("\nThe list in backward order: ");
      while (iterator2.hasPrevious())
         System.out.print(iterator2.previous() + " ");
   }
}

interface MyList<E> extends java.util.Collection<E> {
   /** Add a new element at the specified index in this list */
   public void add(int index, E e);

   /** Return the element from this list at the specified index */
   public E get(int index);

   /** Return the index of the first matching element in this list.
      *  Return -1 if no match. */
   public int indexOf(Object e);

   /** Return the index of the last matching element in this list
      *  Return -1 if no match. */
   public int lastIndexOf(E e);

   /** Remove the element at the specified position in this list
      *  Shift any subsequent elements to the left.
      *  Return the element that was removed from the list. */
   public E remove(int index);

   /** Replace the element at the specified position in this list
      *  with the specified element and returns the new set. */
   public E set(int index, E e);
   
   @Override /** Add a new element at the end of this list */
   public default boolean add(E e) {
      add(size(), e);
      return true;
   }

   @Override /** Return true if this list contains no elements */
   public default boolean isEmpty() {
      return size() == 0;
   }

   @Override /** Remove the first occurrence of the element e 
      *  from this list. Shift any subsequent elements to the left.
      *  Return true if the element is removed. */
   public default boolean remove(Object e) {
      if (indexOf(e) >= 0) {
         remove(indexOf(e));
         return true;
      }
      else
         return false;
   }

   @Override
   public default boolean containsAll(Collection<?> c) {
      // Left as an exercise
      for (Object e: c) {
         if (!contains(e)) {
            return false;
         }
      }
      return true;
   }

   @Override
   public default boolean addAll(Collection<? extends E> c) {
      // Left as an exercise
      for (E e: c) {
         add(e);
      }
      return true;
   }

   @Override
   public default boolean removeAll(Collection<?> c) {
      // Left as an exercise
      boolean modified = false;
      for (Object e: c) {
         if (remove(e)) {
            modified = true;
         }
      }
      return modified;
   }

   @Override
   public default boolean retainAll(Collection<?> c) {
      // Left as an exercise
      boolean modified = false;
      Iterator<E> it = iterator();
      while (it.hasNext()) {
         E element = it.next();
         if (!c.contains(element)) {
               it.remove();
               modified = true;
         }
      }
      return modified;
   }

   @Override
   public default Object[] toArray() {
      // Left as an exercise
      Object[] array = new Object[size()];
      int i = 0;
      for (E e: this) {
         array[i++] = e;
      }  
      return array;
   }

   @Override
   public default <T> T[] toArray(T[] array) {
      // Left as an exercise
      return null;
   }
}

class TwoWayLinkedList<E> implements MyList<E> {
   private Node<E> head, tail;
   private int size = 0;

   /** Create a default list */
   public TwoWayLinkedList() {
   }

   /** Create a list from an array of objects */
   public TwoWayLinkedList(E[] objects) {
      for (E e : objects)
         add(e);
   }

   /** Return the head element in the list */
   public E getFirst() {
      if (size == 0) {
         return null;
      } else {
         return head.element;
      }
   }

   /** Return the last element in the list */
   public E getLast() {
      if (size == 0) {
         return null;
      } else {
         return tail.element;
      }
   }

   @Override
   public String toString() {
      StringBuilder result = new StringBuilder("[");

      Node<E> current = head;
      for (int i = 0; i < size; i++) {
         result.append(current.element);
         current = current.next;
         if (current != null) {
            result.append(", "); // Separate two elements with a comma
         } else {
            result.append("]"); // Insert the closing ] in the string
         }
      }
      return result.toString();
   }

   /** Clear the list */
   public void clear() {
      head = tail = null;
   }

   /** Return true if this list contains the element o */
   public boolean contains(Object e) {
      Node<E> current = head;
      for (int i = 0; i < size; i++) {
         if (current.element.equals(e)) {
            return true;
         }
         current = current.next;
      }
      return false;
   }

   /** Return the element from this list at the specified index */
   public E get(int index) {
      if (index < 0 || index > size) {
         return null;
      }
      else if (index == 0) {
         return getFirst();
      }
      else if (index == size - 1) {
         return getLast();
      }
      else if (index < size / 2) {
         Node<E> current = head;
         for (int i = 0; i < index; i++) {
            current = current.next;
         }
         return current.element;
      }
      else {
         Node<E> current = tail;
         for (int i = 1; i < size - index; i++) {
            current = current.previous;
         }
         return current.element;
      }
   }

   /**
      * Return the index of the head matching element in this list. Return -1 if
      * no match.
      */
   public int indexOf(Object e) {
      System.out.println("Implementation left as an exercise");
   
      Node<E> current = head;
      for (int i = 0; i < size; i++) {
         if (current.element.equals(e)) {
            return i;
         }
         current = current.next;
      }
      return -1;
   }

   /**
      * Return the index of the last matching element in this list Return -1 if
      * no match.
      */
   public int lastIndexOf(Object e) {
      System.out.println("Implementation left as an exercise");
      Node<E> current = tail;
      for (int i = 1; i < size + 1; i++) {
         if (current.element.equals(e)) {
            return size - i;
         }
         current = current.previous;
      }
      return -1;
   }

   /**
      * Replace the element at the specified position in this list with the
      * specified element.
      */
   public E set(int index, E e) {
      System.out.println("Implementation left as an exercise");
      return null;
   }

   private class LinkedListIterator implements java.util.ListIterator<E> {
      private Node<E> current = head; // Current index

      public LinkedListIterator() {
      }
      
      public LinkedListIterator(int index) {
         if (index < 0 || index >= size)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
            + size);
         for (int nextIndex = 0; nextIndex < index; nextIndex++)
         current = current.next;
      }
      
      public void setLast() {
         current = tail;
      }
      
      @Override
      public boolean hasNext() {
         return (current != null);
      }

      @Override
      public E next() {
         E e = current.element;
         current = current.next;
         return e;
      }

      @Override
      public void remove() {
         System.out.println("Implementation left as an exercise");
      }

      @Override
      public void add(E e) {
         System.out.println("Implementation left as an exercise");
      }

      @Override
      public boolean hasPrevious() {
         return current != null;
      }

      @Override
      public int nextIndex() {
         System.out.println("Implementation left as an exercise");
         return 0;
      }

      @Override
      public E previous() {
         E e = current.element;
         current = current.previous;
         return e;
      }

      @Override
      public int previousIndex() {
         System.out.println("Implementation left as an exercise");
         return 0;
      }

      @Override
      public void set(E e) {
         current.element = e; // TODO Auto-generated method stub
      }
   }

   private class Node<E> {
      E element;
      Node<E> next;
      Node<E> previous;

      public Node(E o) {
         element = o;
      }
   }

   @Override
   public int size() {
      return size;
   }

   public ListIterator<E> listIterator() {
      return new LinkedListIterator(); 
   }
   
   public ListIterator<E> listIterator(int index) {
      return new LinkedListIterator(index); 
   }

   @Override
   public Iterator<E> iterator() {
      // TODO Auto-generated method stub
      return null;
   }

   /** Add an element to the beginning of the list */
   public void addFirst(E e) {
      // WRITE YOUR CODE HERE
      Node<E> newNode = new Node<>(e);
      if (head == null) {
         head = tail = newNode;
      }
      else {
         newNode.next = head;
         head.previous = newNode;
         head = newNode;
      }
      size++;
   }

   /** Add an element to the end of the list */
   public void addLast(E e) {
      // WRITE YOUR CODE HERE
      Node<E> newNode = new Node<>(e);
      if (tail == null) {
         head = tail = newNode;
      }
      else {
         newNode.previous = tail;
         tail.next = newNode;
         tail = newNode;
      }
      size++;
   }

   /**
      * Add a new element at the specified index in this list The index of the
      * head element is 0
      */
   public void add(int index, E e) {
      // WRITE YOUR CODE HERE
      if (index == 0) 
         addFirst(e);
      else if (index >= size) 
         addLast(e); 
      else if (index < size / 2) {
         Node<E> current = head; 
         for (int i = 1; i < index; i++) {
            current = current.next;
         }
         Node<E> temp = current.next;
         current.next = new Node<>(e);
         (current.next).next = temp;
         (current.next).previous = current;
         temp.previous = current.next;
         size++;
      }
      else {
         Node<E> current = tail;
         for (int i = 1; i < size - index; i++) {
            current = current.previous;
         }
         Node<E> temp = current.previous;
         current.previous = new Node<>(e);
         (current.previous).previous = temp;
         (current.previous).next = current;
         temp.next = current.previous;
         size++;
      }
   }

   /**
      * Remove the head node and return the object that is contained in the
      * removed node.
      */
   public E removeFirst() {
      // Write your code here
      if (size == 0) {
         return null;
      }
      else {
         E temp = head.element;
         head = head.next;
         head.previous = null;
         size --;
         if (head == null) {
            tail = null;
         }
         return temp;
      }
   }

   /**
      * Remove the last node and return the object that is contained in the
      * removed node.
      */
   public E removeLast() {
      // WRITE YOUR CODE HERE
      if (size == 0) {
         return null;
      }
      else { 
         E temp = tail.element;
         tail = tail.previous;
         tail.next = null;
         size--;
         if (tail == null) {
            head = null;
         }
         return temp;
      }
   }

   /**
      * Remove the element at the specified position in this list. Return the
      * element that was removed from the list.
      */
   public E remove(int index) {
      // WRITE YOUR CODE HERE
      if (index < 0 || index >= size) {
         return null;
      }
      else if (index == 0) {
         return removeFirst();
      }
      else if (index == size - 1) {
         return removeLast();
      }
      else if (index < size / 2) {
         Node<E> previous = head;
         for (int i = 1; i < index; i++) {
            previous = previous.next;
         }
         Node<E> current = previous.next;
         previous.next = current.next;
         current.next.previous = previous;
         size--;
         return current.element;
      }
      else {
         Node<E> next = tail;
         for (int i = 2; i < size - index; i++) {
            next = next.previous;
         }
         Node<E> current = next.previous;
         next.previous = current.previous;
         current.previous.next = next;
         size--;
         return current.element;
      }
   }
}