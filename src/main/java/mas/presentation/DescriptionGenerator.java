package mas.presentation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DescriptionGenerator {
    private static int minLatinChar = 97;
    private static int maxLatinChar = 122;

    private static int minSyllables = 1;
    private static int maxSyllables = 4;

    private static int minSentenceLength = 15;
    private static int maxSentenceLength = 90;

    private static Character[] vowels = {'a', 'e', 'i', 'o', 'u'};
    private static Character[] excludedVowels = {'y'};
    private static Character[] consonants = generateConsonantsArray();

    private static Character[] generateConsonantsArray() {
        List<Character> vowelsList = Arrays.asList(vowels);
        List<Character> excludedList = Arrays.asList(excludedVowels);
        List<Character> consonants = new LinkedList<>();

        for (int c = minLatinChar; c <= maxLatinChar; c++) {
            Character character = (char) c;

            if (!vowelsList.contains(character) && !excludedList.contains(character)) {
                consonants.add(character);
            }
        }

        return consonants.toArray(new Character[0]);
    }

    private static void appendVowel(StringBuilder sb) {
        int v = generateRandomInt(0, vowels.length - 1);
        sb.append(vowels[v]);
    }

    private static void appendConsonant(StringBuilder sb) {
        int c = generateRandomInt(0, consonants.length - 1);
        sb.append(consonants[c]);
    }

    private static void appendSyllable(StringBuilder sb) {
        appendConsonant(sb);
        appendVowel(sb);

        if (generateAsymmetricBoolean()) {
            appendConsonant(sb);
        }
    }

    private static int generateRandomInt(int min, int max) {
        return (int) (min + Math.round(Math.random() * (max - min)));
    }

    private static boolean generateAsymmetricBoolean() {
        int n = (int) (Math.random() * 5);
        return n == 0;
    }

    private static void appendWord(StringBuilder sb) {
        if (generateAsymmetricBoolean()) {
            appendVowel(sb);
        }

        int syllables = generateRandomInt(minSyllables, maxSyllables);

        for (int s = 0; s < syllables; s++) {
            appendSyllable(sb);
        }
    }

    private static void appendSentence(StringBuilder sb, int length) {
        StringBuilder sentenceSb = new StringBuilder();

        while (sentenceSb.length() < length) {
            appendWord(sentenceSb);

            if (generateAsymmetricBoolean()) {
                sentenceSb.append(',');
            }

            sentenceSb.append(' ');
        }

        sentenceSb.setCharAt(0, capitalizeCharacter(sentenceSb.charAt(0)));
        sentenceSb.setLength(sentenceSb.length() - 1);

        int lastCharIndex = sentenceSb.length() - 1;

        if (sentenceSb.charAt(lastCharIndex) == ',') {
            sentenceSb.setCharAt(lastCharIndex, generateAsymmetricBoolean() ? '?' : '.');
        } else {
            sentenceSb.append(generateAsymmetricBoolean() ? '?' : '.');
        }

        sb.append(sentenceSb);
    }

    private static Character capitalizeCharacter(Character c) {
        if (c < 'a') {
            return c;
        }

        return (char) (c - ' ');
    }

    public static String generateText(int length) {
        StringBuilder sb = new StringBuilder();

        while (sb.length() < length) {
            int localMinSentenceLength = Math.min(length - sb.length(), minSentenceLength);
            int localMaxSentenceLength = Math.min(length - sb.length(), maxSentenceLength);

            int sentenceLength = generateRandomInt(localMinSentenceLength, localMaxSentenceLength);
            appendSentence(sb, sentenceLength);
            sb.append(' ');
        }

        return sb.toString().trim();
    }
}