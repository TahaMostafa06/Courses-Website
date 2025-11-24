package ProgressTracker;

import java.util.Map;

public class PerformanceAnalytics {

    public double averageScoreForLesson(Map<String, Map<String, Integer>> quizResults, String lessonId) {
        int totalScore = 0;
        int count = 0;
        for (Map<String, Integer> studentScores : quizResults.values()) {
            if (studentScores.containsKey(lessonId)) {
                totalScore += studentScores.get(lessonId);
                count++;
            }
        }
        return count == 0 ? 0 : (double) totalScore / count;
    }

    public double averageCompletionRate(Map<String, Double> completionPercentages) {
        double total = 0;
        int count = completionPercentages.size();
        for (double p : completionPercentages.values()) {
            total += p;
        }
        return count == 0 ? 0 : total / count;
    }
}

