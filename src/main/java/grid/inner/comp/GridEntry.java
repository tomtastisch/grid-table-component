package grid.inner.comp;

import java.io.Serial;

import com.vaadin.flow.component.Tag;

import grid.GridComponent;
import grid.enums.ThemeType;

/** Basis of entry within a {@link GridCell cell} */
@Tag(value = "div")
class GridEntry extends GridComponent {

	@Serial
	private static final long serialVersionUID = 8409550102268852050L;

	final String name;

	public GridEntry(final String name) {
		this(name, ThemeType.NEUTRAL);
	}

	GridEntry(String name, ThemeType theme) {
		super(theme);
		this.name = name;
		this.theme = theme;
		this.getElement().setText(name);
	}
}
