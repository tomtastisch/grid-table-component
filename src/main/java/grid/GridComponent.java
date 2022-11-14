package grid;

import java.io.Serial;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Html;

import grid.enums.ThemeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Component used for the content of the {@link OldGridContainer} or the
 * {@link OldGridContainer} itself.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class GridComponent extends Component implements HasStyle {

	@Serial
	private static final long serialVersionUID = -7844696104870054005L;
	
	private final ThemeType theme;

	protected GridComponent(ThemeType theme) {
		this(theme, "");
	}

	protected GridComponent(String... classNames) {
		this(ThemeType.NEUTRAL, classNames);
	}

	protected GridComponent(ThemeType theme, String... classNames) {
		this.theme = theme;
		this.addClassNames(classNames);
	}
	
	protected void set(String name, String value) {
		getElement().getStyle().set(name, value);
	}

	/**
	 * Used to create an HTML code of the current element and its content
	 * 
	 * @return the HTML code
	 */
	public Html toHtml() {
		return new Html(this.getElement().getOuterHTML());
	}
}
