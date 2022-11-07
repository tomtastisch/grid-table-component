package grid.enums;

import java.util.function.Function;

import grid.GridComponent;

/** Functionally determines the return value of the specified index type. */
public enum Index {
	/** Function for determining the row index. */
	ROW(e -> e.getParent().get().getElement().getParent().indexOfChild(e.getElement())),
	/** Function for determining the column index. */
	COLUMN(e -> e.getElement().getParent().indexOfChild(e.getElement()));
	
	final Function<? super GridComponent, Integer> fFunction;

	/**
	 * Constructor of this enumeration
	 * 
	 * @param aFunction -> which should get the index of the passed component
	 */
	Index(Function<? super GridComponent, Integer> aFunction) {
		this.fFunction = aFunction;
	}

	public <T extends GridComponent> int from(T aComponent) {
		return this.fFunction.apply(aComponent);
	}
}