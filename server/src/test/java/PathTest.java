
import java.io.File;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yneos
 */
public class PathTest {

    @Test
    public void Test() {
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println(PathTest.class.getClassLoader().getResource(""));
        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(PathTest.class.getResource(""));
        System.out.println(PathTest.class.getResource("/"));//Class文件所在路径 
        System.out.println(new File("/").getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));
    }
}
