import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static final String example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Duis ullamcorper facilisis enim, vel bibendum leo scelerisque ut. " +
            "Nunc ac massa ac nulla sagittis pharetra id eget ipsum. " +
            "In hac habitasse platea dictumst. " +
            "Sed eget bibendum ligula. " +
            "Aliquam ultricies sed odio ut porta. " +
            "Aenean diam justo, hendrerit auctor nulla id, molestie mattis metus. " +
            "Quisque eu nisl vitae magna laoreet elementum. " +
            "Nulla cursus, arcu nec dapibus gravida, turpis sem feugiat est, non laoreet sapien odio quis mauris. " +
            "In vel neque at justo auctor vulputate. " +
            "Praesent malesuada urna sed erat eleifend, eget faucibus enim egestas. " +
            "Phasellus interdum a tellus a posuere. " +
            "Maecenas quis lectus sit amet lectus elementum blandit.";
    static final String WORDS_SEPARATOR = ",?\\s+";
    static final String SENTENCE_SEPARATOR = "[.!?]\\s*";

    public static void main(String[] args) throws Exception {
        StringBuilder source = new StringBuilder(example);

        ArrayList<StringBuilder> sentences = Main.split(source, Main.SENTENCE_SEPARATOR);

        StringBuilder firstSentence = sentences.get(0);
        Main.validateText(sentences);
        Main.validateSentence(firstSentence);

        String[] result = Main.findWords(firstSentence, sentences);

        if (result.length > 0) {
            System.out.printf("result - %s\n", Arrays.toString(result));
        } else {
            System.out.println("0 words found");
        }
    }

    public static String[] findWords(StringBuilder base, ArrayList<StringBuilder> sentences) {
        ArrayList<String> result = new ArrayList<>();

        ArrayList<StringBuilder> words = Main.split(base, Main.WORDS_SEPARATOR);

        for (StringBuilder word : words) {
            Object[] matches = sentences.stream().filter(sentence -> sentence.indexOf(word.toString()) != -1).toArray();

            if (matches.length == 1) {
                result.add(word.toString());
            }
        }

        return result.toArray(new String[0]);
    }

    public static void validateText(ArrayList<StringBuilder> sentences) throws Exception {
        Main.checkLength(sentences, 2, "Not enough text");
    }

    public static void validateSentence(StringBuilder sentence) throws Exception {
        ArrayList<StringBuilder> words = Main.split(sentence, Main.WORDS_SEPARATOR);
        Main.checkLength(words, 1, "First sentence should contain at least 1 word");
    }

    public static void checkLength(ArrayList<StringBuilder> entities, int length, String errorMessage) throws Exception {
        if (entities.size() < length) {
            throw new Exception(errorMessage);
        }
    }

    public static ArrayList<StringBuilder> split(StringBuilder str, String separator) {
        ArrayList<StringBuilder> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(separator);
        Matcher matcher = pattern.matcher(str);

        int pos = 0;

        while (matcher.find()) {
            String sentence = str.substring(pos, matcher.start());
            pos = matcher.end();
            result.add(new StringBuilder(sentence));
        }

        String lastItem = str.substring(pos);
        result.add(new StringBuilder(lastItem));

        return result;
    }
}