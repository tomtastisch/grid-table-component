package grid.inner.comp;

import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;

import grid.GridComponent;
import grid.constants.GridConstants;
import grid.constants.StringConstants;

/**
 * Table that is basically used as an element for display within grid elements.
 * The content and the individual cells of the table are fully iterable and
 * build up based on the passed information.
 */
@Tag(value = "div")
class GridContainer extends GridComponent implements HasComponents {

	@Serial
	private static final long serialVersionUID = -9035881813556427977L;

	/** length per row */
	private int rowLength;

	/** count of columns */
	private int colsCount;

	/** Content of the container as element list */
	private transient Set<GridComponent> elements;

	public GridContainer(int cnt, Comparator<Component> comparator, GridComponent... entries) {
		super(GridConstants.GRID_CONTAINER);
		init(cnt, entries);
		fillTable(comparator, true);
	}

	/**
	 * Initialize the table using the transferred data and calculate other required
	 * content. This method fills in even if the contents of the table columns are
	 * missing (for example, if there is one record but two columns), if necessary
	 * to.
	 * 
	 * @param columnsCount -> Number of columns, which are needed
	 * @param entries      -> Entries to be added in this component
	 * 
	 * @see #colsCount
	 * @see #rowLength
	 * @see #elements
	 */
	private void init(@Nonnegative int columnsCount, GridComponent[] entries) {
		colsCount = Math.max(columnsCount, GridConstants.DEFAULT_SIZE);
		rowLength = Math.max((int) Math.ceil((double) entries.length / colsCount), GridConstants.DEFAULT_SIZE);
		elements = Arrays.stream(Arrays.copyOf(entries, colsCount * rowLength))
				.map(e -> Objects.nonNull(e) ? e : new GridCell()).collect(Collectors.toCollection(LinkedHashSet::new));
	}

	public GridContainer sorted(Comparator<Component> comparator) {
		fillTable(comparator, true);
		return this;
	}

	/**
	 * Filling the table with the transferred and calculated data.
	 */
	private void fillTable(Comparator<Component> comparator, boolean clean) {
		setComponents(elements.stream().map(Component.class::cast).sorted(comparator).toList(), clean);
		HasComponents.super.add(getComponents());
		getElement().getStyle().set(GridConstants.COLUMNS_OPTION, Stream.generate(() -> GridConstants.OPTION_AUTO)
				.limit(colsCount).collect(Collectors.joining(StringConstants.SPACE)));
	}

	/**
	 * Creates a list of cells within the specified row and returns it within a
	 * collection.
	 * 
	 * @param index -> Index of the respective row
	 * @return all cells within the specified row index
	 */
	public List<GridComponent> getRow(@Nonnegative int index) {
		return getComponents().stream().skip(Integer.toUnsignedLong(index * rowLength)).limit(rowLength)
				.map(GridComponent.class::cast).toList();
	}

	/**
	 * Update the contained elements within this element
	 * 
	 * @param consumer -> Update function
	 */
	public void updateElements(Consumer<? super GridComponent> consumer) {
		setComponents(getComponents(), true);
	}

	/**
	 * @return all cells within this table.
	 */
	public List<GridComponent> getElements() {
		return getInnerComponents(GridComponent.class);
	}

	@Override
	public void add(String text) {
		add(new GridCell(text));
	}

	@Override
	public void add(Collection<Component> components) {
		add(components.stream().toArray(Component[]::new));
	}

	@Override
	public void add(Component... components) {
		setComponents(Arrays.asList(components), false);
		HasComponents.super.add(getComponents());
	}

	public void updateContent(Iterable<Component> components) {
		setComponents(components, true);
	}
}
