package grid.constants;

import grid.enums.ThemeType;

public record GridConstants() {
	public static final String GRID_CONTAINER = "grid-container";
	public static final String GRID_HEADER = "grid-header";
	public static final String GRID_TABLE = "grid-table";
	public static final String GRID_CELL = "grid-item";
	
	public static final String COLUMNS_OPTION = "grid-template-columns";
	public static final String OPTION_AUTO = "auto";
	
	public static final ThemeType DEFAULT_THEME = ThemeType.NEUTRAL;
	public static final int DEFAULT_SIZE = 1;
}
