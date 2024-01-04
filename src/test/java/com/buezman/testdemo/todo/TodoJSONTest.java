package com.buezman.testdemo.todo;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

class TodoJSONTest {

    @Test
    void shouldCompareJSON() throws JSONException {
        String expected = """
                {
                    "todos": [
                    {
                    "name": "TEST 1",
                    "completed": true
                    },
                    {
                    "name": "TEST 2",
                    "completed": false
                    }
                    ]
                }
                """;
        JSONAssert.assertEquals(expected, getJSONData(), false);
    }

    private String getJSONData() {
        return """
                {
                    "todos": [
                    {
                    "completed": true,
                    "name": "TEST 1"
                    },
                    {
                    "completed": false,
                    "name": "TEST 2"
                    }
                    ]
                }
                """;
    }
}
