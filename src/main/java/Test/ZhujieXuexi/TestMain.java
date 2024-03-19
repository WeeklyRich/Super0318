package Test.ZhujieXuexi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMain {
    public static void main ( String[] args ) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        Class cl = Class.forName ( "Test.ZhujieXuexi.T1" );

        Method[] methods = cl.getMethods ();

        if(methods != null){
          for(Method method : methods ){
              boolean bl = method.isAnnotationPresent ( Zhujie1.class );
              if(bl){
                  method.invoke ( cl.getConstructor ( null ).newInstance ( null ),null );
//                  这段代码中的 method.invoke(cl.getConstructor(null).newInstance(null), null); 的意思是通过Java反射机制执行一个特定的方法。
//
//                  具体来说：
//                  -  cl.getConstructor(null) ：获取cl类的无参构造函数。
//                  -  newInstance(null) ：使用获取到的无参构造函数创建一个新的cl类的实例。
//                  -  method.invoke(...) ：调用method方法，第一个参数是要调用该方法的对象实例，第二个参数是传递给方法的参数。
//
//                  因此，这行代码的作用是通过反射调用method方法，传递一个新实例化的cl对象作为方法调用的目标，并传递null作为方法的参数。
              }
          }
        }
    }
}
