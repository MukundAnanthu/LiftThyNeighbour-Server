import com.neighbour.server.util.PasswordHelper;

/**
 * @author ajithpandel
 */
public class PasswordHashGenerater {

    public static void main(String[] args) {
        String pass = "pass";
        System.out.println(PasswordHelper.hashPassword(pass));
    }
}
