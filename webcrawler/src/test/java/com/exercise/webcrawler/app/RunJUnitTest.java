package com.exercise.webcrawler.app;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunJUnitTest {
	public static void runAllClasses() {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));

		Result result = junit.run(CrawlerApplicationTest.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		resultReport(result);
	}
	
	public static void resultReport(Result result) {
		System.out.println(
				"Finished. Result: Failures: " + result.getFailureCount() + ". Ignored: " + result.getIgnoreCount()
						+ ". Tests run: " + result.getRunCount() + ". Time: " + result.getRunTime() + "ms.");
	}
	
	public static void main(String[] args) {
		runAllClasses();
	}
}