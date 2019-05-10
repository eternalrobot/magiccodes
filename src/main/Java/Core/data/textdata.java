package Core.data;

import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "textdata")
public class textdata {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "time")
    Date time;
    @Column(name = "projectid")
    int projectid;
    @Column(name = "thename")
    String name;
    @Column(name = "thelength")
    String length;
    @Basic(fetch = FetchType.LAZY)//long text在java代码中的实现
    @Type(type="text")
    @Column(name="content", nullable=true)
    String content;

    public textdata(Date time, int projectid, String name, String length, String content) {
        this.time = time;
        this.projectid = projectid;
        this.name = name;
        this.length = length;
        this.content = content;
    }

    @Override
    public String toString() {
        return "textdata{" +
                "id=" + id +
                ", time=" + time +
                ", projectid=" + projectid +
                ", name='" + name + '\'' +
                ", length='" + length + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public textdata() {
    }
}
