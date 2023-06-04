package work_with_files.homework.model;

import java.util.List;

public class Sample {
    public String firstName;
    public String lastName;
    public String gender;
    public int age;
    public Address address;
    public List<PhoneNumbers> phoneNumbers;

    public static class Address {
        public int streetAddress;
        public String city;
        public String state;
    }

    public static class PhoneNumbers {
        public String type;
        public long number;
    }
}
