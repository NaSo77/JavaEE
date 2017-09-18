package homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeSet;

public class WarAndPease {

    public static String relativePath = "src" + File.separator + "homework";
    public static String fileName = "war_and_peace.txt";

    public static void main(String[] args) {
	File book = new File(relativePath, fileName);

	longestWord(book);
	wordsToFiles(book);
	mostFrequentWord(book);
	warOrPease(book);

	totalWords(book);
	totalLines(book);
	totalSymbols(book);
	totalCommas(book);

	printbook(book);
    }

    private static void printbook(File book) {
	Scanner scanner = getScanner(book);
	if (scanner == null) {
	    System.out.println("Scanner is null");
	    return;
	}

	while (scanner.hasNextLine()) {
	    System.out.println(scanner.nextLine());

	}
	scanner.close();
    }

    private static void totalCommas(File book) {
	Scanner scanner = getScanner(book);
	int counter = 0;
	while (scanner.hasNextLine()) {
	    char[] line = scanner.nextLine().toCharArray();
	    for (char c : line) {
		if (c == ',') {
		    counter++;
		}
	    }
	}
	scanner.close();
	System.out.println("Total commas: " + counter);
    }

    private static void totalSymbols(File book) {
	Scanner scanner = getScanner(book);
	int counter = 0;
	while (scanner.hasNextLine()) {
	    counter += scanner.nextLine().length();
	}
	scanner.close();
	System.out.println("Total symbols: " + counter);

    }

    private static void totalLines(File book) {
	Scanner scanner = getScanner(book);
	int counter = 0;
	while (scanner.hasNextLine()) {
	    scanner.nextLine();
	    counter++;
	}
	scanner.close();
	System.out.println("Total lines: " + counter);
    }

    public static void totalWords(File book) {
	System.out.println("Total words: " + getAllWords(book).size());
    }

    private static ArrayList<String> getAllWords(File book) {
	if (!book.exists()) {
	    System.out.println("File does not exist");
	}

	ArrayList<String> allWords = new ArrayList<>();
	Scanner scanner = getScanner(book);
	while (scanner.hasNext()) {
	    String[] line = scanner.nextLine().split("[\\s+\\-*_*?*!*,*.*:*;*_*$*\"*'*«*»*)*(*¤*є*]");
	    for (String word : line) {
		if (word.length() > 0) {
		    allWords.add(word);
		}
	    }
	}
	scanner.close();

	return allWords;
    }

    private static PrintStream getPrintStream(File file) {
	if (!file.exists()) {
	    try {
		file.createNewFile();
	    } catch (IOException e) {
		System.out.println("Cannot create new file");
		System.out.println(e.getMessage());
	    }
	}
	PrintStream printStream = null;
	try {
	    printStream = new PrintStream(file);
	} catch (FileNotFoundException e2) {
	    e2.printStackTrace();
	}
	return printStream;
    }

    private static Scanner getScanner(File file) {
	Scanner scanner = null;
	try {
	    scanner = new Scanner(file, "UTF-8");
	} catch (FileNotFoundException e) {
	    System.out.println("File not found");
	}
	return scanner;
    }

    private static HashMap<String, Integer> convertArrayListToHashMap(ArrayList<String> allWords) {
	HashMap<String, Integer> wordsByRepetitions = new HashMap<>();
	for (String string : allWords) {
	    if (wordsByRepetitions.containsKey(string)) {
		int count = wordsByRepetitions.get(string);
		wordsByRepetitions.put(string, count + 1);
	    } else {
		wordsByRepetitions.put(string, 1);
	    }
	}
	return wordsByRepetitions;
    }

    public static void longestWord(File book) {
	ArrayList<String> words = getAllWords(book);
	String longestWord = "";
	for (String string : words) {
	    if (string.length() > longestWord.length()) {
		longestWord = string;
	    }
	}
	System.out.println("Longest word is: " + longestWord);
    }

    public static void mostFrequentWord(File book) {
	ArrayList<String> words = getAllWords(book);
	HashMap<String, Integer> wordsByRepetitions = convertArrayListToHashMap(words);

	int maxCount = 0;
	String mostFrequantWord = "";

	for (Entry<String, Integer> entry : wordsByRepetitions.entrySet()) {
	    String currentWord = entry.getKey();
	    int currentCount = entry.getValue();

	    if (currentCount > maxCount) {
		maxCount = currentCount;
		mostFrequantWord = currentWord;
	    }
	}

	System.out.println("most frequant word is: " + mostFrequantWord + " - " + maxCount + " times");
    }

    public static void warOrPease(File book) {
	ArrayList<String> words = getAllWords(book);

	int warCount = 0;
	int peaceCount = 0;
	for (String string : words) {
	    if (string.equals("�����")) {
		warCount++;
	    }
	    if (string.equals("���")) {
		peaceCount++;
	    }
	}

	System.out.println("war: " + warCount);
	System.out.println("peace: " + peaceCount);

    }

    public static void wordsToFiles(File book) {
	ArrayList<String> words = getAllWords(book);
	TreeSet<String> wordsByLength = new TreeSet<>(new Comparator<String>() {

	    @Override
	    public int compare(String o1, String o2) {
		if (o1.equals(o2)) {
		    return 0;
		}
		if (o1.length() > o2.length()) {
		    return 1;
		}
		if (o1.length() < o2.length()) {
		    return -1;
		} else {
		    return 1;
		}
	    }
	});

	wordsByLength.addAll(words);

	File folder = new File(relativePath, "wordsBySymbols");
	if (!folder.exists()) {
	    folder.mkdir();
	} else {
	    for (File f : folder.listFiles()) {
		f.delete();
	    }
	}

	int previousLength = 1;
	File file = new File(folder, previousLength + "_symbol.txt");
	PrintStream printStream = getPrintStream(file);

	HashSet<String> wordsByLength1 = new HashSet<>();
	for (String word : wordsByLength) {

	    if (word.length() != previousLength) {

		for (String string : wordsByLength1) {
		    printStream.println(string);
		}
		wordsByLength1 = new HashSet<>();
		printStream.close();
		previousLength = word.length();

		file = new File(folder, word.length() + "_symbol.txt");
		printStream = getPrintStream(file);
	    }
	    wordsByLength1.add(word);
	}

    }

}
