package grid.inner.comp;

import java.io.Serial;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;

import grid.GridComponent;
import grid.constants.GridConstants;
import grid.enums.ThemeType;
import grid.utils.ColumnsUtils;

/**
 * Class that serves as a template for the construction of a grid table. Within
 * this class {@link GridComponent component} both a {@link GridHeader header}
 * and a {@link GridContainer container} are formed, with the
 * {@link GridContainer container} basically representing the actual table.
 */
@Tag(value = "div")
@CssImport("./themes/atus-web/components/grid-table-style.css")
class GridTable extends GridComponent implements HasComponents {

	@Serial
	private static final long serialVersionUID = 4007301230963116892L;

	private GridHeader header;

	private GridContainer container;

	public GridTable(String... txt) {
		this(ColumnsUtils.getLeastDivisor(txt.length), txt);
	}

	public GridTable(int columns, String... txt) {
		this(GridHeader.NO_HEADER, columns, txt);
	}

	public GridTable(String header, int columns, String... txt) {
		this(header, GridConstants.DEFAULT_THEME, columns, txt);
	}

	public GridTable(String header, ThemeType theme, int columns, String... txt) {
		this(header, theme, columns, (o1, o2) -> 0, Stream.of(txt).map(GridEntry::new).toArray(GridComponent[]::new));
	}

	public GridTable(String tableHeaedr, ThemeType theme, int i, Comparator<Component> comp, GridComponent... entries) {
		super(GridConstants.GRID_TABLE);
		init(new GridHeader(theme, Objects.requireNonNullElse(tableHeaedr, GridHeader.NO_HEADER)),
				new GridContainer(i, comp, entries));
	}

	private void init(GridHeader header, GridContainer container) {
		this.header = header;
		this.container = container;
		add(header, container);
	}

	public GridHeader getHeader() {
		return this.header;
	}

	public GridContainer getContainer() {
		return this.container;
	}
}
