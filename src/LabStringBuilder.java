import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LabStringBuilder {
    private final StringBuilder stringBuilder;

    public LabStringBuilder(String source) {
        this.stringBuilder = new StringBuilder(source);
    }

    public ArrayList<LabStringBuilder> split(String separator) {
        ArrayList<LabStringBuilder> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(separator);
        Matcher matcher = pattern.matcher(stringBuilder);

        int pos = 0;

        while (matcher.find()) {
            String sentence = stringBuilder.substring(pos, matcher.start());
            pos = matcher.end();
            result.add(new LabStringBuilder(sentence));
        }

        String lastItem = stringBuilder.substring(pos);

        if (!lastItem.isEmpty()) {
            result.add(new LabStringBuilder(lastItem));
        }

        return result;
    }

    public int indexOf(String substring) {
        return this.stringBuilder.indexOf(substring);
    }

    public String toString() {
        return this.stringBuilder.toString();
    }
}
