package com.chernov.ivan.myroutines

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.chernov.ivan.myroutines.dummy.DummyContent
import com.chernov.ivan.myroutines.list.ItemFragment
import com.chernov.ivan.myroutines.list.ItemFragmentDirections
import com.chernov.ivan.myroutines.list.ProgramFragment
import com.chernov.ivan.myroutines.list.ProgramFragmentDirections
import com.chernov.ivan.myroutines.model.Program
import com.chernov.ivan.myroutines.model.ProgramItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener, ProgramFragment.OnListFragmentInteractionListener_program {

    //--todo Safeargs
    //--todo data class
    //--todo choose build-in intent
    //--todo tweack actions.xml

    //todo impliment deep link //Define the urlTemplate in the format you define your deeplinks in AndroidManifest.xml

    //todo rename to routine and steps
    //todo data class to RV


//todo Android Tutorial: Convert Speech To Text | Speech Recognition
    //todo prepare for Voice
    //todo open a program by Voice
    //todo read stuff


//todo add LivecyclerObserver ???
//todo change FAB icon
//todo • Restore state after orientation change
//todo add mp3 to item

    //todo In the Navigation component, you can initialize a ViewModel with a navigation graph scope. This means all the fragments in the same navigation graph and their parent Activity share the same ViewModel. + https://www.raywenderlich.com/4332831-navigation-component-for-android-part-2-graphs-and-deep-links


    private val navController by lazy { findNavController(R.id.nav_host_fragment) } //1

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

    override fun onListFragmentInteraction(item: ProgramItem?) {

        val action
                = ItemFragmentDirections.actionItemFragmentToDetailedFragment()
        action.test1 = "Selected2"
        action.text2 = "1" + item.toString()
        navController.navigate(action)


//        val args = Bundle()
//        args.putString("text1", "Selected")
//        args.putString("text2", item.toString())
//        findNavController(R.id.nav_host_fragment).navigate(R.id.action_itemFragment_to_detailedFragment, args)
    }

    override fun onListFragmentInteraction_program(item: Program?) {
//        TODO("VIEW MODEL")

        val action
                = ProgramFragmentDirections.actionProgramFragmentToItemFragment()
//        action.test1 = "Selected2"
//        action.text2 = "1" + item.toString()
        navController.navigate(action)
    }
}

