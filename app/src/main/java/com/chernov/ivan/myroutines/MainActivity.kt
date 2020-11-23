package com.chernov.ivan.myroutines

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.chernov.ivan.myroutines.dialogs.ProgramEditDialog

import com.chernov.ivan.myroutines.model.ProgramEntity
import com.chernov.ivan.myroutines.model.ItemEntity
import com.chernov.ivan.myroutines.util.TAG_D
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    AppCompatActivity(),
    ItemFragment.OnListFragmentInteractionListener,
    ProgramFragment.OnListFragmentInteractionListener_program,
    ProgramFragment.OnListFragmentInteractionListener_program_longClick,
    ProgramEditDialog.ProgramEditDialogListener
{
// TODO: 22.11.2020 Plain Ol'Notes 4 has emptyMainActiviy

    //--todo Safeargs
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

//        fab_mainActivity.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
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

    override fun onListFragmentInteraction_item(item: ItemEntity?,programId: Int) {

        val action
                = ItemFragmentDirections.actionItemFragmentToDetailedFragment(programId)
        action.itemId = item?.id ?: -1
        action.programID = programId
        navController.navigate(action)


//        val args = Bundle()
//        args.putString("text1", "Selected")
//        args.putString("text2", item.toString())
//        findNavController(R.id.nav_host_fragment).navigate(R.id.action_itemFragment_to_detailedFragment, args)
    }

    override fun onListFragmentInteraction_program(program: ProgramEntity?) {
        val action
                = ProgramFragmentDirections.actionProgramFragmentToItemFragment(program!!.id)

//        action.idProgram = program!!.id
//        action.text2 = "1" + item.toString()
        Log.d(TAG_D, "onListFragmentInteraction_program:${program.id}")
        navController.navigate(action)
    }

    override fun onListFragmentInteraction_program_longClick(program: ProgramEntity?) {
        val action
                = ProgramFragmentDirections.actionProgramFragmentToProgramEditDialog(program!!.id,
            program.name)

        navController.navigate(action)
    }

    override fun onProgramEditDialogResult(programId: Int) {
        Toast.makeText(applicationContext, programId, Toast.LENGTH_SHORT).show()
    }




//    override fun onListFragmentInteraction_program(item: ProgramEntity?) {
//
//
//        val action
//                = ProgramFragmentDirections.actionProgramFragmentToItemFragment(item!!.id)
//
////        action.idProgram = program!!.id
////        action.text2 = "1" + item.toString()
//        navController.navigate(action)
//    }
}

