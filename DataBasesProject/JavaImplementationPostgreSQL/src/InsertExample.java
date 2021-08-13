import java.sql.*;
import java.util.Locale;

public class InsertExample {
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
                    "INSERT INTO public.lifce(id_lifce,dobitno) VALUES (1800,false)");

            ResultSet insertot = statement.executeQuery();

            boolean isEmpty = !insertot.next();
            boolean hasData = !isEmpty;
            System.out.println("---------- PROCESIRAN INSERT ----------");

            insertot.close();
            // ****************************************

        } catch (Exception e) {
            System.out.println(e);
        }


    }
}