package demo.minifly.com;

import org.junit.Assert;
import org.junit.Test;

import demo.minifly.com.utils.Common;

/**
 * author ：minifly
 * date: 2017/6/30
 * time: 10:44
 * desc:  在as中，直接使用 ctr+shift+t 可以生成一个测试类，在运行的时候使用ctr+shift+F10快速运行测试用例
 * 而正常运行是shift+f10
 */
public class CommonTest {
    @Test
    public void addSum() throws Exception {
        Assert.assertEquals(3,Common.getInstance().addSum(1,2));
    }

}