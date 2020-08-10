package com.dmlab.bst;

public class BookSearch {

    private BinarySearchTree<String, String> bst;

    /**
     * Constructor
     * Do not modify this function.
     */
    public BookSearch() {
        bst = new BinarySearchTree<>();
    }

    /**
     * This function returns the root of the bst.
     * TreePrinter will call this function to print your current tree.
     * So do not modify this function.
     *
     * @return the root of the BinarySearchTree
     */
    public BinaryNode<String, String> getRoot() {
        if (bst == null)
            return null;
        return bst.getRoot();
    }

    /**
     * This function adds the book information into BookSearch.
     * The book information is in forms of a key-value pair:
     * the key is "name" as the book name, and the value is "location" of the book.
     *
     * @param name     of the book
     * @param position of the book
     */
    public void add(String name, String position) {
        bst.insert(name, position);
    }

    /**
     * This function removes the book with "name" from BookSearch.
     *
     * @param name of the book we want to remove.
     * @return the location of removed book. If no such book, return null.
     */
    public String remove(String name) {
        return bst.remove(name);
    }

    /**
     * Given the book name, this function should return the location of the book.
     *
     * @param name of the book that we want to get.
     * @return the position of the book. If no such book, return null.
     */
    public String get(String name) {
        return bst.find(name);
    }

    /**
     * This function returns the number of books in the BookSearch.
     *
     * @return the number of books.
     */
    public int size() {
        return bst.size();
    }

    /**
     * This function retrieves the information of books in lexicographical order.
     * The function should print all book names. Print each book name for each line.
     * The Output specification is "BOOK:\t"+ bookName.
     * If BookSearch does not have any book, print the message "BookSearch does not have any book".
     */
    public void printBookList() {
        if (bst.size() == 0) {
            System.out.println("BookSearch does not have any book");
            return;
        }
        bst.printBookList();
    }

    /**
     * This function finds the name of the book of the given order.
     *
     * @param order of the book.
     * @return the name of corresponding book.
     */
    public String orderSearch(int order) {
        if (bst.size() == 0) {
            return null;
        }
        return bst.orderSearch(order);
    }

    /**
     * This function finds the order of the book of the given name.
     *
     * @param name of the book.
     * @return the order of the book.
     */
    public int orderSearch(String name) {
        if (bst.size() == 0) {
            return 0;
        }
        return bst.orderSearch(name);
    }
}
