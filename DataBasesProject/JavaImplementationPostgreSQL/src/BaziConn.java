import java.sql.*;
import java.util.Locale;

public class BaziConn {
    public static void main(String[] args){
        bazaConnect();
    }

    //funkcijata se povikuva od main
    public static void bazaConnect() {

        String baza_DRIVER = "org.postgresql.Driver";
        String baza_STRING = "jdbc:postgresql://localhost:5435/db_201920z_gv_ind_063";
        String baza_USERNAME = "u152030";
        String baza_PASSWORD = "152030";



        Locale.setDefault(Locale.ENGLISH);

        try {


            Driver driver = (Driver) Class.forName(baza_DRIVER).newInstance();

            Connection conn = DriverManager.getConnection(baza_STRING,
                    baza_USERNAME, baza_PASSWORD);


            PreparedStatement statement = conn.prepareStatement(
                    "SELECT s.id_lifce ,s.dobitno from lifce as s WHERE s.dobitno is true GROUP BY id_lifce,dobitno ORDER BY id_lifce");

            ResultSet dobitniTiketi = statement.executeQuery();

            boolean isEmpty = !dobitniTiketi.next();
            boolean hasData = !isEmpty;
            System.out.println("---------- ID NA TICKETI I NIVNIOT STATUS ----------");
            while (hasData) {


                System.out.println("ID TICKET: " +
                        dobitniTiketi.getString("id_lifce")
                        + " | STATUS: " + dobitniTiketi.getString("dobitno"));

                hasData = dobitniTiketi.next();
            }

            dobitniTiketi.close();
            // ****************************************

        } catch (Exception e) {
            System.out.println(e);
        }


    }
}