package grid.inner.comp;

import java.io.Serial;

import com.vaadin.flow.component.Tag;

import grid.GridComponent;
import grid.constants.GridConstants;
import grid.constants.StringConstants;
import grid.enums.ThemeType;

/** 
 * This class corresponds to the header of the table to be created 
 */
@Tag(value = "header")
class GridHeader extends GridComponent {
	
	@Serial
	private static final long serialVersionUID = 7113774239337566165L;

	/* The default value used when the header content is null or empty. **/
	public static final String NO_HEADER = StringConstants.EMPTY;

	/**
	 * @param aText -> Text to be used in the header line
	 */
	public GridHeader(String text) {
		this(ThemeType.NEUTRAL, text);
	}
	
	/**
	 * @param theme -> Background theme of this header
	 * @param text  -> Text to be used in the header line
	 */
	public GridHeader(ThemeType theme, String text) {
		super(theme, GridConstants.GRID_HEADER);
		getElement().setText(text);
	}
}