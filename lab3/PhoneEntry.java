/**
 * Data storage class for phone number and name.
 * @author Casey Hopkins
 */
public class PhoneEntry {

    private String phoneNumber;
    private String name;

    public PhoneEntry(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
