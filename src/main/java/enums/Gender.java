package enums;

public enum Gender {
    MALE,
    FEMALE;

    public Gender getGender(String genderName){
        for(Gender gender : values()){
            if(gender.name().equals(genderName)){
                return gender;
            }
        }
        return null;
    }

}
