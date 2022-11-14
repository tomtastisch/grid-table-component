package grid.inner.comp;

import java.io.Serial;
import java.util.Collection;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;

import grid.GridComponent;
import grid.GridContainerElement;
import grid.constants.GridConstants;
import grid.constants.StringConstants;
import grid.inner.comp.wrapper.GridWrapper;
import grid.utils.ColumnsUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * This class reflects the individual cell elements within the constructed table
 * and is used only to ensure the rendering of text and image within the table.
 */
@Getter
@Tag(value = "div")
@EqualsAndHashCode(callSuper = false)
public class GridCell extends GridContainerElement {

	@Serial
	private static final long serialVersionUID = 2379211827906909884L;
	
	private Component component;
	
	public GridCell() {
		this(StringConstants.PLACEHOLDER);
	}

	public GridCell(String text) {
		this(new GridEntry(text));
	}

	public GridCell(Component component) {
		this(GridComponent.class.cast(component instanceof GridComponent ? component : new GridWrapper(component)));
	}

	public GridCell(GridComponent component) {
		super(component.getTheme(), GridConstants.GRID_CELL);
		add(component);
	}
	
	/**
	 * @return <code>TRUE</code> if this cell contains a placeholder as text
	 */
	public boolean isPlaceholder() {
		return this.component.getElement().getText().equals(StringConstants.PLACEHOLDER);
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

		super.add(components);

		if (getComponents().size() > GridConstants.MAX_CELL_ELEMENTS_COUNT) {
			final int columns = ColumnsUtils.getLeastDivisor(getComponents().size());
			this.setComponents(new GridTable(columns, components));
		}
		
		this.component = this.getComponents().stream().findFirst().orElse(null);
	}
}
