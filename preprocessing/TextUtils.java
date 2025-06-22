package explainability.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    public static String cleanExtractedText(String text) {
        // Remove non-printable characters, including <0x0e> and <0x0f>
        text = text.replaceAll("[\\x00-\\x1F\\x7F-\\x9F]", "");

        // Remove all quotation marks
        text = text.replace("\"", "").replace("'", "");

        text = text.replaceAll("\\s+", " ");
        text = text.replaceAll("\\b[0-9a-zA-Z]\\b", "");
        text = text.replaceAll("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b", "");
        text = text.replaceAll("https?://\\S+\\b", "");

        // Replace "", " ", "" with appropriate characters
        text = text.replace("", "t").replace(" ", " ").replace("", "");

        text = text.replaceAll("\\b\\w{2}\\b", "");

        // Replace hyphenated words at the end of a line with the concatenated word
        text = text.replaceAll("-\\s+(\\w+)", "$1");

        // Sanitize newlines and tabs, replacing them with a single space
        text = text.replaceAll("\\r?\\n|\\t", " ");

// Remove multiple spaces (you already have a version of this, but this one is more robust)
        text = text.replaceAll("\\s+", " ");

// Convert everything to lowercase
        text = text.toLowerCase();

// Remove isolated numbers
        text = text.replaceAll("\\b\\d+\\b", "");

// Remove very long words (more than 30 characters, adjust as needed)
        text = text.replaceAll("\\b\\w{30,}\\b", "");

        // Trim the sanitized text
        text = text.trim();

        text = text.replaceAll("\\b(html|body|head|title|script|style|div|span|table|tr|td|th|p|img|a|h1|h2|h3|h4|h5|h6|ul|ol|li|b|i|u|em|strong|br|hr|quot|amp|lt|gt|nbsp|punc|iframe|frame|object|embed|param|meta|link|script|noscript|style|area|base|basefont|center|dir|font|isindex|menu|s|strike|u|wbr|xmp|xml|DOCTYPE)\\b", "");

        // Remove everything after the last occurrence of the word "references"
        int referencesIndex = text.toLowerCase().lastIndexOf("references");
        if (referencesIndex != -1) {
            text = text.substring(0, referencesIndex);
        }

        text = text.replaceAll("<[^>]*>", "");

        Pattern pattern = Pattern.compile("\\b[a-zA-Z0-9'-]+\\b", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(text);
        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group()).append(" ");
        }

        // Remove multiple spaces and trim
        return result.toString().replaceAll("\\s+", " ").trim();



    }
}
