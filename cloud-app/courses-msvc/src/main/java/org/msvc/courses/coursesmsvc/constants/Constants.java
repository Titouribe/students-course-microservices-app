package org.msvc.courses.coursesmsvc.constants;

public class Constants {
    public static final String COURSE = "Course";
    public static final String FOUND_AND_DELETED = " Found and deleted.";

    public static String foundAndDeleted(String entity, String reference){
        return entity + " " + reference + FOUND_AND_DELETED;
    }
}
