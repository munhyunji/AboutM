package study.spring.springhelper.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class GradeList {
    @SerializedName("student")    private List<Student> student;

    @Data
    public class Student {
        @SerializedName("name")   private String name;
        @SerializedName("kor")    private int kor;
        @SerializedName("eng")    private int eng;
        @SerializedName("math")   private int math;
    }
}
