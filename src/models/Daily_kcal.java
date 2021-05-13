package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "daily_kcal")

@NamedQueries({
    @NamedQuery(
            name = "getAllDaily_kcal",
            query = "SELECT d FROM Daily_kcal AS d ORDER BY d.id DESC"
            ),
    @NamedQuery(
            name = "getDaily_kcalCount",
            query = "SELECT COUNT(d) FROM Daily_kcal AS d"
            )
})



@Entity

public class Daily_kcal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "personal_data_id", nullable = false)
    private Personal_data personal_data;

    @Column(name = "kcal", nullable = false)
    private Integer kcal;

    @Column(name = "todays_weight", nullable = false)
    private Double todays_weight;

    @Column(name = "bmr_difference", nullable = false)
    private Integer bmr_difference;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "year", nullable = false)
    private Date year;

    @Column(name = "month", nullable = false)
    private Date month;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Personal_data getPersonal_data() {
        return personal_data;
    }

    public void setPersonal_data(Personal_data personal_data) {
        this.personal_data = personal_data;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public Double getTodays_weight() {
        return todays_weight;
    }

    public void setTodays_weight(Double todays_weight) {
        this.todays_weight = todays_weight;
    }

    public Integer getBmr_difference() {
        return bmr_difference;
    }

    public void setBmr_difference(Integer bmr_difference) {
        this.bmr_difference = bmr_difference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }



}
