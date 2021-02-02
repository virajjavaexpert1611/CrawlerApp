package com.exercise.webcrawler.app;

import org.junit.Test;

/**
 * Unit test for CrawlerApplicationTest
 */
public class CrawlerApplicationTest 
{
	App crawlerApplication = new App();
    
	/*
	 * Check for the valid URL
	 * 
	 * */
	
    @Test
    public void getPageLinksTest()
    {
        crawlerApplication.getPageLinks("https://wiprodigital.com");
    }
    
    /*
	 * Check for the InValid URL
	 * 
	 * */
    
    @Test
    public void getPageLinksExceptionTest()
    {
        crawlerApplication.getPageLinks("https://wiprodigital.co");
    }
}