package tec.bases2.minigoogle;

import tec.bases2.webcrawler.WebCrawler;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebCrawler webCrawler = new WebCrawler();
		webCrawler.getPageLinks("http://www.mkyong.com/");
		System.out.println("Hola mundo");

	}

}
