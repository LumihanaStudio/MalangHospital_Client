package kr.edcan.hospital.data;

/**
 * Created by Marzin-Oh on 2015-10-09.
 */
public class Hospital {
    public String _id;
    public String name;
    public String lat, longi;
    public int location;
    public String address;
    public String number;
    public String tel;
    public Hospital(String _id, String name,String number, String lat, String longi, int location, String address, String tel){
        this._id = _id;
        this.name=  name;
        this.lat = lat;
        this.longi = longi;
        this.number =number;
        this.location = location;
        this.address = address;
        this.tel = tel;
    }
}
