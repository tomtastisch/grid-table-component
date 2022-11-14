package grid;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.apache.commons.collections4.CollectionUtils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;

import grid.enums.ThemeType;
import grid.inner.comp.GridCell;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Container, which contains the information of all elements together and
 * displays them as a whole element.
 */
@Getter
@Tag(value = "div")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class GridContainerElement extends GridComponent implements HasComponents {

	@Serial
	private static final long serialVersionUID = -6196787319549901666L;

	private final transient List<Component> components = new ArrayList<>();

	protected GridContainerElement(ThemeType theme, String... classNames) {
		super(theme, classNames);
	}

	protected void setComponents(Component... components) {
		setContent(Arrays.stream(components).toList(), true);
		HasComponents.super.add(getComponents());
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
		setContent(Arrays.asList(components), false);
		HasComponents.super.add(getComponents());
	}

	/**
	 * Add or replace the content with the passed collection.
	 * 
	 * @param <T>   -> Abstraction type of the content components of this element
	 * @param comps -> Collection, which contains the new content
	 * @param clean -> Truth value indicating whether the passed collection should
	 *              overwrite [<code>TRUE</code>] the current content or be added to
	 *              the current collection [<code>FALSE</code>].
	 * 
	 * @see CollectionUtils#union(Iterable, Iterable)
	 */
	protected <T extends Component> void setContent(@NonNull Iterable<T> comps, boolean clean) {
		
		if(clean) {
			this.getElement().removeAllChildren();
			this.components.clear();
		}
		
		StreamSupport.stream(Spliterators.spliteratorUnknownSize(comps.iterator(), Spliterator.ORDERED), false)
				.forEach(e -> components.add(components.size(), e));
	}
}
