package example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String username;
    private String email;

    private String addressStreet;
    private String addressSuite;
    private String addressCity;
    private String addressZipcode;
    private String lat;
    private String lng;

    private String phone;
    private String website;

    private String companyName;
    private String catchPhrase;
    private String bs;

    @JsonProperty("address")
    private void unpackNestedAddress(Map<String,Object> address) {
        this.addressStreet = (String)address.get("street");
        this.addressStreet = (String)address.get("suite");
        this.addressStreet = (String)address.get("city");
        this.addressStreet = (String)address.get("zipcode");
        Map<String,String> geo = (Map<String,String>)address.get("geo");
        this.lat = geo.get("lat");
        this.lng = geo.get("lng");
    }

    @JsonProperty("company")
    private void unpackNestedCompany(Map<String,Object> company) {
        this.companyName = (String)company.get("name");
        this.catchPhrase = (String)company.get("catchPhrase");
        this.bs = (String)company.get("bs");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(addressStreet, user.addressStreet) && Objects.equals(addressSuite, user.addressSuite) && Objects.equals(addressCity, user.addressCity) && Objects.equals(addressZipcode, user.addressZipcode) && Objects.equals(lat, user.lat) && Objects.equals(lng, user.lng) && Objects.equals(phone, user.phone) && Objects.equals(website, user.website) && Objects.equals(companyName, user.companyName) && Objects.equals(catchPhrase, user.catchPhrase) && Objects.equals(bs, user.bs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, addressStreet, addressSuite, addressCity, addressZipcode, lat, lng, phone, website, companyName, catchPhrase, bs);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressSuite() {
        return addressSuite;
    }

    public void setAddressSuite(String addressSuite) {
        this.addressSuite = addressSuite;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressZipcode() {
        return addressZipcode;
    }

    public void setAddressZipcode(String addressZipcode) {
        this.addressZipcode = addressZipcode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
