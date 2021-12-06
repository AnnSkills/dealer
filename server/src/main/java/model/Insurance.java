package model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Insurance implements Serializable {
    private int idInsurance;
    private int idClient;
    private String insuranceName;

    public Insurance(){
        this.idInsurance = 0;
        this.idClient = 0;
        this.insuranceName = "";
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "idInsurance=" + idInsurance +
                ", idClient=" + idClient +
                ", insuranceName='" + insuranceName + '\'' +
                '}';
    }
}
