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
        query = "SELECT p FROM Personal_data AS p ORDER BY p.id DESC"
    ),
    @NamedQuery(
        name = "getPersonal_dataCount",
        query = "SELECT COUNT(p) FROM Personal_data AS p"
    ),
    @NamedQuery(
        name = "checkRegisteredName",
        query = "SELECT COUNT(p) FROM Personal_data AS p WHERE p.name = :name"
    ),
    @NamedQuery(
        name = "checkLoginNameAndPassword",
        query = "SELECT p FROM Personal_data AS p WHERE p.delete_flag = 0 AND p.name = :code AND p.password = :pass"
    )
})
@Entity
public class Personal_data {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name",  unique = true, nullable = false )
    private String name;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "target_kcal", nullable = false)
    private Integer target_kcal;

    @Column(name = "target_weight", nullable = false)
    private Double target_weight;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;


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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
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

    public Double getTarget_weight() {
        return target_weight;
    }

    public void setTarget_weight(Double target_weight) {
        this.target_weight = target_weight;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}
