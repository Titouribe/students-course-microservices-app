package org.msvc.courses.coursesmsvc.constants;

import org.springframework.stereotype.Component;

@Component
public class ErrorsConstants {

    public static final String NOT_FOUND = "404";
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "401";

    public static final String EMPTY_LIST = "Return a empty list.";
    public static String notFound(String entity, String attribute) {
        return entity + " " + attribute + " not found.";
    }
}
