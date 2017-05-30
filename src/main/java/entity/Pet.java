package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int petId;
    private String name;
    private String kind;
    private int age;
    private double weight;


    public Pet(String name, String kind, int age, double weight) {
        this.name = name;
        this.kind = kind;
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                "} \n";
    }
}
