package network.database.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Table(name = "Equipment", schema = "sql11225282", catalog = "")
public class EquipmentEntity {
    private int id;
    private String helm;
    private String chest;
    private String legs;
    private String gloves;
    private String boots;
    private String mainHand;
    private String offHand;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "helm", nullable = true, length = 30)
    public String getHelm() {
        return helm;
    }

    public void setHelm(String helm) {
        this.helm = helm;
    }

    @Basic
    @Column(name = "chest", nullable = true, length = 30)
    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    @Basic
    @Column(name = "legs", nullable = true, length = 30)
    public String getLegs() {
        return legs;
    }

    public void setLegs(String legs) {
        this.legs = legs;
    }

    @Basic
    @Column(name = "gloves", nullable = true, length = 30)
    public String getGloves() {
        return gloves;
    }

    public void setGloves(String gloves) {
        this.gloves = gloves;
    }

    @Basic
    @Column(name = "boots", nullable = true, length = 30)
    public String getBoots() {
        return boots;
    }

    public void setBoots(String boots) {
        this.boots = boots;
    }

    @Basic
    @Column(name = "main_hand", nullable = true, length = 30)
    public String getMainHand() {
        return mainHand;
    }

    public void setMainHand(String mainHand) {
        this.mainHand = mainHand;
    }

    @Basic
    @Column(name = "off_hand", nullable = true, length = 30)
    public String getOffHand() {
        return offHand;
    }

    public void setOffHand(String offHand) {
        this.offHand = offHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentEntity that = (EquipmentEntity) o;
        return id == that.id &&
                Objects.equals(helm, that.helm) &&
                Objects.equals(chest, that.chest) &&
                Objects.equals(legs, that.legs) &&
                Objects.equals(gloves, that.gloves) &&
                Objects.equals(boots, that.boots) &&
                Objects.equals(mainHand, that.mainHand) &&
                Objects.equals(offHand, that.offHand);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, helm, chest, legs, gloves, boots, mainHand, offHand);
    }
}
