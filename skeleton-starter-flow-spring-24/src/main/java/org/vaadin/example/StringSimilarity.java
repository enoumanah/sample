package org.vaadin.example;

import org.apache.commons.codec.language.DoubleMetaphone;

public class StringSimilarity {

    private static final DoubleMetaphone doubleMetaphone = new DoubleMetaphone();

    public static int getSimilarityScore(String s1, String s2) {
        s1 = s1.toLowerCase().trim();
        s2 = s2.toLowerCase().trim();

        if (s1.equals(s2))
            return 100;

        int maxLen = Math.max(s1.length(), s2.length());
        if (maxLen == 0)
            return 100;

        int levDistance = levenshteinDistance(s1, s2);
        double spellingScore = (1.0 - ((double) levDistance / maxLen)) * 100;

        String dm1Primary = doubleMetaphone.doubleMetaphone(s1);
        String dm2Primary = doubleMetaphone.doubleMetaphone(s2);
        String dm1Alt = doubleMetaphone.doubleMetaphone(s1, true);
        String dm2Alt = doubleMetaphone.doubleMetaphone(s2, true);

        boolean primaryMatch = dm1Primary.equals(dm2Primary);
        boolean altMatch = dm1Alt.equals(dm2Alt);

        double phoneticScore = (primaryMatch || altMatch) ? 100 : 50;

        return (int) Math.round((spellingScore * 0.7) + (phoneticScore * 0.3));
    }

    public static void logScoreBreakdown(String s1, String s2) {
        s1 = s1.toLowerCase().trim();
        s2 = s2.toLowerCase().trim();

        int maxLen = Math.max(s1.length(), s2.length());
        if (maxLen == 0) {
            System.out.println("[" + s1 + "] vs [" + s2 + "] => Both empty.");
            return;
        }

        int levDistance = levenshteinDistance(s1, s2);
        double spellingScore = (1.0 - ((double) levDistance / maxLen)) * 100;

        String dm1Primary = doubleMetaphone.doubleMetaphone(s1);
        String dm2Primary = doubleMetaphone.doubleMetaphone(s2);
        String dm1Alt = doubleMetaphone.doubleMetaphone(s1, true);
        String dm2Alt = doubleMetaphone.doubleMetaphone(s2, true);

        boolean primaryMatch = dm1Primary.equals(dm2Primary);
        boolean altMatch = dm1Alt.equals(dm2Alt);
        double phoneticScore = (primaryMatch || altMatch) ? 100 : 50;

        double finalScore = (spellingScore * 0.6) + (phoneticScore * 0.4);

        System.out.printf("Compare: %-25s ↔ %-25s | Levenshtein: %5.1f | Phonetic: %5.1f | Final: %5.1f%n",
                s1, s2, spellingScore, phoneticScore, finalScore);
    }

    public static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++)
            dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++)
            dp[0][j] = j;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost);
            }
        }
        return dp[s1.length()][s2.length()];
    }
}