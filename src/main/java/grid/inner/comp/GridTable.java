package grid.inner.comp;

import java.io.Serial;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;

import grid.GridComponent;
import grid.GridContainerElement;
import grid.constants.GridConstants;
import grid.constants.StringConstants;
import grid.enums.ThemeType;
import grid.utils.ColumnsUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * Container that is basically used as an element for display within grid
 * elements. The content and the individual cells of the table are fully
 * iterable and build up based on the passed information.
 */
@Data
@Tag(value = "div")
@Setter(value = AccessLevel.NONE)
@EqualsAndHashCode(callSuper = false)
class GridTable extends GridContainerElement {

	@Serial
	private static final long serialVersionUID = 211974789826631517L;
	
	/** length per row */
	private int rowsCount;
	
	/** count of columns */
	private int colsCount;

	public GridTable(String... components) {
		this(Arrays.stream(components).map(GridEntry::new).toArray(GridComponent[]::new));
	}

	public GridTable(Component... components) {
		this(ColumnsUtils.getLeastDivisor(components.length), components);
	}

	public GridTable(int cnt, Component... components) {
		super(ThemeType.NEUTRAL, GridConstants.GRID_TABLE);
		init(cnt, components);
	}

	/**
	 * Initialize the table using the transferred data and calculate other required
	 * content. This method fills in even if the contents of the table columns are
	 * missing (for example, if there is one record but two columns), if necessary
	 * to.
	 * 
	 * @param columnsCount -> Number of columns, which are needed
	 * @param components   -> Entries to be added in this component
	 * 
	 * @see #colsCount
	 * @see #rowsCount
	 */
	private void init(@Nonnegative int columnsCount, Component[] components) {
		colsCount = Math.max(columnsCount, GridConstants.DEFAULT_SIZE);

		// Generates the option style and set the count of columns
		final String style = Stream.generate(() -> GridConstants.OPTION_AUTO).limit(colsCount)
				.collect(Collectors.joining(StringConstants.SPACE));

		set(GridConstants.COLUMNS_OPTION, style);
		add(components);
	}

	/**
	 * Returns a list within the specified row and returns it within a collection.
	 * 
	 * @param index -> Index of the respective row
	 * @return all cells within the specified row index
	 */
	public List<Component> getRow(@Nonnegative int index) {
		return getComponents().stream().skip(Integer.toUnsignedLong(index * colsCount)).limit(colsCount).toList();
	}

	/**
	 * Returns a list within the specified column and returns it within a
	 * collection.
	 * 
	 * @param index -> Index of the respective row
	 * @return all cells within the specified column index
	 */
	public List<Component> getColumn(@Nonnegative int index) {
		return Stream.iterate(Math.max(index, colsCount), i -> i + (index > colsCount ? 0 : colsCount))
				.limit(index > colsCount ? 0 : getComponents().size() / colsCount)
				.map(i -> getComponents().stream().toList().get(i)).toList();
	}

	@Override
	public void add(String text) {
		this.add(new GridCell(text));
	}

	@Override
	public void add(Collection<Component> components) {
		this.add(components.stream().toArray(Component[]::new));
	}

	@Override
	public void add(Component... components) {
		
		final Component[] concatComps = Stream.of(getComponents().stream().map(GridCell.class::cast)
				.filter(cell -> !cell.isPlaceholder()).toList(), Arrays.asList(components)).flatMap(Collection::stream)
				.toArray(Component[]::new);

		// reset size of rows
		rowsCount = Math.max((int) Math.ceil((double) concatComps.length / colsCount), GridConstants.DEFAULT_SIZE);
		
		// set components as new content
		setComponents(Arrays.stream(Arrays.copyOf(concatComps, colsCount * rowsCount))
				.map(e -> Objects.requireNonNullElse(e, new GridCell())).toArray(Component[]::new));

	}
}
