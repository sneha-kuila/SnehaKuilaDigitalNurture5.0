import java.util.Arrays;
import java.util.Comparator;

public class LibrarySearch {

    // O(n) - checks each book until title matches
    public static Book linearSearchByTitle(Book[] books, String title) {
        for (Book b : books) {
            if (b.getTitle().equals(title)) {
                return b;
            }
        }
        return null;
    }

    // O(log n) - requires books to be sorted by title
    public static Book binarySearchByTitle(Book[] sortedBooks, String title) {
        int low = 0, high = sortedBooks.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = sortedBooks[mid].getTitle().compareTo(title);
            if (cmp == 0) {
                return sortedBooks[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
                new Book(1, "Clean Code", "Robert Martin"),
                new Book(2, "The Pragmatic Programmer", "Andrew Hunt"),
                new Book(3, "Introduction to Algorithms", "Cormen"),
                new Book(4, "Effective Java", "Joshua Bloch"),
        };

        System.out.println("Linear search 'Effective Java': " +
                linearSearchByTitle(books, "Effective Java"));

        Book[] sorted = books.clone();
        Arrays.sort(sorted, Comparator.comparing(Book::getTitle));
        System.out.println("Binary search 'Effective Java': " +
                binarySearchByTitle(sorted, "Effective Java"));
    }
}
