package com.hzy.wan


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.hzy.baselib.base.BaseActivity
import com.hzy.wan.fragment.ProjectsFragment
import com.hzy.wan.mvp.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), BottomNavigationBar.OnTabSelectedListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private var fragmentManager: FragmentManager? = null
    private var homeFragment: HomeFragmentMVP? = null
    private var officialAccountsFragment: OfficialAccountsFragmentMVP? = null
    private var projectsFragment: ProjectFragmentMVP? = null
    private var systemFragment: SystemFragmentMVP? = null
    private var naviFragment: NaviFragmentMVP? = null
    private var mCurrentFragment: Fragment? = null

    override fun initView() {
        removeTitleBar()
       // fragmentManager = supportFragmentManager
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
        bottomNavigationBar
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_01, "首页"))
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_02, "公众号"))
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_03, "体系"))
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_04, "项目"))
                .addItem(BottomNavigationItem(R.mipmap.icon_nav_04, "导航"))
                .setActiveColor(R.color.theme)
                .setFirstSelectedPosition(0)
                .setBarBackgroundColor(R.color.white)
                .initialise()
        bottomNavigationBar.setTabSelectedListener(this)
        initFragment()
    }


    override fun initData() {
    }


    override fun onTabReselected(position: Int) {
    }

    override fun onTabUnselected(position: Int) {
    }

    override fun onTabSelected(position: Int) {
        when (position) {
            0 -> switchFragment(homeFragment!!)
            1 -> switchFragment(officialAccountsFragment!!)
            2 -> switchFragment(systemFragment!!)
            3 -> switchFragment(projectsFragment!!)
            4 -> switchFragment(naviFragment!!)
            else -> {

            }
        }
    }

    fun initFragment() {
        if (homeFragment == null) {
            homeFragment = HomeFragmentMVP()
        }
        if (officialAccountsFragment == null) {
            officialAccountsFragment = OfficialAccountsFragmentMVP()
        }
        if (projectsFragment == null) {
            projectsFragment = ProjectFragmentMVP()
        }
        if (systemFragment == null) {
            systemFragment = SystemFragmentMVP()
        }
        if (naviFragment == null) {
            naviFragment = NaviFragmentMVP()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, homeFragment!!, "1").hide(homeFragment!!)
        transaction.add(R.id.container, officialAccountsFragment!!, "2").hide(officialAccountsFragment!!)
        transaction.add(R.id.container, projectsFragment!!, "3").hide(projectsFragment!!)
        transaction.add(R.id.container, systemFragment!!, "4").hide(systemFragment!!)
        transaction.add(R.id.container, naviFragment!!, "5").hide(naviFragment!!)
        transaction.show(homeFragment!!).commit()
        mCurrentFragment = homeFragment!!
    }

    fun switchFragment(fragment: Fragment) {
        val manage = supportFragmentManager
        val transaction = manage.beginTransaction()
        if (mCurrentFragment != fragment) {
            if (!fragment.isAdded) {
                transaction.add(R.id.container, fragment)
            }
            transaction.hide(mCurrentFragment!!)
            transaction.show(fragment)
            transaction.commit()
            mCurrentFragment = fragment
        }
        //transaction.addToBackStack("sss")
        //Log.e("MainActivity","backStackEntryCount: "+manage.backStackEntryCount);
    }
}
