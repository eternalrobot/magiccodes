package Core.data;

import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "code")
public class code {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "user_id")
    int user_id;
    @Column(name = "jointime")
    Date jointime;
    @Column(name = "input")
    String input;
    @Column(name = "output")
    String output;
    @Column(name = "detial")
    String detial;
    @Basic(fetch = FetchType.LAZY)//long text在java代码中的实现
    @Type(type="text")
    @Column(name="content", nullable=true)
    String content;
    @Basic(fetch = FetchType.LAZY)//long text在java代码中的实现
    @Type(type="text")
    @Column(name="otherdetial", nullable=true)
    String otherdetial;

    @Override
    public String toString() {
        return "code{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", jointime=" + jointime +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", detial='" + detial + '\'' +
                ", content='" + content + '\'' +
                ", otherdetial='" + otherdetial + '\'' +
                '}';
    }

    public code(int user_id, Date jointime, String input, String output, String detial, String content, String otherdetial) {
        this.user_id = user_id;
        this.jointime = jointime;
        this.input = input;
        this.output = output;
        this.detial = detial;
        this.content = content;
        this.otherdetial = otherdetial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOtherdetial() {
        return otherdetial;
    }

    public void setOtherdetial(String otherdetial) {
        this.otherdetial = otherdetial;
    }

    public code() {
    }
}
