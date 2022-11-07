package grid.inner.comp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

class GridTableTest {

	final Random randomnizer = new Random();
	final int count = 2; //Math.abs(randomnizer.nextInt(0, Byte.MAX_VALUE));

	int cols;

	@Test
	void testCreateGridTable() {
		final GridTable gTable = new GridTable(new String[count]);
		//System.out.println(gTable);
		
		assertEquals(count, gTable.getContainer().getElements().size());
		assertEquals(0, gTable.getContainer().getElements().size() % count);
	}
	
	@Test
	void testAddComponents() {
		final GridTable gTable = new GridTable("HEADER TEST", 3, new String[count]);
		
		final GridTable gInnerTable = new GridTable(new String[2]);
		
		gTable.getContainer().add(gInnerTable);
		
		System.out.println(gTable);
		System.out.println();
		
		//gTable.getContainer().getComponents().forEach(e -> System.out.println(e + "\n"));
	}
}