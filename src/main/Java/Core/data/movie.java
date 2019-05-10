package Core.data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "movie")
public class movie {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "filelocation")
    String filelocation;
    @Column(name = "moviename")
    String name;
    @Column(name = "thecountry")
    String thecountry;
    @Column(name = "playnumber")
    int playnumber;
    @Column(name = "score")
    int lovescore;
    @Column(name = "power")
    String power;

    public movie() {
    }

    public movie(String filelocation, String name, String thecountry, int playnumber, int lovescore, String power) {
        this.filelocation = filelocation;
        this.name = name;
        this.thecountry = thecountry;
        this.playnumber = playnumber;
        this.lovescore = lovescore;
        this.power = power;
    }

    @Override
    public String toString() {
        return "movie{" +
                "id=" + id +
                ", filelocation='" + filelocation + '\'' +
                ", name='" + name + '\'' +
                ", thecountry='" + thecountry + '\'' +
                ", playnumber=" + playnumber +
                ", lovescore=" + lovescore +
                ", power='" + power + '\'' +
                '}';
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilelocation() {
        return filelocation;
    }

    public void setFilelocation(String filelocation) {
        this.filelocation = filelocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThecountry() {
        return thecountry;
    }

    public void setThecountry(String thecountry) {
        this.thecountry = thecountry;
    }

    public int getPlaynumber() {
        return playnumber;
    }

    public void setPlaynumber(int playnumber) {
        this.playnumber = playnumber;
    }

    public int getLovescore() {
        return lovescore;
    }

    public void setLovescore(int lovescore) {
        this.lovescore = lovescore;
    }
}
