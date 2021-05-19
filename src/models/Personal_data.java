package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "personal_data")
@NamedQueries({
    @NamedQuery(
        name = "getAllPersonal_data",
        query = "SELECT p FROM Personal_data AS p WHERE p.id= :id ORDER BY p.id DESC"
    ),
    @NamedQuery(
        name = "getPersonal_dataCount",
        query = "SELECT COUNT(p) FROM Personal_data AS p WHERE p.id = :id"
    ),
    @NamedQuery(
        name = "checkRegisteredName",
        query = "SELECT COUNT(p) FROM Personal_data AS p WHERE p.name = :name"
    ),
    @NamedQuery(
            name = "checkLoginNameAndPassword",
            query = "SELECT p FROM Personal_data AS p WHERE p.name = :name AND p.password = :pass"
        )
})
@Entity
public class Personal_data {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",  nullable = false,  unique = true)
    private String name;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "target_kcal", nullable = false)
    private Integer target_kcal;

    @Column(name = "target_weight", nullable = false)
    private double target_weight;

    @Column(name = "for_month", nullable = false)
    private Integer for_month;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTarget_kcal() {
        return target_kcal;
    }

    public void setTarget_kcal(Integer target_kcal) {
        this.target_kcal = target_kcal;
    }

    public double getTarget_weight() {
        return target_weight;
    }

    public void setTarget_weight(double target_weight) {
        this.target_weight = target_weight;
    }

    public Integer getFor_month() {
        return for_month;
    }

    public void setFor_month(Integer for_month) {
        this.for_month = for_month;
    }

}
