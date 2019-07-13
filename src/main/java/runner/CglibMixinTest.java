package runner;

import helper.Impl1;
import helper.Impl2;
import helper.Interface1;
import helper.Interface2;
import net.sf.cglib.proxy.Mixin;

public class CglibMixinTest {
    public static void main(String[] args) {
        Mixin mixin = Mixin.create(new Object[]{new Impl1(), new Impl2()});
        System.out.println(((Interface1) mixin).func1());
        System.out.println(((Interface2) mixin).func2());
    }
}
