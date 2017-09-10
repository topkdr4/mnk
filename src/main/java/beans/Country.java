package beans;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public class Country {

    private int id;
    private String name;
    private String url;


    public Country() {
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


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "Country{" + "id=" + id + ", name='" + name + '\'' + ", url='" + url + '\'' + '}';
    }
}
