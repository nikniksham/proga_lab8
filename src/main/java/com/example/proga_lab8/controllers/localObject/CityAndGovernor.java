package com.example.proga_lab8.controllers.localObject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class CityAndGovernor {
    private SimpleIntegerProperty city_id, creator_id, metersAboveSeaLevel, carCode, standardOfLiving_id,  climate_id, governor_id;
    private SimpleLongProperty area, population;
    private SimpleStringProperty city_name, governor_name, coordinates, creation_date;
    private LocalDate birthdate;

    public CityAndGovernor(Integer city_id, String city_name, String coordinates, String creation_date, Long area, Long population,
                           Integer metersAboveSeaLevel, Integer carCode, Integer climate_id, Integer standardOfLiving_id,
                           Integer creator_id, Integer governor_id, String governor_name, LocalDate birthdate) {
        this.city_id = new SimpleIntegerProperty(city_id);
        this.creator_id = new SimpleIntegerProperty(creator_id);
        this.metersAboveSeaLevel = new SimpleIntegerProperty(metersAboveSeaLevel);
        this.carCode = new SimpleIntegerProperty(carCode);
        this.standardOfLiving_id = new SimpleIntegerProperty(standardOfLiving_id);
        this.climate_id = new SimpleIntegerProperty(climate_id);
        this.area = new SimpleLongProperty(area);
        this.population = new SimpleLongProperty(population);
        this.governor_id = (governor_id != null) ? new SimpleIntegerProperty(governor_id) : null;
        this.governor_name = (governor_name != null) ? new SimpleStringProperty(governor_name) : null;
        this.city_name = new SimpleStringProperty(city_name);
        this.birthdate = birthdate;
        this.coordinates = new SimpleStringProperty(coordinates);
        this.creation_date = new SimpleStringProperty(creation_date);
    }

    public int getCity_id() {
        return city_id.get();
    }

    public SimpleIntegerProperty city_idProperty() {
        return city_id;
    }

    public int getCreator_id() {
        return creator_id.get();
    }

    public SimpleIntegerProperty creator_idProperty() {
        return creator_id;
    }

    public int getMetersAboveSeaLevel() {
        return metersAboveSeaLevel.get();
    }

    public SimpleIntegerProperty metersAboveSeaLevelProperty() {
        return metersAboveSeaLevel;
    }

    public int getCarCode() {
        return carCode.get();
    }

    public SimpleIntegerProperty carCodeProperty() {
        return carCode;
    }

    public int getStandardOfLiving_id() {
        return standardOfLiving_id.get();
    }

    public SimpleIntegerProperty standardOfLiving_idProperty() {
        return standardOfLiving_id;
    }

    public int getClimate_id() {
        return climate_id.get();
    }

    public SimpleIntegerProperty climate_idProperty() {
        return climate_id;
    }

    public int getGovernor_id() {
        return governor_id.get();
    }

    public SimpleIntegerProperty governor_idProperty() {
        return governor_id;
    }

    public long getArea() {
        return area.get();
    }

    public SimpleLongProperty areaProperty() {
        return area;
    }

    public long getPopulation() {
        return population.get();
    }

    public SimpleLongProperty populationProperty() {
        return population;
    }

    public String getCity_name() {
        return city_name.get();
    }

    public SimpleStringProperty city_nameProperty() {
        return city_name;
    }

    public String getGovernor_name() {
        return governor_name.get();
    }

    public SimpleStringProperty governor_nameProperty() {
        return governor_name;
    }

    public String getCoordinates() {
        return coordinates.get();
    }

    public SimpleStringProperty coordinatesProperty() {
        return coordinates;
    }

    public String getCreation_date() {
        return creation_date.get();
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setCity_id(int city_id) {
        this.city_id.set(city_id);
    }

    public void setCreator_id(int creator_id) {
        this.creator_id.set(creator_id);
    }

    public void setMetersAboveSeaLevel(int metersAboveSeaLevel) {
        this.metersAboveSeaLevel.set(metersAboveSeaLevel);
    }

    public void setCarCode(int carCode) {
        this.carCode.set(carCode);
    }

    public void setStandardOfLiving_id(int standardOfLiving_id) {
        this.standardOfLiving_id.set(standardOfLiving_id);
    }

    public void setClimate_id(int climate_id) {
        this.climate_id.set(climate_id);
    }

    public void setGovernor_id(int governor_id) {
        this.governor_id.set(governor_id);
    }

    public void setArea(long area) {
        this.area.set(area);
    }

    public void setPopulation(long population) {
        this.population.set(population);
    }

    public void setCity_name(String city_name) {
        this.city_name.set(city_name);
    }

    public void setGovernor_name(String governor_name) {
        this.governor_name.set(governor_name);
    }

    public void setCoordinates(String coordinates) {
        this.coordinates.set(coordinates);
    }

    public void setCreation_date(String creationDate) {
        this.creation_date.set(creationDate);
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
