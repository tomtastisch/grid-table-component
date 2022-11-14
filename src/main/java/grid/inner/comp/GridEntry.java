package grid.inner.comp;

import java.io.Serial;

import com.vaadin.flow.component.Tag;

import grid.GridComponent;
import grid.constants.GridConstants;
import grid.enums.ThemeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
 * Basis of entry within a {@link GridCell cell} 
 */
@Data
@Tag(value = "div")
@EqualsAndHashCode(callSuper = false)
class GridEntry extends GridComponent {
	
	@Serial
	private static final long serialVersionUID = 8409550102268852050L;
	
	private final String name;
	
	public GridEntry(String text) {
		this(ThemeType.NEUTRAL, text);
	}

	GridEntry(ThemeType theme, String text) {
		super(theme, GridConstants.GRID_ENTRY);
		this.name = text;
		this.getElement().setText(text);
	}
}
