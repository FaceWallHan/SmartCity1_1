package com.example.smartcity1_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 15:03
 */
public class ServiceInfo  {


    /**
     * id : 1
     * name : 便民服务
     * weight : 10
     * image : http://localhost:8080/mobileA/images/tubiao1.png
     * type : 智慧服务
     */

    private int id;
    private String name;
    private int weight;
    private String image;
    private String type;

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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
