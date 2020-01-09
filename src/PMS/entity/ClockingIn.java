package PMS.entity;
//import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
//import java.util.Date;
import java.sql.Timestamp;
public class ClockingIn {
    private String Cleno ;
    private Timestamp Cldatetime;
    private String Clstatus;
    public static final String ontime ="准时出勤";
    public static final String late ="迟到";
    public static final String absenteeism ="旷工";

    public String getCleno() {
        return Cleno;
    }

    public void setCleno(String cleno) {
        Cleno = cleno;
    }

    public Timestamp getCldatetime() {
        return Cldatetime;
    }

    public void setCldatetime(Timestamp cldatetime) {
        Cldatetime = cldatetime;
    }

    public String getClstatus() {
        return Clstatus;
    }

    public void setClstatus(String clstatus) {
        Clstatus = clstatus;
    }

    @Override
    public String toString() {
        return "ClockingIn{" +
                "Cleno='" + Cleno + '\'' +
                ", Cldatetime=" + Cldatetime +
                ", Clstatus='" + Clstatus + '\'' +
                '}';
    }
    public static void main(String []args){
        ClockingIn C= new ClockingIn();
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));
    }
}
