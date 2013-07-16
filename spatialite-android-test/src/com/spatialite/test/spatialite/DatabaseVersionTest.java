package com.spatialite.test.spatialite;

import com.spatialite.test.utilities.DatabaseTestCase;

import jsqlite.Stmt;

public class DatabaseVersionTest extends DatabaseTestCase {
	private static final String TAG = "DatabaseVersionTest";

	// Place version information here so that it can be changed easily when the
	// libraries change.
	private static final String SPATIALITE_VERSION = "4.0.0";
	private static final String PROJ4_VERSION = "Rel. 4.8.0, 6 March 2012";
	private static final String GEOS_VERSION = "3.3.6-CAPI-1.7.6";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		openNewDatabase("test.sqlite");
	}

	@Override
	protected void tearDown() throws Exception {
		closeDatabase();
		deleteDatabase();
		super.tearDown();
	}
	
	public void testVersions() throws Exception {
		Stmt stmt01 = db.prepare("SELECT spatialite_version();");
		if (stmt01.step()) {
			assertEquals(SPATIALITE_VERSION, stmt01.column_string(0));
		}

		stmt01 = db.prepare("SELECT proj4_version();");
		if (stmt01.step()) {
			assertEquals(PROJ4_VERSION, stmt01.column_string(0));
		}

		stmt01 = db.prepare("SELECT geos_version();");
		if (stmt01.step()) {
			assertEquals(GEOS_VERSION, stmt01.column_string(0));
		}
		stmt01.close();
	}
}
