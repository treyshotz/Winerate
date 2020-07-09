package models;

import java.util.List;

public class Whisky extends Drink {
    //The type in this case would be, scotch, bourbon, rye and so on
    //There might be a better way to differentiate, but works for now
    private String type;
    private int age;
    //As the type mainly is based on country, we dont need it. Region however is important, you can have a scotch from the islay region
    //if the region is not specified, we can have the country as region. If we want both, we can write Scotland, Islay
    private String region;
    //how long is stored in casket mainly
    private String method;

    public Whisky(){
    }

    public Whisky(int productId, String name, double alcohol, double volume, double price, String description, List<Rating> ratings, String type, int age, String region, String method){
        super(productId, name, alcohol, volume, price, description, ratings);
        this.type = type;
        this.age = age;
        this.region = region;
        this.method = method;
    }

    public Whisky(int productId, String name, double alcohol, double volume, double price, String description, String type, int age, String region, String method){
        super(productId, name, alcohol, volume, price, description);
        this.type = type;
        this.age = age;
        this.region = region;
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public String getRegion() {
        return region;
    }

    public String getMethod() {
        return method;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
