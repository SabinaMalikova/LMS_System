package models;

public class GenerateId {
    public static Long groupId = 0L;
    public static Long studentId = 0L;
    public static Long lessonId = 0L;


    public static Long genGroupId(){
        return ++groupId;
    }
    public static Long genStudent(){
        return ++studentId;
    }
    public static Long genLessonId(){
        return ++lessonId;
    }


}
