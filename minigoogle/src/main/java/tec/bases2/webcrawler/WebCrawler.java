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
                // System.out.println("Document " + document);
                System.out.println(htmlTags(document));
                Elements linksOnPage = document.select("body");

                for (Element page : linksOnPage) {
                	System.out.println(page.text());
                    //getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
	
	private static String htmlTags(Document doc) {
        StringBuilder sb = new StringBuilder();
        htmlTags(doc.children(), sb);
        return sb.toString();
    }

    private static void htmlTags(Elements elements, StringBuilder sb) {
        for(Element el:elements) {
            // System.out.println("el #1: " + el.text().length());
            // System.out.println("el children size #1: " + el.childrenSize());
            if(el.text().length() > 0 && el.childrenSize() == 0) {
            	sb.append(el.nodeName());
            	sb.append(" / ");
            	sb.append(el.text());
            	sb.append(", ");
            }
            htmlTags(el.children(), sb);
        }
    }
}
