package com.atguigu.thread;

import lombok.Data;

@Data
class Person {
    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }
}

public class TestTransferValue {
    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setPersonName("xxx");
    }

    public void changeValue3(String str) {
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age = " + age);

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("person = " + person);

        String str = "abc";
        // 常量池, 池子中没有就新建了一个, main方法中引用的str没有发生变化
        // changeValue3引用的str发生了变化, 但也仅仅只是发生了变化
        test.changeValue3(str);
        System.out.println("str = " + str);
    }

}
