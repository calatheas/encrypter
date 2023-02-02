import com.calatheas.encrypter.AesType;
import com.calatheas.encrypter.AsymmetricEncrypter;
import com.calatheas.encrypter.AsymmetricKeyGenerator;
import com.calatheas.encrypter.SymmetricEncrypter;
import com.calatheas.encrypter.SymmetricKeyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class AsymmetricEncrypterTest {
    @Test
    public void generateKeyPair() throws NoSuchAlgorithmException {
        AsymmetricKeyGenerator.generateAndPrintKey();
    }

    @Test
    public void encrypt() {
        String publicKeyBase64Encoded = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtarGW6KXONPQZn2bJxSSV384B4ehGK37kfUMgC5bUnVTuB0ahg3lj3pw5s2TlA0zWrRpQnHziMxln2SodUyIfoAQOyvKX+d+ORheR7ysaDCC0BvuQ41TRbRIHn0aCuLxISo9sSEED9w7NEMRWwrUPGCDjX38PedvwbUGN4qgIwtzltSwR2EYz4WoDNcFh7f6i94rouxnFvWANaXez7j485MwP+JSLeIwaKuEfvk5DzmCOlzU+f9OCBIKnc0YF3+Bw3aM2ek3rIyVyl3Hu88hAgO3pQVLABHhF1dwAjDilLqE4qb+lFbM1wHYEjjzNOAwwibqyyD4V7Mv1I5NX+SGbQIDAQAB";
        String privateKeyBase64Encoded = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1qsZbopc409BmfZsnFJJXfzgHh6EYrfuR9QyALltSdVO4HRqGDeWPenDmzZOUDTNatGlCcfOIzGWfZKh1TIh+gBA7K8pf5345GF5HvKxoMILQG+5DjVNFtEgefRoK4vEhKj2xIQQP3Ds0QxFbCtQ8YIONffw952/BtQY3iqAjC3OW1LBHYRjPhagM1wWHt/qL3iui7GcW9YA1pd7PuPjzkzA/4lIt4jBoq4R++TkPOYI6XNT5/04IEgqdzRgXf4HDdozZ6TesjJXKXce7zyECA7elBUsAEeEXV3ACMOKUuoTipv6UVszXAdgSOPM04DDCJurLIPhXsy/Ujk1f5IZtAgMBAAECggEBAKTGUAI/mDAIFwdPOFIbG51XG8fgoiLTc9HwVTVSVqyVFKlzZGJg6m3YHd2KaygnTQQhjTOiTcUvt6D6WL2D/G3Y1GAHGRkhzGAXFTS2Eh6ymYQPQW5uGjCoE53r1Jo5O4LcYHZvvtJv7Bc8GdrdGKHm3IuS0R6I89nbytbH2vXM3S6YzgYImP/k0h1X2uy59gU6Dou7wtDLxzcey0Ur4rFoyiiGoyUkRMXo0tmm1LWHPzCO+6f3vJSe4LfGkyAR9YqeNBRw7L6SJLDR8SP7KycKMPIvT/yaCsnnmOZSpozFJxe/o1cOQlNW8d6AwYLpmt+64oNl3Nsb2V8+ZEa57eECgYEA7aBPZBDdYb/eFT9Ah8eDl06qP63Z4qDqpq4a7HxP9Lt87VC+zEPIng/3WzThP0XbX4g1n2R2w4iHTeOfiYk3s2oQQMe/qngamfsQhtfMAEQgKekOXwdICP/6ViVDo6TCesqCvGMHwVZOeSeJUbQnyu5wDz4eJSgcLeTSmxzSEeUCgYEAw7bISgsILKo5p6WDv15hMNeFxlpCjkK4yVmObLdwpgBV2L5o8X/vWYPlmBE3ltpOUp8BTAa5KAinrSoejqbtVFJ79fymjRukzQos2tCa6kMDs+yIpjr5DmKo0A5X2DI6WpB99hmL4h80pHnMK8PLuzEhfB5eCv3WId/ISoF5eekCgYBGhb9KGV86dXD5qU4DfgjlzOjmATia89rwINfIu5QXM9V9sOsPQEa38lEc9nKrDpZuocQYS/6bRVeynA1rChhH3QB52U5ez0pFzKc4/a6DnbRV+97SAR013pJzIpgSZwF448UtF87jaDPd7QfqAaeVqHIFdBAKQv8p97Exa7pEqQKBgBH+FtG/y+OXTq+b5s3MFnFQDwtaNu4vMh/IrPBPuPp542qGrs0s7ssy9gOR6aNjtHmiBpN/trv4d7nSunZw4Da1UpIdxQ4J8Y+sFM2VCfjpaki7QSDPISByb4SZHPhSPc0fpYTfel2ycruAOwX2/R7beHGZtRmblnkMwrQezRUpAoGBAJn77baiFTW5pv/+NZ4W8hHfyixSoHmei9TuUsWWrKqdLM94qJgctwccKg81Hqk6rIuTPG4I08gCZgfEUmMe1Ee87mElV/CFE1mfQ338kZl7sD8frRkBH4ARmxVXtqmbwiJIGlS3AwWtjnCEwZuhC0i0mc+jdJeLCopDmajVmO0p";

        AsymmetricEncrypter encrypter = new AsymmetricEncrypter(publicKeyBase64Encoded, privateKeyBase64Encoded);

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
