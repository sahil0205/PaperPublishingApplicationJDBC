package com.cg.ppa.tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({CategoryTests.class,NewsTests.class,PaperTests.class,UserTest.class})
public class AllJUnit5Tests {

}
