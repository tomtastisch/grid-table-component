package grid.inner.comp;

import java.awt.Point;
import java.io.Serial;

import com.vaadin.flow.component.Tag;

import grid.GridComponent;
import grid.constants.GridConstants;
import grid.constants.StringConstants;
import grid.enums.Index;

/**
 * This class reflects the individual cell elements within the constructed table
 * and is used only to ensure the rendering of text and image within the table.
 */
@Tag(value = "div")
public class GridCell extends GridComponent {

	@Serial
	private static final long serialVersionUID = 2379211827906909884L;

	/**
	 * Protected constructor to ensure that it can only be accessed within the table
	 * components.
	 */
	protected GridCell() {
		this(StringConstants.PLACEHOLDER);
	}

	/**
	 * Constructor
	 * 
	 * @param aEntry -> Entry which is to be reproduced within this cell
	 */
	public GridCell(GridEntry aEntry) {
		super(aEntry.getTheme(), GridConstants.GRID_CELL);
		getElement().setText(aEntry.name);
	}

	/**
	 * Constructor
	 * 
	 * @param aText -> Text to be used in the header line
	 */
	public GridCell(String aText) {
		super(GridConstants.DEFAULT_THEME, GridConstants.GRID_CELL);
		getElement().setText(aText);
	}

	/**
	 * @return the position of this cell within the table
	 */
	public Point getPosition() {
		return new Point(getIndex(Index.COLUMN), getIndex(Index.ROW));
	}
}
