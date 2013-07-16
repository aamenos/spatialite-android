package com.spatialite.test.spatialite;

import jsqlite.Stmt;

import com.spatialite.test.utilities.DatabaseTestCase;

public class PerformanceTest extends DatabaseTestCase {
	private static final String TAG = "PerformanceTest";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		openExistingDatabase("test-2.3.d.sqlite");
	}

	@Override
	protected void tearDown() throws Exception {
		closeDatabase();
		deleteDatabase();
		super.tearDown();
	}

	public void testLargeQuery() throws Exception {
		Stmt stmt01 = db.prepare("SELECT name, peoples, County, HEX(Geometry) from Towns ORDER BY peoples DESC;");
		while (stmt01.step()) {
			// Do nothing
		}
		stmt01.close();
	}

	public void testLargeQueryIndex() throws Exception {
		// Create index
		Stmt stmt1 = db.prepare("select createspatialindex('Towns','Geometry')");
		if (stmt1.step()) {
			assertEquals(1, stmt1.column_int(0));
		} else {
			fail("couldn't create spatial index");
		}
		stmt1.close();

		// MATCH - prepare query
		Stmt stmt3 = db
				.prepare("SELECT PK_UID FROM Towns WHERE ROWID IN ( select pkid from idx_Towns_Geometry where pkid match RTreeDistWithin(675182.242021, 4679818.170099,400000))");
		while (stmt3.step()) {
			//assertEquals(11890, stmt3.column_int(0));
		}
		stmt3.close();
	}
}
