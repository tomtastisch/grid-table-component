package grid.inner.comp.wrapper;

import java.io.Serial;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;

import grid.GridComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Tag(value = "div")
@EqualsAndHashCode(callSuper = true)
public class GridWrapper extends GridComponent {

	@Serial
	private static final long serialVersionUID = 6995937734763509660L;
	
	private final Component component;
}
