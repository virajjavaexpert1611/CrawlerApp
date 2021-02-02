package com.exercise.webcrawler.app;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
	private static final String WEBSITE_URL = "https://wiprodigital.com";
	private long linkCount = 0L;
	private HashSet<String> links;

	public App() {
		links = new HashSet<String>();
	}

	public static void main(String[] args) {
		new App().getPageLinks(WEBSITE_URL);
		System.out.println("TOTAL PAGES===" + new App().linkCount);
	}

	public void getPageLinks(String URL) {
		// 4. Check if you have already crawled the URLs
		// (we are intentionally not checking for duplicate content in this example)
		if (!links.contains(URL)) {
			try {
				linkCount = linkCount + 1;
				// 2. Fetch the HTML code
				Document document = Jsoup.connect(URL).get();

				String title = document.title();
				if (links.add(URL)) {
					System.out.println("Page Link " + linkCount + " : " + title + " : " + URL);
				}

				// 3. Parse the HTML to extract links to other URLs
				Elements linksOnPage = document.select("a[href]");

				Elements images = document.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

				System.out.println("	Images LINKS : ");
				for (Element image : images) {
					System.out.println("	 - " + image.attr("src"));
				}

				HashSet<String> inLinks = new HashSet<String>();

				System.out.println("	External LINKS : ");
				for (Element page : linksOnPage) {
					String link = page.attr("abs:href");
					if (link.contains(WEBSITE_URL))
						inLinks.add(link);
					else
						System.out.println("	 - " + link);
				}

				for (String link : inLinks) {
					getPageLinks(link);
				}
			} catch (IOException e) {
				System.err.println("Error: Invalid url for the website as website not found");
			}
		}
	}
}