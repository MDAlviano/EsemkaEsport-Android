package com.lks.esemka.esport.activity.mainscreen.tim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lks.esemka.esport.R
import com.lks.esemka.esport.activity.mainscreen.MainScreenContainer
import com.lks.esemka.esport.activity.mainscreen.pemain.PemainFragment

class TeamFragment : Fragment() {

    private lateinit var usernameTxt: TextView
    private lateinit var toPemainFragment: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        initComponents(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents(view)
        onClickAction()

        val username = arguments?.getString(MainScreenContainer.USN_KEY) ?: "Guest"
        usernameTxt.text = "Halo $username 👋"
    }

    private fun initComponents(view: View) {
        usernameTxt = view.findViewById(R.id.nameTeamFragmentTxt)
        toPemainFragment = view.findViewById(R.id.pemainBtn)
    }

    private fun onClickAction() {

        toPemainFragment.setOnClickListener {
            val pemainFragment = PemainFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager
                .beginTransaction().apply {
                    replace(
                        R.id.main_screen_container,
                        pemainFragment,
                        PemainFragment::class.java.simpleName
                    )
                    addToBackStack(null)
                    commit()
                }
        }

    }

}