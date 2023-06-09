package com.example.proga_lab8.my_programm.obj;

import com.example.proga_lab8.Main;
import com.example.proga_lab8.my_programm.enums.Climate;
import com.example.proga_lab8.my_programm.enums.StandardOfLiving;
import javafx.scene.paint.Color;

import java.sql.Timestamp;
import java.util.Date;

public class City {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String coordinates; //Поле не может быть null
    private Timestamp creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long area; //Значение поля должно быть больше 0
    private Long population; //Значение поля должно быть больше 0, Поле не может быть null
    private Integer metersAboveSeaLevel;
    private int carCode; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private Climate climate; //Поле может быть null
    private StandardOfLiving standardOfLiving; //Поле может быть null
    private Human governor; //Поле может быть null
    private Integer creator_id;

    public City(Integer id_p, String name_p, String coordinates_p, long area_p, Long population_p, int metersAboveSeaLevel_p, int carCode_p, Climate climate_p, StandardOfLiving standardOfLiving_p, Human governor_p, Integer creator_id) {
        if (name_p == null) {throw new RuntimeException("Имя города не должно быть null");}
        if (area_p < 1) {throw new RuntimeException("Площадь города должна быть больше 0");}
        if (population_p == null) {throw new RuntimeException("Население города не должно быть null");}
        if (population_p < 1) {throw new RuntimeException("Население города должна быть больше 0");}
        if (carCode_p < 1 || carCode_p > 1000) {throw new RuntimeException("Код автомобиля должен быть в диапозоне [1; 1000]");}
        if (climate_p == null) {throw new RuntimeException("Климат должен быть не null");}
        if (standardOfLiving_p == null) {throw new RuntimeException("Стандарт жизни должен быть не null");}
        this.id = id_p;
        this.setName(name_p);
        this.setCoordinates(coordinates_p);
        this.setArea(area_p);
        this.setPopulation(population_p);
        this.setMetersAboveSeaLevel(metersAboveSeaLevel_p);
        this.setCarCode(carCode_p);
        this.setClimate(climate_p);
        this.setStandardOfLiving(standardOfLiving_p);
        this.setGovernor(governor_p);
        this.setCreationDate(new Timestamp(System.currentTimeMillis()));
        this.creator_id = creator_id;
    }

    public boolean inRange(String cur, long lower, long maximal) {
        long cur_num = Long.parseLong(cur);
        return cur_num >= lower && cur_num <= maximal;
    }

    public String setNewData(String name_p, String coordinates_p, String area_p, String population_p, String metersAboveSeaLevel_p, String carCode_p, String climate_p, String standardOfLiving_p) {
        try {
            if (name_p.equals("")) {
                throw new RuntimeException("Имя города не должно быть null");
            }
            String[] coor = coordinates_p.split(", ");
            if (coor.length != 2 || !inRange(coor[0], 0, 200) || !inRange(coor[1], 0, 200)) {
                throw new RuntimeException("Координаты должны задаваться в формате [0; 200], [0; 200]");
            }
            if (Long.parseLong(area_p) <= 0) {
                throw new RuntimeException("Площадь города должна быть больше 0");
            }
            if (Long.parseLong(population_p) < 0) {
                throw new RuntimeException("Население города должно быть больше или равно 0");
            }
            Integer.parseInt(metersAboveSeaLevel_p);
            if (!inRange(carCode_p, 1, 1000)) {
                throw new RuntimeException("Код автомобиля должен быть в диапозоне [1; 1000]");
            }
            Climate.getClimateByString(climate_p);
            StandardOfLiving.getStandardById(standardOfLiving_p);

            this.setName(name_p);
            this.setCoordinates(coordinates_p);
            this.setArea(Long.parseLong(area_p));
            this.setPopulation(Long.parseLong(population_p));
            this.setMetersAboveSeaLevel(Integer.parseInt(metersAboveSeaLevel_p));
            this.setCarCode(Integer.parseInt(carCode_p));
            this.setClimate(Climate.getClimateByString(climate_p));
            this.setStandardOfLiving(StandardOfLiving.getStandardById(standardOfLiving_p));

        } catch (Exception e) {
            return "error " + e.getMessage();
        }

        return "success " + this.getCommandFormat();
    }

    public String getCommandFormat() {
        return "{" + '"' + this.name+ '"' + ", [" + this.coordinates + "], " + this.area + ", " + this.population + ", " + this.metersAboveSeaLevel + ", " + carCode + ", " + Climate.getIdByName(climate) + ", " + StandardOfLiving.getIdByName(standardOfLiving) + "}";
    }
    public long get_num_for_srav() {
        return (area + population + metersAboveSeaLevel + carCode) * (climate == null ? 1 : Climate.getIdByName(climate)) * (standardOfLiving == null ? 1 : StandardOfLiving.getIdByName(standardOfLiving));
    }

    @Override
    public String toString() {
        return "Город: " + getName() +
               "\nid: " + id +
               "\nкоординаты: " + getCoordinates() +
               "\nдата добавления в таблицу: " + getCreationDate() +
               "\nплощадь: " + getArea() +
               "\nнаселение: " + getPopulation() +
               "\nвысота над уровнем моря: " + getMetersAboveSeaLevel() +
               "\nкод региона для авто: " + getCarCode() +
               "\nклимат: " + getClimate() +
               "\nуровень жизни: " + getStandardOfLiving() +
               "\nправитель: " + getGovernor();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    private void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public long getArea() {
        return area;
    }

    private void setArea(long area) {
        this.area = area;
    }

    public Long getPopulation() {
        return population;
    }

    private void setPopulation(Long population) {
        this.population = population;
    }

    public Integer getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    private void setMetersAboveSeaLevel(Integer metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public int getCarCode() {
        return carCode;
    }

    private void setCarCode(int carCode) {
        this.carCode = carCode;
    }

    public Climate getClimate() {
        return climate;
    }

    private void setClimate(Climate climate) {
        this.climate = climate;
    }

    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    private void setStandardOfLiving(StandardOfLiving standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public Color getColor() {
        return new Color(Math.abs(Math.cos(creator_id * 1.7025)%1), Math.abs(Math.sin(creator_id * 1.7512)%1), Math.abs((Math.cos(creator_id * 1.361212)+Math.sin(creator_id * 1.1125))%1), 0.8);
    }

    public float getRadius() {
        float res = 10f;
        if (area > 27_610_000L) {
            res = 60f;
            if (area < 2_561_000_000L) {
                res = (float) ((Math.log(area / 1000000f) / Math.log(2.5)) - 2.57) * 10f;
            }
        }
        return res;
    }

    public Integer getId() {
        return this.id;
    }
}
