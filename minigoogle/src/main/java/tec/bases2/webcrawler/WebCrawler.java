package tec.bases2.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WebCrawler {
	private HashSet<String> links;
	
	public WebCrawler() {
        links = new HashSet<String>();
    }
	
	public void getPageLinks(String URL) {
        if (!links.contains(URL)) {
            try {
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a");

                for (Element page : linksOnPage) {
                	System.out.println(page.text());
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
}
