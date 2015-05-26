package net.azae.kata;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

public class WrapBench {
	private List<String> allLines;
	{
		URI fileUri;
		try {
			fileUri = ClassLoader.getSystemResource("cendrillon.txt").toURI();
			allLines = Files.readAllLines(Paths.get(fileUri), Charset.defaultCharset());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_wrapRecursive() throws IOException, URISyntaxException {
        long startTime = System.nanoTime();
        WrapRecursive wrapRecursive = new WrapRecursive();
        for (String line : allLines) {
			wrapRecursive.wrapLine(line, 80);
		}
        long endTime = System.nanoTime();
        long elapse = (endTime - startTime) / 1000 ;
        System.out.println("wrapRecursive : " + elapse);
        
	}

	@Test 
	public void test_wrapTailRecursive() {

		long startTime = System.nanoTime();
        WrapTailRecursive wrapTailRecursive = new WrapTailRecursive();
        for (String line : allLines) {
			wrapTailRecursive.wrapLine(line, 80);
		}
        long endTime = System.nanoTime();
        long elapse = (endTime - startTime) / 1000 ;
        System.out.println("wrapTailRecursive : " + elapse);
	}
	
	@Test 
	public void test_wrapLoop() {

		long startTime = System.nanoTime();
        WrapLoop wrapTailRecursive = new WrapLoop();
        for (String line : allLines) {
			wrapTailRecursive.wrapLine(line, 80);
		}
        long endTime = System.nanoTime();
        long elapse = (endTime - startTime) / 1000 ;
        System.out.println("wrapLoop : " + elapse);
	}
}
