package com.cg.ppa.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CategoryTestUsingJUnit4.class, NewsTestsUsingJUnit4.class, PaperTestsUsingJUnit4.class,
		UserTestsUsingJUnit4.class })
public class AllJUnit4Tests {

}
