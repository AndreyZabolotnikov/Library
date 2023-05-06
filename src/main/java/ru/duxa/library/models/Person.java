package ru.duxa.library.models;

import javax.validation.constraints.*;

public class Person {

    private  int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Pattern(regexp = "[А-ЯЁA-Z]\\S\\D+ +[А-ЯЁA-Z]\\S\\D+ +[А-ЯЁA-Z]\\S\\D+", message = "Введите ФИО правильно (пример: Иванов Иван Иванович)")
    @Size(min = 2, max = 100, message = "Имя должно быть от  2 до 100 символов длиной")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    private int age;

    public Person( String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
