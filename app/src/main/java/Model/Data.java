package Model;


import java.io.Serializable;

public class Data implements Serializable {

    private int id;
    private String name;
    private String email;
    private String password;
    private String roll;

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {

        this.roll = roll;
    }

    public Data(int id, String name, String email, String password, String roll) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roll = roll;
    }

    public Data(String name, String email, String password, String roll) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roll=roll;
    }

    public Data(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public Data(String name,String email, String password) {
        this.name = name;
        this.password = password;
        this.email=email;
    }

    public Data() {

    }

    public Data(String s) {
    this.name=s;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}