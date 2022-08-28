package fileParsing.domain;

import java.util.ArrayList;

public class Person {

    private String name;
    private String lastname;
    private String dateBirth;
    private Object[] hobbys;

    private Mother mother;

    public Mother getMother() {
        return mother;
    }

    public static class Mother {
        private String motherName;
        private String motherLastname;

        public String getMotherName() {
            return motherName;
        }

        public String getMotherLastname() {
            return motherLastname;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Object[] getHobbys() {
        return hobbys;
    }

    public void setHobbys(Object[] hobbys) {
        this.hobbys = hobbys;
    }
}
