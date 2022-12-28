package com.example.cv_desktop_app;

import java.util.ArrayList;
public class Person {
        private String name;
        private String surname;
        private ArrayList<contactInfo> ContactInfo;
        private int birthday;
        private String educationInfo;
        private String skills;
        private String experience;
        private String publications;

        public Person(String name, String surname, ArrayList<contactInfo> ContactInfo, int birthday, String educationInfo, String skills, String experience, String publications) {
            this.name = name;
            this.surname = surname;
            this.ContactInfo = ContactInfo;
            this.birthday = birthday;
            this.educationInfo = educationInfo;
            this.skills = skills;
            this.experience = experience;
            this.publications = publications;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public ArrayList<contactInfo> getContactInfo() {
            return ContactInfo;
        }

        public void setContactInfo(ArrayList<contactInfo> ContactInfo) {
            this.ContactInfo = ContactInfo;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public String getEducationInfo() {
            return educationInfo;
        }

        public void setEducationInfo(String educationInfo) {
            this.educationInfo = educationInfo;
        }

        public String getSkills() {
            return skills;
        }

        public void setSkills(String skills) {
            this.skills = skills;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getPublications() {
            return publications;
        }

        public void setPublications(String publications) {
            this.publications = publications;
        }
    }

