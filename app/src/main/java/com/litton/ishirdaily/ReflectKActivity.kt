package com.litton.ishirdaily

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.litton.ishirdaily.bean.Person

class ReflectKActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val tag = "ReflectKActivity"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflect)

        /**
         * 获取Class对象的方法
         * 1.调用类的class属性
         * 2.调用对象的Class()方法
         * 3.使用Class类中的forName()静态方法(最安全/性能最好)
         */

        // 1.调用类的class属性
        val personClass = Person::class.java
        // 2.调用对象的Class()方法
        val person = Person("ishir",18)
        val personClass1 = person::class.java
        val personClass2 = person.javaClass
        // 3.使用Class类中的forName()静态方法(最安全/性能最好)
        val personClass3 = Class.forName("com.litton.ishirdaily.bean.Person")

        /**
         * 访问构造(Constructor)
         * 1.获取 Constructor:
         * 　　　1.1.getConstructor  获取所有"公有的"构造方法.
         * 　　　1.2.getDeclaredConstructor (获取所有的构造方法(包括私有、受保护、默认、公有)
         * 2.实例化对象
         * 　　　2.1.getConstructor().newInstance()
         * 　　　2.2.getDeclaredConstructor(xxx.::class.java,xxx.::class.java,···).newInstance(xxx,xxx,···)
         */

        val personNew1 = personClass3.getConstructor(String::class.java, Int::class.java).newInstance("holly", 25) as Person
        val personNew2 = personClass3.getDeclaredConstructor(String::class.java, Int::class.java).newInstance("andy", 22) as Person

        /**
         * 访问属性(Field)
         * 1.获取 Field: getField(String fieldName)
         * 2.获取 Field 的值:
         * 　　　　2.1.setAccessible(true)
         * 　　　　2.2.field.get(Object obj)
         * 3.设置 Field 的值:
         * 　　　　field.set(Obejct obj, Object val)
         */

        //在访问私有的属性时 加上isAccessible=true,就是告诉java运行时你不要管我,我就是要访问
        personClass1.getDeclaredField("name").apply { isAccessible = true }.set(personNew1,"lynn")
        val personNew1name:String = personClass1.getDeclaredField("name").apply { isAccessible = true }.get(personNew1) as String
        val personNew1age = personClass1.getDeclaredField("age").apply { isAccessible = true }.get(personNew1)
        Log.e(tag, "$personNew1name:$personNew1age")

        /**
         * 访问方法(Method)
         * 1.获取 Method:
         * 　　　1.1.getDeclaredMethods: 得到 Method 的数组.
         * 　　　1.2.getDeclaredMethod(String methondName, Class ... parameterTypes)
         * 2.调用 Method
         * 　　　2.1.方法被private 修饰,需先调用 Method.setAccessible(true),使其可访问
         * 　　　2.2. method.invoke(obj, Object ... args);
         */

        val personNew2name = personClass1.getDeclaredMethod("getName").invoke(personNew2)
        val person1 = personClass1.getDeclaredMethod("copy",String::class.java,Int::class.java).invoke(personNew2,personNew2.name,personNew2.age)
        Log.e(tag,"$personNew2name")
        Log.e(tag,"$person1")
        val invoke = personClass1.getDeclaredMethod("sayHello",String::class.java).invoke(personNew2,"windy")
        Log.e(tag,"$invoke")

    }
}
