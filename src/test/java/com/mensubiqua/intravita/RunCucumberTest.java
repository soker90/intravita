package com.mensubiqua.intravita;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/java/com/mensubiqua/intravita/features")
public class RunCucumberTest {

}