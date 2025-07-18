package com.example.jexpath;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JexPathFilterTest {

    private final String sampleJson = """
        {
          "alarms": [
            {
              "alarmId": "ALM001",
              "alarmType": "COMMUNICATIONS_ALARM",
              "perceivedSeverity": "CRITICAL",
              "ackState": "UNACKNOWLEDGED"
            },
            {
              "alarmId": "ALM002",
              "alarmType": "EQUIPMENT_ALARM",
              "perceivedSeverity": "MAJOR",
              "ackState": "ACKNOWLEDGED"
            },
            {
              "alarmId": "ALM003",
              "alarmType": "COMMUNICATIONS_ALARM",
              "perceivedSeverity": "MINOR",
              "ackState": "UNACKNOWLEDGED"
            }
          ]
        }
    """;

    @Test
    void test_and_condition() {
        var result = JexPathFilter.filter(sampleJson,
                "alarmType==\"COMMUNICATIONS_ALARM\" and perceivedSeverity==\"CRITICAL\"",
                "$.alarms");
        assertEquals(1, result.size());
    }

    @Test
    void test_or_condition() {
        var result = JexPathFilter.filter(sampleJson,
                "perceivedSeverity==\"CRITICAL\" or perceivedSeverity==\"MAJOR\"",
                "$.alarms");
        assertEquals(2, result.size());
    }

    @Test
    void test_pipe_condition() {
        var result = JexPathFilter.filter(sampleJson,
                "perceivedSeverity==\"CRITICAL|MINOR\"",
                "$.alarms");
        assertEquals(2, result.size());
    }

    @Test
    void test_combined_logic() {
        var result = JexPathFilter.filter(sampleJson,
                "alarmType==\"COMMUNICATIONS_ALARM\" and perceivedSeverity==\"CRITICAL|MINOR\"",
                "$.alarms");
        assertEquals(2, result.size());
    }
}
