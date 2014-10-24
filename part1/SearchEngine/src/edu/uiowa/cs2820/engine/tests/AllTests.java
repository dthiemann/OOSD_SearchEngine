package edu.uiowa.cs2820.engine.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DatabaseTest.class, FieldSearchTest.class, FieldTest.class, IndexerTest.class, IntegrationTests.class, OperatorTests.class })
public class AllTests
{

}
