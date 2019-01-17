package com.example.xumengyin.mypractice.testactivity

import android.app.Activity
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.test.MoreAsserts
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast 
import com.example.xumengyin.mypractice.Data
import com.example.xumengyin.mypractice.R
import com.example.xumengyin.mypractice.R.id.auto_text
import com.example.xumengyin.mypractice.kotlin.*
import kotlinx.android.synthetic.main.activity_auto_text.*
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.longToast
import java.time.Duration
import java.util.ArrayList
import kotlin.properties.Delegates

/**
 * Created by Administrator on 2018/5/9.
 *   by lazy 和 lateinit 的区别
by lazy 修饰val的变量
lateinit 修饰var的变量，且变量是非空的类型

拓展属性和拓展函数
with函数，函数块内可以用this
apply函数，与with类似，调用后，直接使用this
 */


class KotlinActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
//        when (v.id)
//            ->R.id.auto_text
    }
    var helloProxy by ProexyTest()
    lateinit var listUser: List<User>
    var name = this.javaClass.simpleName
    val vTextView: TextView by lazy { findViewById<TextView>(R.id.auto_text) }
    val vCusTextView: TextView by lazy { TextView(this) }
    var vTextView2: TextView? = null
    override fun getcontentView(): Int {
        return R.layout.activity_auto_text;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var builder: StringBuilder = StringBuilder("haha")
        builder.getlast = 'd'

        //Kotlin中类型分为可空类型和不可空类型，通过？代表可空，不带？代表不可为空
        /**
         * 对于一个不可为空类型：如果直接给不可为空类型赋值一个可能为空的对象就在编译阶段就不能通过
        对于一个可空类型：通过？声明，在访问该类型的时候直接访问不能编译通过，需要通过?.或者!!.
        ?. 代表着如果该类型为空的话就返回null不做后续的操作，如果不为空的话才会去访问对应的方法或者属性
        !!. 代表着如果该类型为空的话就抛出NullPointerException，
        如果不为空就去访问对应的方法或者属性， 所以只有在很少的特定场景才用这种符号，代表着程序不处理这种异常的case了，会像java代码一样抛出NullPointerException。 而且代码中一定不用出现下面这种代码，会让代码可读性很差而且如果有空指针异常，我们也不能马上发现是哪空了：
         */
        vTextView2 = findViewById(R.id.auto_text)
        //通过let语句，在?.let之后，如果为空不会有任何操作，只有在非空的时候才会执行let之后的操作

        var textSize = vTextView2?.let {
            it.text = name
            it.textSize
        }
        vTextView.let {
            //貌似可以当当前属性的代码块来用
        }
        //Elvis操作符?:能够简化上面的操作，
        // ?:符号会在符号左边为空的情况才会进行下面的处理。跟?.let正好相反，例如我们可以用两行代码来简化上面从操作：

        vTextView2?.text = name ?: null
        //导入插件可以直接使用
        auto_text.setText(name)
        auto_text.setOnClickListener(View.OnClickListener { it.id })
        var config:Config = Config(mapOf("af" to "","af" to "","af" to ""))
        //ManagedSQLiteOpenHelper.
        testCollection()
        helloProxy.logitself()
        helloProxy="nimei"
        helloProxy.logitself()
        var mapzz = mutableMapOf("str1" to 1,"str2" to 2)
        var zz :Int by mapzz
    }

    var StringBuilder.getlast: Char
        get() = get(length - 1)
        set(value) {
            setCharAt(length - 1, value)
        }

    fun testWith(): String =
            with(StringBuilder())
            {
                toString()
            }

    fun testApply(): String {
        return StringBuilder().apply {
            append("1")
        }.toString()
    }

    fun Context.toast(msg: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, msg, duration).show()
    }

    /**
     * 高阶函数：以另一个函数作为参数或者返回值的函数
    函数类型
    (Int, String) -> Unit
    参数类型->返回类型 Unit不能省略
     */
    val funsum = { x: Int, y: Int -> x + y }
    val funsum2 :(Int,Int)->Int={x:Int,y:Int ->x+y}
    val funsum3={toast("fun")}
    fun testFun() {
            funsum(2,3)
            funsum2(2,3)
            funsum3()
            //传参数进来
            testFun2(funsum2)
           val sunfun= testFun3(111,12)
            sunfun(9)

    }
    // 函数作为参数传进来
    fun testFun2(funfun:(Int,Int)->Int)
    {
       val  k=funfun(10,10)
        toast("${k}")
    }

    //返回类型是函数，用大括号括起来,vararg可变长长度
    fun testFun3(vararg k:Int):(Int) -> String
    {
        return {"1"}
    }
    fun getShippingCostCalculator(): (Int) -> Double {
//        if (delivery == Delivery.EXPEDITED) {
//            return { 6 + 2.1 * it }
//        }
        return { 1.3 * it }
    }

    class Config(maps:Map<String,String>)
    {
        val config1:String by maps
    }
    class DbHelp(ctx: Context) : ManagedSQLiteOpenHelper(ctx,DbHelp.name,null, DbHelp.version)
    {

        companion object {
            val name:String="db"
            val version:Int=1
        }
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onCreate(db: SQLiteDatabase?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
    fun testCollection()
    {
        var list= listOf<User>(User("xu",1),User("m",2))
        list.count { it.age>0 }
        list.forEach { it.age=3 }
        list.forEachIndexed { index, user ->user.age=index  }
        //list.maxBy {  }
        list.takeLast(3)
        var listhaha= listOf<Int>(1,2,3,4,5,6,7,8,10,11)
        //listhaha.getOrElse()
        //list.elementAtOrElse()
        var kk=listhaha.firstOrNull { it>10 }
        var pari:Pair<List<User>, List<User>> =list.partition { it.age>10 }
        listhaha.bianli()
        kk.logitself()
        //遍历
        for(i in list)
        {
            i.logitself()
        }
        for(i in list.indices)
        {
            list[i].logitself()
        }
        for(i in 0..10)
        {

        }
        for(i in 10 downTo 1 step 2)
        {

        }

        (0..10).map {  }

        var f=Feng("string")
        f.name
       var arr:IntArray=  intArrayOf(1,2,3,3)
        arr[1]=3
    }


    fun <T :View> T.clickWrap(name:String ,clickwr:(View)->Unit):Unit
    {
        setOnClickListener { clickwr(it) }
    }
    //扩展方法 打印出来
    fun <T>Iterable<T>.bianli()
    {
        for(ele in this)
        {
            Log.d("Tag",ele.toString())
        }

    }

    fun objectTest()
    {
        DataProvider.setDatat("")
        DataProvider.getDatat()
        companionClass.companion()
        companionClass.fuckdata="2"
        companionClass().companion()

        //复制data类
        val xu=User2("xu",10)
        xu.name=""
        val meng=xu.copy(name="2")

    }
}
fun <T>T.logitself()
{
    Log.d("Tag",this.toString())
}