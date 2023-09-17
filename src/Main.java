import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final String example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ullamcorper facilisis enim, vel bibendum leo scelerisque ut. Nunc ac massa ac nulla sagittis pharetra id eget ipsum. In hac habitasse platea dictumst. Sed eget bibendum ligula. Aliquam ultricies sed odio ut porta. Aenean diam justo, hendrerit auctor nulla id, molestie mattis metus. Quisque eu nisl vitae magna laoreet elementum. Nulla cursus, arcu nec dapibus gravida, turpis sem feugiat est, non laoreet sapien odio quis mauris. In vel neque at justo auctor vulputate. Praesent malesuada urna sed erat eleifend, eget faucibus enim egestas. Phasellus interdum a tellus a posuere. Maecenas quis lectus sit amet lectus elementum blandit.";
    static final String WORDS_SEPARATOR = ",?\\s";
    static final String SENTENCE_SEPARATOR = "[\\.!?]\\s";

    public static void main(String[] args) throws Exception {
        StringBuilder stringBuilder = new StringBuilder(example);

        String[] sentences = getSentences(stringBuilder, SENTENCE_SEPARATOR);

        validateText(sentences);
        validateSentence(sentences[0]);

        String[] result = findWords(sentences[0], sentences);

        if (result.length > 0) {
            System.out.printf("result - %s\n", Arrays.toString(result));
        } else {
            System.out.println("0 words found");
        }
    }

    public static String[] findWords(String base, String[] sentences) {
        ArrayList<String> result = new ArrayList<>();

        String[] words = getWords(base, WORDS_SEPARATOR);

        for (String word : words) {
            Object[] matches = Arrays.stream(sentences).filter(sentence -> sentence.contains(word)).toArray();

            if (matches.length == 1) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }

    public static String[] getSentences(StringBuilder source, String pattern) {
        return source.toString().split(pattern);
    }

    public static String[] getWords(String source, String pattern) {
        return source.split(pattern);
    }

    public static void validateText(String[] sentences) throws Exception {
        checkLength(sentences, 2, "Not enough text");
    }

    public static void validateSentence(String sentence) throws Exception {
        String[] words = sentence.split(WORDS_SEPARATOR);
        checkLength(words, 1, "First sentence should contain at least 1 word");
    }

    public static void checkLength(String[] entities, int length, String errorMessage) throws Exception {
        if (entities.length < length) {
            throw new Exception(errorMessage);
        }
    }
}