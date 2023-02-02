import com.calatheas.encrypter.AsymmetricEncrypter;
import com.calatheas.encrypter.Encrypter;
import com.calatheas.encrypter.KeyGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AsymmetricEncrypterTest {
    String publicKeyHexString = "30820122300D06092A864886F70D01010105000382010F003082010A028201010090757DE73254F66A962DA7627DED9C95D93B62A70F0C1C9D48469B932AE8F8C97CCA5A07058F76E50BB2FF2F3FDCDF86FAA458308C2CD914B36EAD51616D3C3A1C82AE50D179439FAF873958815903D82FB68754A22218BD6352EAC00EC25CC1AA533A0D9F73FBFE2EB261D635E20E0B33137F1FC4E62AF6A53FD36492B9326C538FD2DE1B3B3A0AA87F5C61958FB00E9E70D5469584CFA8BFC868893D87C2EDE955DF7E32F77A1766C1CA0CCD63BE5926F23D23B2AF6C424CE4E4758ED7BD87B49C21292DDE14D4ED3D79C119A52A20CC4D308C2983F31C4BCD8B1DC6EDE1DEA59B12D576C5D2AD27BC10D3FFDDC2D8469CB2449196F6818FF1ABBF180B25DB0203010001";
    String privateKeyHexString = "308204BD020100300D06092A864886F70D0101010500048204A7308204A3020100028201010090757DE73254F66A962DA7627DED9C95D93B62A70F0C1C9D48469B932AE8F8C97CCA5A07058F76E50BB2FF2F3FDCDF86FAA458308C2CD914B36EAD51616D3C3A1C82AE50D179439FAF873958815903D82FB68754A22218BD6352EAC00EC25CC1AA533A0D9F73FBFE2EB261D635E20E0B33137F1FC4E62AF6A53FD36492B9326C538FD2DE1B3B3A0AA87F5C61958FB00E9E70D5469584CFA8BFC868893D87C2EDE955DF7E32F77A1766C1CA0CCD63BE5926F23D23B2AF6C424CE4E4758ED7BD87B49C21292DDE14D4ED3D79C119A52A20CC4D308C2983F31C4BCD8B1DC6EDE1DEA59B12D576C5D2AD27BC10D3FFDDC2D8469CB2449196F6818FF1ABBF180B25DB02030100010282010008E0EFC811F3DEE15631A0241191551A70AB91B8AAD817BA230313F4CBA25E08243F5BA12ADA5278696BAAFB807556D7B8F7F8E3A2F9EEADB3098E657E1374AC2DAC6A82551D9AE669A76A02E29052082B869072C7344948FA9DD207D3E0664B10E8E49C89BC5B34983226893FEE90E667513F72F86B1D20E3AF3492EDE1AC139CAA1205392508A025468764BDF5994563BD1A81AF1A821CEA85B04CE4118D9416048DC3477E2C16394DDA18636AA9D3922005221AA6119BD2CA95188B0E94C12757ADBA2BF7DBF89409B4EB605A7B96CCAF751F5737B4662F8D0AACD911A20624167CF3D2BE4A88F18C94ECBFCC2162AE69C5BC63FD39BB12ED0115165DF34102818100CF7E1A69EFF10178577985BDC1BA215A60A57C7335BF9EE6AA1157EFDDAA1780D50B59C497F08A9645BE8A3FA6652FFB73DFB76271D8F66A49BEA001CA7B4E8EEDCCD999A0644ACE2D34A019AB1613C80C108275412B0FB0D0ABFE7984BE1D7B5D608D39F26EEE057CD3D88ADB42F2504BB8E0462928D2E3E44E7547DE23D75302818100B23AFCE24CB41E4FD8191DDE3652AECCD62B0B9EA5EC435A528FFD5E5F33BEE59AFCBEAE570ED7D6345421270CDEB316032D84594A7CB4430381491571084C6A271B3934F1415B288F9663E19144DBCB630C847944F1B2FF70C1D22D8DC95FBB1BB335E3BC223035024312D4AA03BB81A327B00B848BC60B329FA734E8DE4E5902818100A06F8F18EEB05944A5CE8EFCE47879259AA91A8B32BA11B1A53A77D63E7D30632B57F5772F4C8CBC18FD758435B2F3E0E7FDC99C530F41A9798FEDC4B4BD9746A8D4C6442562A6FCB8B3823F96AC216B82F89906F4F6C8D11CE88081918436E39695FB5D1CF96D0DCD3138B45549747765B573A48C5AC685F3983ED2ED0EA5850281804DEF0F3F210D2DA7FA3559631E5BDF45806CC822F228B276DF5D490C2812E7695CF121ABF8336C5BA4FC18970BC0A18C818D38AF16AC1AE2A6B8B4AD64849D6D597644F88A79EA937CDDE504F051ACCE6D9C3E9D0C71A7B49CA893F0096CC1A87E7F49943062BEB39F2E972058CB530567C365EE53620F123C6BA560B466E8D902818025B9F08A2C542C39C92BBDC295D11C67C2EE2C6D9366DE6050EE4BD8377ECC1FE9DDE1ACBBBA48E8A074C793A136F04AAE12BECA1A7CE83A20902EA2F391CD6A6C766D5CFEE72305D91DD83B639D1F29C6BDDFF4822E8815A7CB8AC8CE6A0B971B6172837ACA7B21CE9661680B344E3BC8BD7FBA2B8344E5B18CD91C79DBD9C2";

    Encrypter encrypter = new AsymmetricEncrypter(publicKeyHexString, privateKeyHexString);

    @Test
    @Disabled
    public void generateKeyPair() {
        KeyGenerator.generateKeyPair();
    }

    @Test
    public void encrypt() {
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
