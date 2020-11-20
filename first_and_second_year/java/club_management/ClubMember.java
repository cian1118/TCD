package Week6;

public class ClubMember {
    String name;
    String address;
    int yearOfReg;
    String email;

    public ClubMember(String name, String address, int yearOfReg, String email) {
        this.name = name;
        this.address = address;
        this.yearOfReg = yearOfReg;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYearOfReg() {
        return yearOfReg;
    }

    public void setYearOfReg(int YearOfReg) {
        this.yearOfReg = YearOfReg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClubMember{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", YearOfReg=" + yearOfReg +
                ", email='" + email + '\'' +
                '}';
    }
}
