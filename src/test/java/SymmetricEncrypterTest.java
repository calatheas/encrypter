import com.calatheas.encrypter.AesType;
import com.calatheas.encrypter.SymmetricEncrypter;
import com.calatheas.encrypter.SymmetricKeyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SymmetricEncrypterTest {
    private SymmetricEncrypter encrypter = new SymmetricEncrypter("SD+YRGZwN08TiVGZuvxEJ3pXxzxlz+AOptEdFNf1xEc=");

    @Test
    public void generateAndPrintKey() {
        SymmetricKeyGenerator.generateAndPrintKey(AesType.AES256);
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
