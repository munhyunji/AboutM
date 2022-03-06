package study.spring.springhelper.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Simple {
	@SerializedName("name") private String name;
	@SerializedName("type") private String type;
	@SerializedName("img") private String img;

}
