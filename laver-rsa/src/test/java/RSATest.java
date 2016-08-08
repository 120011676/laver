import com.github.laver.rsa.util.OpenSSHRSAFile;
import com.github.laver.rsa.util.RSA;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import java.security.Security;
import java.security.interfaces.RSAPrivateKey;


/**
 * Created by say on 8/8/16.
 */
public class RSATest {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        String m = "d6KB44FrFvyS2qrwPT0JDJb6ntBNhuRQKbRV/NdKMsx8HCBFBB1Td0Zg65LfG/m/eMyg8HWcwLlP\nlb1zVOAdxCVShaJyO+5tPfY3xPO99lXyD2z88qb+HZn2ugy1gA7pkDHFnZwczQvPi577OnPfKfUo\nkGSNbsQwekTHtEWFCuWEH1488Bd6HR8IVeFwS2VvLwW/dNCtO0eNxb3OirhDjDMELxeGKRPriOdw\nOlWUxTwBcXbuMhDl0p4qEVbXdrt4T4penp32N1KMfvvaVu5E2zqt4fsfEqu31wTrmzLpV0vf+ZVf\n1wFyXbjatLcVzra2Zf4LKYHtufMUQH/UrVszdQ==";
        byte[] bs = Base64.decode(m);
        RSAPrivateKey privateKey = OpenSSHRSAFile.getPrivateKey("/Users/say/Downloads/1003");
        byte[] ws = RSA.decrypt(bs, privateKey);
        System.out.println(new String(ws));
    }
}
