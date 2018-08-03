package com.litton.ishirdaily;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.litton.ishirdaily.bean.Games;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectActivity extends AppCompatActivity {

    /**
     * Class: 是一个类; 一个描述类的类.
     * 封装了描述构造器的 Constructor,
     * 描述字段的 Filed,
     * 描述方法的 Method.
     */

    private static final String TAG = "ReflectKActivity";
    private Class<?> gameClass3;
    private Games games1;
    private Games games2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);

        /**
         * 获取Class对象的方法
         * 1.调用类的class属性
         * 2.调用对象的getClass()方法
         * 3.使用Class类中的forName()静态方法(最安全/性能最好)
         */

        // 1.调用类的class属性
        Class<Games> gameClass1 = Games.class;
        // 2.调用对象的getClass()方法
        Games games = new Games("贪玩蓝月", "端游");
        Class<? extends Games> gameClass2 = games.getClass();
        // 3.使用Class类中的forName()静态方法(最安全/性能最好)
        try {
            gameClass3 = Class.forName("com.litton.ishirdaily.bean.Games");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * 访问构造(Constructor)
         * 1.获取 Constructor:
         *　　　1.1.getConstructor  获取所有"公有的"构造方法.
         *　　　1.2.getDeclaredConstructor (获取所有的构造方法(包括私有、受保护、默认、公有)
         * 2.实例化对象
         *　　　2.1.getConstructor().newInstance()
         *　　　2.2.getDeclaredConstructor(xxx.class,xxx.class,···).newInstance(xxx,xxx,···)
         */

        try {
            games1 = (Games) gameClass3.getConstructor(String.class, String.class).newInstance("阴阳师", "和风卡牌");
            games2 = (Games) gameClass3.getDeclaredConstructor(String.class, String.class).newInstance("镇魔曲", "武侠格斗");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        /**
         * 访问属性(Field)
         * 1.获取 Field: getField(String fieldName)
         * 2.获取 Field 的值:
         *　　　　2.1.setAccessible(true)
         *　　　　2.2.field.get(Object obj)
         * 3.设置 Field 的值:
         　　　　field.set(Obejct obj, Object val)
         */

        try {
            Field f = gameClass1.getDeclaredField("name");
            f.setAccessible(true);
            f.set(games1, "「影之诗」");
            Log.e(TAG, "Modify field: " + f.get(games1));

            Field[] fields = gameClass1.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Log.e(TAG, "field: " + field.get(games1));
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        /**
         * 访问方法(Method)
         * 1.获取 Method:
         *　　　1.1.getDeclaredMethods: 得到 Method 的数组.
         *　　　1.2.getDeclaredMethod(String methondName, Class ... parameterTypes)
         * 2.调用 Method
         *　　　2.1.方法被private 修饰,需先调用 Method.setAccessible(true),使其可访问
         *　　　2.2. method.invoke(obj, Object ... args);
         */

        try {
            gameClass1.getMethod("setName", String.class).invoke(games1, "「Suger fee」");
            Log.e(TAG, "Game: " + games1.toString());

            Method[] methods = gameClass1.getMethods();
            for (Method method : methods) {
                String name = method.getName();
                Log.e(TAG, "Method List: " + name);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
