package com.moonlight.roadmapapi.common.utils;

import com.moonlight.roadmapapi.common.exception.RoadmapException;
import com.moonlight.roadmapapi.common.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateUtilsTest {

    @Test
    void notNull() {
        assertThrows(ValidationException.class,()->{
            ValidateUtils.notNull(null,"Integer");
        });
    }

    @Test
    void internalNotNull() {
        assertThrows(RoadmapException.class,()->{
            ValidateUtils.internalNotNull(null);
        });
    }

    @Test
    void notNullOrEmpty() {
        assertThrows(ValidationException.class,()-> {
            ValidateUtils.notNullOrEmpty(null,"null");
        });

        assertThrows(ValidationException.class,()-> {
            ValidateUtils.notNullOrEmpty("","");
        });
    }

    @Test
    void min() {
        assertThrows(ValidationException.class,()-> {
            ValidateUtils.min(-123,0,"");
        });
    }

    @Test
    void doubleMin() {
        assertThrows(ValidationException.class, () -> {
            ValidateUtils.doubleMin(-123.13, 0, "");
        });
    }

    @Test
    void friendlyAssertTrue() {
        assertThrows(ValidationException.class, () -> {
            ValidateUtils.Friendly.assertTrue(false,  "message");
        });
    }
}