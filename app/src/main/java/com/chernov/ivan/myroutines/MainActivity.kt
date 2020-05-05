package com.chernov.ivan.myroutines

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.chernov.ivan.myroutines.dummy.DummyContent
import com.chernov.ivan.myroutines.list.ItemFragment
import com.chernov.ivan.myroutines.list.ItemFragmentDirections

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener {

    //todo Safeargs
    //todo data class
    //todo name of params it action to Const

    //todo dataClass? program name, text
    //todo prepare for Voice


//todo add LivecyclerObserver ???
//todo change FAB icon
//todo • Restore state after orientation change


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {

        val action = ItemFragmentDirections.actionItemFragmentToDetailedFragment()
        action.test1 = "Selected2"
        action.text2 = "1"+item.toString()
        findNavController(R.id.nav_host_fragment).navigate(action)


//        val args = Bundle()
//        args.putString("text1", "Selected")
//        args.putString("text2", item.toString())
//        findNavController(R.id.nav_host_fragment).navigate(R.id.action_itemFragment_to_detailedFragment, args)
    }
}

