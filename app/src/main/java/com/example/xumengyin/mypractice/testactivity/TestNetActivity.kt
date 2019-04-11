package com.example.xumengyin.mypractice.testactivity

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent

import com.example.xumengyin.mypractice.R
import com.example.xumengyin.mypractice.bean.Repo

import butterknife.OnClick
import com.example.xumengyin.mypractice.bean.InitBean
import com.example.xumengyin.mypractice.kotlin.DataProvider.data
import com.example.xumengyin.mypractice.kotlin.TestInterface
import com.example.xumengyin.mypractice.kotlin.User2
import com.example.xumengyin.mypractice.net.*
import com.example.xumengyin.mypractice.util.L
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test_net.*
import org.jetbrains.anko.custom.async
import kotlin.math.log

class TestNetActivity : BaseActivity(), GithubContract.View, TestInterface {


    init {
        var array = Array<String>(10) { i -> "" + i }
    }

    var booleanValue: Boolean? = null
    override fun kick() {

    }

    override val name: Boolean
        get() = "1a" is String


    private lateinit var prestener: GitHubPrester
    lateinit var viewModel: TestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prestener = GitHubPrester(this, this)
        viewModel = TestViewModel(application)
        prestener.liveDataMap.observe(this, Observer {
            values-> logMsg("请求num"+values)
        })
        button1.setOnClickListener {
            prestener.cpsdnaInit("南京")
        }
        button2.setOnClickListener { initCpsdna() }
        viewModel.liveDataMap.observe(this, Observer { value->logMsg("viewModel 请求次数"+value) })
        val user = com.example.xumengyin.mypractice.kotlin.User(false)
        val User2 = User()
        with(User2)
        {

        }
        main()
        booleanValue?.let {
            booleanValue = true;
        }
    }
    //俩个View 的公共父亲view
//    fun souGou(view1:View,view2:View):ViewParent?
//    {
//        var list1=getParentView(view1)
//        var list2=getParentView(view2)
//        var viewParent:ViewParent
//        for(i in Math.min(list1.size,list2.size)  downTo 0)
//        {
//            if(list1[i]==list2[i])
//            {
//                continue
//            }else
//            {
//                if(i>0)
//                {
//                    viewParent=list1[i]
//                }
//            }
//        }
//        return viewParent
//    }
    fun getParentView(view:View):List<ViewParent>
    {
        var viewGroup=view.parent
        var list= mutableListOf<ViewParent>()
        while (viewGroup!=null)
        {
            list.add(viewGroup)
            viewGroup=viewGroup.parent;
        }
        return list
    }
    override fun getcontentView(): Int {
        return R.layout.activity_test_net
    }

//    @OnClick(R.id.button1)
//    fun netPost(view: View) {
//        prestener.cpsdnaInit("南京")
//    }

    override fun rpnoData(repos: List<Repo>) {
        logMsg("repos got it")

    }

    fun User() {

    }

    /**
     * 高阶函数和lambda表达式
     */

    fun main() {
        //sampleStart
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits
                .filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach {
                    val text = """
                            for (c in "fruits")
print(c)
                            """

                    L.logE("net", "ViewModel got it  ${text}")
                }
    }

    /**
     * 问号 代表可为空，str?.toIntOrNull() 不为空返回int，为空返回null，不会空指针了
     */
    fun parse(str: String?): Int? {
        return str?.toIntOrNull()
    }

    private fun initCpsdna() {
        viewModel.initCpsdna("南京").observe(this, object : UiObserver<InitBean>() {
            override fun uiSuccess(data: InitBean?) {
                L.logE("net", "ViewModel got it" + data.toString())

                var dataList = data?.sysCodeDictList?.filter { it.catatoryId != null }
                dataList?.let {
                    for (i in 0..it.size) {
                        it[i].catatoryId
                    }
                    for (i in it.indices) {
                        it[i].catatoryId
                    }
                    for ((i, value) in dataList.withIndex()) {
                        //i.value.catatoryId
                        value.catatoryId
                    }

                }
            }

            override fun uiCache(data: InitBean?) {
                super.uiCache(data)

                L.logE("net", "ViewModel cache got it${data}")
            }
        })
    }
}
