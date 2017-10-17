//Scraper class, imports jaunt API
import com.jaunt.*;

public class Scraper {

	UserAgent userAgent;

	Scraper() {
		userAgent = new UserAgent();
	}

	void scrape(String siteToScrape) throws JauntException {
		// create new userAgent (headless browser)
		userAgent.settings.autoSaveAsHTML = true;
		userAgent.visit(siteToScrape); // visit google
		userAgent.doc.apply("butterflies"); // apply form input (starting at first editable field)
		userAgent.doc.submit("Google Search"); // click submit button labelled "Google Search"

		Elements links = userAgent.doc.findEvery("").findEvery("");
		// find search result links
		for (Element link : links)
			System.out.println(link.getAt("href")); // print results

	}

}
