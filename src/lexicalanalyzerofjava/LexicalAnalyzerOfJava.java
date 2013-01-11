/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzerofjava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2-nd version final version
 *
 * @author I
 */
public class LexicalAnalyzerOfJava {

    private final static String INPUT_FILE = "inputfile.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Analyzer.makeLexicalAnalizeOfJavaSourse(new FileReader(INPUT_FILE));
    }   

    public static class Analyzer {

        private static StringBuilder text;
        private static List<String> tokens;
        private final static List<String> KEYWORDS = getKeyWords();
        private final static String KEYWORDSFILE = "keywords.txt";

        public static StringBuilder readTextFromReader(Reader reader) {
            StringBuilder text = null;
            BufferedReader myreader = null;
            try {
                myreader = new BufferedReader(reader);
                text = new StringBuilder();
                String str;
                while ((str = myreader.readLine()) != null) {
                    text.append(str + "\n");
                }
                System.out.println("Object has been created succesfully");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            return text;
        }

        public static void makeLexicalAnalizeOfJavaSourse(Reader reader) {
            text = readTextFromReader(reader);
            tokens = getTokens();
            analyzeTokens();
        }

        private static void analyzeTokens() {
            tokens = getTokens();
            System.out.println("Lexical tokens:");
            for (int i = 0; i < tokens.size(); i++) {
                System.out.println(identifyToken(tokens.get(i)) + ":" + tokens.get(i));
            }

        }

        private static List<String> getTokens() {
            String regexp = Regexpresions.LEXEMS;
            Pattern p = Pattern.compile(regexp);
            Matcher m = p.matcher(text);


            List<String> list = new ArrayList<>();
            while (m.find()) {
                if (m.group().length() != 0) {
                    list.add(m.group().trim());

                }
            }
            return list;
        }

        private static boolean containsKeyWord(String str) {
            for (int i = 0; i < KEYWORDS.size(); i++) {
                String s = KEYWORDS.get(i).trim();
                if (s.equals(str)) {
                    return true;
                }
            }
            return false;
        }

        private static List<String> getKeyWords() {
            BufferedReader reader = null;
            List<String> set = null;
            try {
                reader = new BufferedReader(new FileReader(KEYWORDSFILE));
                set = new ArrayList<String>();
                String str = null;
                while ((str = reader.readLine()) != null) {
                    set.add(str);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage() + "\nKeyWirds haven't been initialized");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            return set;
        }

        public static String identifyToken(String str) {
            char[] ar = str.toCharArray();
            //
            if (ar.length >= 2) {
                if (ar[0] == '/' && ar[1] == '/') {
                    return "COMMENT";
                };
                if (((ar[0] == '\'') && (ar[ar.length - 1] == '\'')) || ((ar[0] == '\"') && (ar[ar.length - 1] == '\"'))) {
                    return "STRINGLITERAL";
                };
                if (ar.length >= 4) {
                    if ((ar[0] == '/') && (ar[1] == '*') && (ar[ar.length - 2] == '*') && (ar[ar.length - 1] == '/')) {
                        return "COMMENT";
                    }
                }
            }
            //checking on NUMBERS
            Matcher m = Pattern.compile(Regexpresions.NUMBERS + "|" + Regexpresions.NUMBERS2).matcher(str);
            if (m.find()) {
                return "NUMBER";
            }
            //checking on INDIFICATORS
            m = Pattern.compile(Regexpresions.INDIFICATORS).matcher(str);
            if (m.find()) {
                if (containsKeyWord(str)) {
                    return "KEYWORD";
                } else {
                    return "INDIFICATOR";
                }
            }
            //checking on SEPARATORS
            m = Pattern.compile(Regexpresions.SEPARATORS).matcher(str);
            if (m.find()) {
                return "SEPARATOR";
            }
            //checking on OPERATORS
            m = Pattern.compile(Regexpresions.OPERATORS).matcher(str);
            if (m.find()) {
                return "OPERATOR";
            }
            //if UFO happened
            return "UNKNOWN TOKEN";
        }
    }
}
