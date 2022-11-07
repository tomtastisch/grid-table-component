package grid.enums;

import grid.enums.attr.AttributeType;

/** Summarizing enumeration of all possible themes and their typification */
public enum ThemeType implements AttributeType {

	SUCCESS("success"), NEUTRAL(""), ERROR("error");

	/* Publicly available variable for the representation of the attribute */
	public static final Attribute ATTRIBUTE = new Attribute("theme");

	private final String origin = "badge %s";
	private final String theme;

	/**
	 * @param name -> name of this theme type
	 */
	ThemeType(String name) {
		// Merging of the individual strings to a usable string and subsequent removal
		// of leading and trailing blanks, which occur e.g. with a neutral result
		this.theme = String.format(origin, name).strip();
	}

	public String getTheme() {
		return this.theme;
	}
}