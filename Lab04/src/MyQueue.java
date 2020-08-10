
public class MyQueue<E> implements Queue<E> {

    private Node<E> first = null;
    private Node<E> last = null;
    private int size = 0;

    @Override
    public void enqueue(E item) {
        Node<E> newNode = new Node(item, null);
        if (size == 0) {
            this.first = newNode;
            this.last = newNode;
            size++;
        } else {
            this.last.setNext(newNode);
            this.last = newNode;
            this.size++;
        }
    }

    @Override
    public E dequeue() {
        if (this.first != null) {
            Node<E> resultNode = this.first;
            this.first = this.first.getNext();
            this.size--;
            return resultNode.getItem();
        }
        return null;
    }

    @Override
    public E pop() {
        if (this.last != null) {
            Node<E> resultNode = this.last;
            for (Node<E> currNode = this.first; currNode != null; currNode = currNode.getNext()) {
                if (currNode.getNext() == this.last) {
                    currNode.setNext(null);
                    this.last = currNode;
                    break;
                }
            }
            size--;
            return resultNode.getItem();
        }

        return null;
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public String toString() {
        Node<E> cursor = first;
        StringBuffer sb = new StringBuffer("(");
        while (cursor != null) {
            sb.append(cursor.getItem());
            if (cursor != last) {
                sb.append(' ');
            }
            cursor = cursor.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

}
