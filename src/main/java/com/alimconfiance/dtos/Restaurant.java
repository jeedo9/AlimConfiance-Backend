public class Restaurant {
    private String name;
    private String address;
    private String postalCode;
    private String city;
    private String inspectionDate;
    private String acitivty;
    private Rating rating;

    public Restaurant(final String name, final String address, final String postalCode, final String city, final String inspectionDate, final String acitivty, final Rating rating) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.inspectionDate = inspectionDate;
        this.activity = acitivty;
        this.rating = rating;
    }
}