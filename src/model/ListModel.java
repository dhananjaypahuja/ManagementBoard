package model;

import java.util.LinkedList;

/**
 * Parent class for list-structured project model components.
 */
public abstract class ListModel<T> extends LinkedList<T> {
	private static final long serialVersionUID = 1L;

    private String title;

    public ListModel() {
        title = null;
    }
    @SafeVarargs
    public ListModel(String title, T... contents) {
        this.title = title;
        for (T t : contents)
            add(t);
    }

    // Accessors
    public String getTitle() {
        return title;
    }
    // Mutators
    public void setTitle(String title) {
        this.title = title;
    }
}