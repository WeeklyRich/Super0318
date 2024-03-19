package Test.ZhujieXuexi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author ASUS
 */
@Target ( ElementType.METHOD )
@Retention ( RetentionPolicy.RUNTIME )
public @interface Zhujie1 {
/*
@Target -> 指定注解针对的地方
    @Target ( ElementType.TYPE )                针对类和接口
    @Target ( ElementType.METHOD )              针对成员方法
    @Target ( ElementType.CONSTRUCTOR )         针对构造器
    @Target ( ElementType.ANNOTATION_TYPE )     针对注解
    @Target ( ElementType.FIELD)                针对成员变量
    @Target ( ElementType.PARAMETER )           针对方法参数
    @Target ( ElementType.PACKAGE)              针对包



@Retention —> 指定注解的集成域
    @Retention ( RetentionPolicy.SOURCE )       源代码级别，由编译器处理，处理之后就不再保留
    @Retention ( RetentionPolicy.CLASS )        注解信息保留到对应的class文件中
    @Retention ( RetentionPolicy.RUNTIME )      由JVM读取，运行时使用


 */
}
