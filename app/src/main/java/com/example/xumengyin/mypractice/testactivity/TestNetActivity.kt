package com.example.xumengyin.mypractice.testactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

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
        button1.setOnClickListener { prestener.cpsdnaInit("南京") }
        button2.setOnClickListener { initCpsdna() }
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

                    L.logE("net", "ViewModel got it  ${text}") }
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
                    for ((i,value) in dataList.withIndex() ) {
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
