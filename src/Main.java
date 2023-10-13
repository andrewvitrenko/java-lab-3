import java.util.ArrayList;
import java.util.Arrays;

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

    public static void main(String[] args) {
        try {
            LabStringBuilder source = new LabStringBuilder(example);

            ArrayList<LabStringBuilder> sentences = source.split(Main.SENTENCE_SEPARATOR);

            Main.validateText(sentences);
            LabStringBuilder firstSentence = sentences.get(0);
            Main.validateSentence(firstSentence);

            String[] result = Main.findWords(firstSentence, sentences);

            if (result.length > 0) {
                System.out.printf("result - %s\n", Arrays.toString(result));
            } else {
                System.out.println("0 words found");
            }
        } catch (LabException e) {
            System.out.println("Lab exception - " + e.getMessage());
        }
    }

    public static String[] findWords(LabStringBuilder base, ArrayList<LabStringBuilder> sentences) {
        ArrayList<String> result = new ArrayList<>();

        ArrayList<LabStringBuilder> words = base.split(Main.WORDS_SEPARATOR);

        for (LabStringBuilder word : words) {
            Object[] matches = sentences.stream().filter(sentence -> sentence.indexOf(word.toString()) != -1).toArray();

            if (matches.length == 1) {
                result.add(word.toString());
            }
        }

        return result.toArray(new String[0]);
    }

    public static void validateText(ArrayList<LabStringBuilder> sentences) throws LabException {
        Main.checkLength(sentences, 2, "Not enough text");
    }

    public static void validateSentence(LabStringBuilder sentence) throws LabException {
        ArrayList<LabStringBuilder> words = sentence.split(Main.WORDS_SEPARATOR);
        Main.checkLength(words, 1, "First sentence should contain at least 1 word");
    }

    public static void checkLength(ArrayList<LabStringBuilder> entities, int length, String errorMessage) throws LabException {
        if (entities.size() < length) {
            throw new LabException(errorMessage);
        }
    }
}