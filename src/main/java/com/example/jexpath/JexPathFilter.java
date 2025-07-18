package com.example.jexpath;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JexPathFilter {

    public static String transformToJsonPathFilter(String jex, String rootPath) {
        String expr = jex;

        // Normalize logical operators
        expr = expr.replaceAll("(?i)\\sand\\s", " && ");
        expr = expr.replaceAll("(?i)\\sor\\s", " || ");

        // Convert pipe-separated values: field=="A|B|C"
        Pattern pipePattern = Pattern.compile("(\\w+)\\s*==\\s*\"([^\"]*\\|[^\"]*)\"");
        Matcher matcher = pipePattern.matcher(expr);
        while (matcher.find()) {
            String field = matcher.group(1);
            String[] values = matcher.group(2).split("\\|");
            StringBuilder replacement = new StringBuilder("(");
            for (int i = 0; i < values.length; i++) {
                replacement.append(String.format("@.%s==\"%s\"", field, values[i]));
                if (i < values.length - 1) replacement.append(" || ");
            }
            replacement.append(")");
            expr = expr.replace(matcher.group(0), replacement.toString());
        }

        // Add @. prefix to field references for remaining simple comparisons
        // This needs to be done after pipe processing to avoid conflicts
        expr = expr.replaceAll("(?<!@\\.)\\b(\\w+)\\s*==\\s*\"([^\"]+)\"", "@.$1==\"$2\"");

        return String.format("%s[?(%s)]", rootPath, expr);
    }

    public static List<Object> filter(String json, String jexFilter, String rootPath) {
        String path = transformToJsonPathFilter(jexFilter, rootPath);
        ReadContext ctx = JsonPath.parse(json);
        return ctx.read(path);
    }
}
