public class Person {
    public Person(String name, String surname, ContactInfo[] contactInfo, int birthday, String educationInfo, String skills, String experience, String publications) {
        Name = name;
        Surname = surname;
        ContactInfo = contactInfo;
        this.birthday = birthday;
        EducationInfo = educationInfo;
        Skills = skills;
        Experience = experience;
        this.publications = publications;
    }

    private String Name;
    private String Surname;
    private ContactInfo[] ContactInfo;
    private int birthday;
    private String EducationInfo;
    private String Skills;
    private String Experience;
    private String  publications;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public ContactInfo[] getContactInfo() {
        return ContactInfo;
    }

    public void setContactInfo(ContactInfo[] contactInfo) {
        ContactInfo = contactInfo;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getEducationInfo() {
        return EducationInfo;
    }

    public void setEducationInfo(String educationInfo) {
        EducationInfo = educationInfo;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getPublications() {
        return publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }
}