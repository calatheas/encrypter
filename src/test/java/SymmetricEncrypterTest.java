import com.encrypter.Encrypter;
import com.encrypter.KeyGenerator;
import com.encrypter.SymmetricEncrypter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class SymmetricEncrypterTest {
    private Encrypter encrypter = new SymmetricEncrypter("pGavtHd0tNJYFSQW3PXsGQ==");

    @Test
    public void generateAndPrintKey() {
        KeyGenerator.generateAndPrintKey();
    }

    @Test
    public void encryptAndDecrypt() {
        //given
        String target = "I'm korean.";
        String targetKor = "나는 대한민국 사람입니다.";

        //when
        String encTarget = encrypter.encrypt(target);
        String encTargetKor = encrypter.encrypt(targetKor);
        String decTarget = encrypter.decrypt(encTarget);
        String decTargetKor = encrypter.decrypt(encTargetKor);

        Assertions.assertEquals(target, decTarget);
        Assertions.assertEquals(targetKor, decTargetKor);
    }
}
