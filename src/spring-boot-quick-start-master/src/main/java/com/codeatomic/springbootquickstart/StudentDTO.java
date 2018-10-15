package com.codeatomic.springbootquickstart;

public class StudentDTO {
   public String name, age;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getAge() {
	return age;
}

public void setAge(String age) {
	this.age = age;
}

@Override
public String toString() {
	return "StudentDTO [name=" + name + ", age=" + age + "]";
}
}
