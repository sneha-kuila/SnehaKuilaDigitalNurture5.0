/**
 * Singly Linked List implementation for managing tasks.
 */
public class TaskLinkedList {

    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
        }
    }

    private Node head;
    private int size;

    // O(1) - add at head; O(n) if inserted at tail while tracking tail is not kept
    public void add(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode; // O(n) to reach the end
        }
        size++;
    }

    // O(n) - must traverse from head until match is found
    public Task search(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // O(n) - visits every node
    public void traverse() {
        Node current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.task).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        System.out.println(sb);
    }

    // O(n) - must find the node and relink pointers
    public void delete(int taskId) {
        if (head == null) return;

        if (head.task.getTaskId() == taskId) {
            head = head.next;
            size--;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.task.getTaskId() != taskId) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next; // unlink
            size--;
        }
    }

    public int size() { return size; }

    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();
        list.add(new Task(1, "Design schema", "Pending"));
        list.add(new Task(2, "Build API", "In Progress"));
        list.add(new Task(3, "Write tests", "Pending"));

        System.out.print("Traverse: ");
        list.traverse();

        System.out.println("Search 2: " + list.search(2));

        list.delete(1);
        System.out.print("After delete: ");
        list.traverse();
    }
}
