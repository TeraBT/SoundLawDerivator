package Parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XMLParser {
    public static String parse(String pathName) throws IOException {
        Path filePath = Path.of(pathName);
        String fileContent = Files.readString(filePath);
        Document xmlDocument = Jsoup.parse(fileContent, Parser.xmlParser());
        return xmlDocument.select("p").text();
    }
}
