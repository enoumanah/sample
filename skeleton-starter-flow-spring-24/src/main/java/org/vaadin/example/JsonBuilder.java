package org.vaadin.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonBuilder {

    public static void appendStepMapJson(String rqType, List<TransitionStep> steps) {
        StringBuilder jsonBuilder = new StringBuilder();

        jsonBuilder.append("{\n");
        jsonBuilder.append("\"").append(rqType).append("\": {\n");
        jsonBuilder.append("\"stepMap\": {\n");

        for (int i = 0; i < steps.size(); i++) {
            TransitionStep step = steps.get(i);

            jsonBuilder.append("\"").append(step.step).append("\": {\n")
                       .append("\"stateFrom\": \"").append(step.stateFrom).append("\",\n")
                       .append("\"action\": \"").append(step.action).append("\",\n")
                       .append("\"stateTo\": \"").append(step.stateTo).append("\"\n")
                       .append("}");

            if (i < steps.size() - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }

        jsonBuilder.append("}\n"); // close stepMap
        jsonBuilder.append("}\n"); // close rqType object
        jsonBuilder.append("}\n"); // close root

        try (FileWriter writer = new FileWriter("output.json", true)) {
            writer.write(jsonBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    public class TransitionStep {
        String step;
        String stateFrom;
        String action;
        String stateTo;
    
        public TransitionStep(String step, String stateFrom, String action, String stateTo) {
            this.step = step;
            this.stateFrom = stateFrom;
            this.action = action;
            this.stateTo = stateTo;
        }
    }
    
}

