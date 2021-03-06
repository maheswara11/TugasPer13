package id.wahyatma.brillian.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.wahyatma.brillian.ui.biayaOngkir.BiayaFragment
import id.wahyatma.brillian.ui.cekresi.CekResiFragment

class HomeTabAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle){

    private val fragment: ArrayList<Fragment> = arrayListOf(
        BiayaFragment(), CekResiFragment()
    )

    override fun getItemCount(): Int {
        return fragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }

}