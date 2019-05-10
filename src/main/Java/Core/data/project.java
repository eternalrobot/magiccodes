package Core.data;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "project")
public class project {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "thename")
    String name;
    @Column(name = "begintime")
    Date begintime;
    @Column(name = "endtime")
    Date endtime;
    @Column(name = "description")
    String description;

    public project() {
    }

    public project(String name, Date begintime, Date endtime, String description) {
        this.name = name;
        this.begintime = begintime;
        this.endtime = endtime;
        this.description = description;
    }

    @Override
    public String toString() {
        return "project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", begintime=" + begintime +
                ", endtime=" + endtime +
                ", description='" + description + '\'' +
                '}';
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

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
