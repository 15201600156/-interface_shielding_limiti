package com.study.limit;

import sun.awt.SunHints;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Retention 应用到一个注解上的时候，它解释说明了这个注解的的存活时间
 *            RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
 *            RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
 *            RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
 * @Documented 顾名思义，这个元注解肯定是和文档有关。它的作用是能够将注解中的元素包含到 Javadoc 中去。
 * @Target    Target 是目标的意思，@Target 指定了注解运用的地方。 你可以这样理解，当一个注解被 @Target 注解时，这个注解就被限定了运用的场景。
 *            ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 *            ElementType.CONSTRUCTOR 可以给构造方法进行注解
 *            ElementType.FIELD 可以给属性进行注解
 *            ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 *            ElementType.METHOD 可以给方法进行注解
 *            ElementType.PACKAGE 可以给一个包进行注解
 *            ElementType.PARAMETER 可以给一个方法内的参数进行注解
 *            ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 *@Inherited  Inherited 是继承的意思，但是它并不是说注解本身可以继承，而是说如果一个超类被 @Inherited 注解过的注解进行注解的话，那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。
 *@Repeatable 在需要对同一种注解多次使用时，往往需要借助@Repeatable。
 *
                @Repeatable(Limits.class)
                @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE,ElementType.TYPE})
                @Retention(RetentionPolicy.RUNTIME)
                @Documented
                public @interface Limit {
                String role() default "";
                }


                @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE,ElementType.TYPE})
                @Retention(RetentionPolicy.RUNTIME)
                @Documented
                @interface Limits {
                Limit[] value();
                }

                @Limit(role = "admin")
                @Limit(role = "user")
                class Man {
                String name="";
                }
                我理解的时：Repeatable里面就是指定一个集合。而这个集合当中套用的是当前的这个注解。  也就是说新建一个当前注解的集合类的注解，并将@Repeatable指向这个集合
 *
 *
 *
 */



@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limit {
    //默认允许10秒钟内访问10次
    int value() default 10;
    /**
     * 防重复操作过期时间（借助redis实现限时控制）
     */
    long expireSeconds() default 10;
}

//@Repeatable(Limits.class)
//@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE,ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//public @interface Limit {
//    String role() default "";
//}
//
//
//@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE,ElementType.TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@interface Limits {
//    Limit[] value();
//}
//
//@Limit(role = "admin")
//@Limit(role = "user")
//class Man {
//    String name="";
//}
