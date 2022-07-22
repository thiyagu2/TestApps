package com.moduleone;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryTestRunner {
@Test
@Factory
public Object[] RunTest(){
	return new Object[]{new LoginPage(), new SampleDataProviderTest()};	
}
}
