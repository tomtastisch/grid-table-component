package grid;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.dom.Element;

import grid.enums.Index;
import grid.enums.ThemeType;

/**
 * Component used for the content of the {@link GridContainer} or the
 * {@link GridContainer} itself.
 */
public abstract class GridComponent extends Component implements HasStyle {

	@Serial
	private static final long serialVersionUID = -4342425409057805230L;

	protected ThemeType theme;

	private transient Set<Component> components = new HashSet<>();

	protected GridComponent(ThemeType theme) {
		this.setTheme(theme);
	}

	protected GridComponent(String... classNames) {
		this.addClassNames(classNames);
	}

	protected GridComponent(ThemeType theme, String... classNames) {
		this.setTheme(theme);
		this.addClassNames(classNames);
	}

	public Element setTheme(ThemeType theme) {
		this.theme = theme;
		return this.getElement().setAttribute(ThemeType.ATTRIBUTE.name(), theme.getTheme());
	}

	/**
	 * Determines the index of this element
	 * 
	 * @param index -> index type of this element
	 * @return the index of the given {@link Index Index type}
	 * 
	 * @see Index
	 */
	protected int getIndex(Index index) {
		return index.from(this);
	}

	public Collection<Component> getComponents() {
		return components;
	}

	/**
	 * Creates a formatted list of all contained components and transfers them to
	 * the passed class
	 * 
	 * @param <T> -> Type of the formatting class
	 * @param cls -> The formatting class
	 * @return a list of the elements included
	 */
	protected List<GridComponent> getInnerComponents(Class<GridComponent> cls) {
		return components.stream().map(cls::cast).toList();
	}

	protected <T extends Component> void setComponents(Iterable<T> c, boolean clean) {
		setComponents(c, null, clean);
	}

	/**
	 * Add or replace the content with the passed collection.
	 * 
	 * @param <T>        -> Abstraction type of the content components of this
	 *                   element
	 * @param c          -> Collection, which contains the new content
	 * @param comparator -> xx
	 * @param clean      -> Truth value indicating whether the passed collection
	 *                   should overwrite [<code>TRUE</code>] the current content or
	 *                   be added to the current collection [<code>FALSE</code>].
	 * 
	 * @see CollectionUtils#union(Iterable, Iterable)
	 */
	protected <T extends Component> void setComponents(Iterable<T> c, Comparator<Component> comparator, boolean clean) {
		components = CollectionUtils
				.union(components.stream().filter(e -> !clean).toList(),
						Objects.requireNonNullElse(c, new ArrayList<>()))
				.stream().sorted(Objects.nonNull(comparator) ? comparator : (o1, o2) -> 0)
				.collect(Collectors.toCollection(HashSet::new));
	}

	/**
	 * Creates a string containing the information of the theme. It returns the
	 * corresponding stored information as CSS string.
	 * 
	 * @return CSS- String of the deposited theme
	 * @see ThemeType#getTheme()
	 */
	public String getTheme() {
		return this.theme.getTheme();
	}

	/**
	 * Used to create an HTML code of the current element and its content
	 * 
	 * @return the HTML code
	 */
	public Html toHtml() {
		return new Html(this.toString());
	}

	@Override
	public String toString() {
		return this.getElement().getOuterHTML();
	}
}
