import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final String example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ullamcorper facilisis enim, vel bibendum leo scelerisque ut. Nunc ac massa ac nulla sagittis pharetra id eget ipsum. In hac habitasse platea dictumst. Sed eget bibendum ligula. Aliquam ultricies sed odio ut porta. Aenean diam justo, hendrerit auctor nulla id, molestie mattis metus. Quisque eu nisl vitae magna laoreet elementum. Nulla cursus, arcu nec dapibus gravida, turpis sem feugiat est, non laoreet sapien odio quis mauris. In vel neque at justo auctor vulputate. Praesent malesuada urna sed erat eleifend, eget faucibus enim egestas. Phasellus interdum a tellus a posuere. Maecenas quis lectus sit amet lectus elementum blandit.";

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder(example);

        String[] sentences = split(stringBuilder, "[\\.!?]\\s");

        String[] result = findWords(sentences);

        System.out.printf("result - %s\n", Arrays.toString(result));
    }

    public static String[] findWords(String[] sentences) {
        ArrayList<String> result = new ArrayList<String>();

        String firstSentence = sentences[0];
        String[] words = split(firstSentence, ",?\\s");

        for (String word : words) {
            Object[] matches = Arrays.stream(sentences).filter(sentence -> sentence.contains(word)).toArray();

            if (matches.length == 1) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }

    public static String[] split(StringBuilder source, String pattern) {
        return source.toString().split(pattern);
    }

    public static String[] split(String source, String pattern) {
        return source.split(pattern);
    }
}