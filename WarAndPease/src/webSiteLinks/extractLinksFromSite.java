package webSiteLinks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class extractLinksFromSite {

    // https://regex101.com/r/vM7wT6/392

    public static String regex = "[w]{3}[\\\\.][\\w]+[\\\\.][bg]+";

    public static void main(String[] args) throws IOException {

	String path = "src" + File.separator + "webSiteLinks" + File.separator + "www.gbg.bg";
	File folder = new File(path);

	TreeSet<String> links = new TreeSet<>();

	extractWebSites(folder, links);

	File result = new File("src" + File.separator + "webSiteLinks" + File.separator + "allWebsites.txt");
	PrintStream printStream = new PrintStream(result);
	for (String website : links) {
	    printStream.println(website);
	}
    }

    private static Scanner getScanner(File file) {
	Scanner scanner = null;
	try {
	    scanner = new Scanner(file);
	} catch (FileNotFoundException e) {
	    System.out.println("File not found");
	}
	return scanner;
    }

    private static void extractWebSites(File folder, TreeSet<String> links) {
	File[] files = folder.listFiles();

	for (File file : files) {
	    if (file.isFile()) {
		Scanner scanner = getScanner(file);
		StringBuilder text = new StringBuilder();
		while (scanner.hasNextLine()) {
		    text.append(scanner.nextLine());
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text.toString());
		while (matcher.find()) {
		    links.add(matcher.group());
		}
	    } else {
		extractWebSites(file, links);
	    }
	}
    }
}
