package kr.edcan.hospital.data;

/**
 * Created by kotohana5706 on 15. 7. 13.
 */
public class HospitalSearchData {
    private String title;
    private String address;
    private String number;
    private int value;

    public HospitalSearchData(String title, String address, String number, int value) {
        this.title = title;
        this.address = address;
        this.number = number;
        this.value = value;
    }
    public String getTitle(){
        return title;
    }
    public String getAddress(){
        return address;
    }
    public String getNumber(){
        return number;
    }

    public int getValue() {
        return value;
    }
}
