package com.example.MyRoutine2

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MyRoutine2.databinding.ProgramsFragmentBinding

import com.example.MyRoutine2.fastadapter.IDraggableViewHolder
import com.example.MyRoutine2.fastadapter.SwipeableDrawerProgram
import com.example.MyRoutine2.model.ItemEntity
import com.example.MyRoutine2.model.ProgramEntity
import com.example.MyRoutine2.viewmodel.ProgramsFragmentViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.drag.ItemTouchCallback
import com.mikepenz.fastadapter.drag.SimpleDragCallback
import com.mikepenz.fastadapter.listeners.ClickEventHook
import com.mikepenz.fastadapter.swipe.SimpleSwipeDrawerCallback
import com.mikepenz.fastadapter.swipe_drag.SimpleSwipeDrawerDragCallback
import com.mikepenz.fastadapter.utils.DragDropUtil
import io.reactivex.functions.Consumer

//from activity to fragment
class ProgramsFragment : Fragment() , ItemTouchCallback, SimpleSwipeDrawerCallback.ItemSwipeCallback {

    private lateinit var viewModel: ProgramsFragmentViewModel
    private lateinit var binding: ProgramsFragmentBinding

    private lateinit var rv: RecyclerView

    //save our FastAdapter
    private lateinit var fastItemDrawerAdapter: FastItemAdapter<SwipeableDrawerProgram>

    //drag & drop
    private lateinit var touchCallback: SimpleDragCallback
    private lateinit var touchHelper: ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ProgramsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ProgramsFragmentViewModel::class.java)

        rv = binding.recyclerView
//        setContentView(R.layout.activity_sample)

        //123
        // Handle Toolbar
//        setSupportActionBar(toolbar)

        //create our FastAdapter which will manage everything
        fastItemDrawerAdapter = FastItemAdapter()

//        fastItemDrawerAdapter.onClickListener = { v: View?, _: IAdapter<SwipeableDrawerItem>, item: SwipeableDrawerItem, position: Int ->
//            if (v != null) {
//
////                val action = ItemsFragmentDirections
////                    .actionItemsFragmentToTimerFragment(item.identifier)
////                findNavController().navigate(action)
//
//                Toast.makeText(v.context, position.toString(), Toast.LENGTH_SHORT).show()
//            }
//            false
////        }
//        fastItemDrawerAdapter.onClickListener = { view, adapter, item, position ->
//            Toast.makeText(view?.context, position.toString(), Toast.LENGTH_SHORT).show()
//            false
//        }


        //configure the itemAdapter
        fastItemDrawerAdapter.itemFilter.filterPredicate = { item: SwipeableDrawerProgram, constraint: CharSequence? ->
            item.name?.textString.toString().contains(constraint.toString(), ignoreCase = true)
        }

        // just add an `EventHook` to your `FastAdapter` by implementing either a `ClickEventHook`, `LongClickEventHook`, `TouchEventHook`, `CustomEventHook`
        fastItemDrawerAdapter.addEventHook(object : ClickEventHook<SwipeableDrawerProgram>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                //return the views on which you want to bind this event
                return if (viewHolder is SwipeableDrawerProgram.ViewHolder) {
                    viewHolder.itemContent
                } else {
                    null
                }
            }

            override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<SwipeableDrawerProgram>, item: SwipeableDrawerProgram) {
                findNavController().navigate(ProgramsFragmentDirections.actionProgramsFragmentToItemsFragment(item.identifier.toInt()))

            }
        })

        //get our recyclerView and do basic setup
        rv.layoutManager = LinearLayoutManager(context)
        rv.itemAnimator = DefaultItemAnimator()
        rv.adapter = fastItemDrawerAdapter


        viewModel.programsList?.observe(viewLifecycleOwner, {
            if (it.size==0) {
                //fill with some sample data
                viewModel.addSampleData()
            }
            fastItemDrawerAdapter.set(itemToSwipeableDrawerProgram(it ?: emptyList()))
        })


        //add drag and drop for item
        //and add swipe as well
        touchCallback = SimpleSwipeDrawerDragCallback(
            this,
            ItemTouchHelper.LEFT,
            this)
            .withNotifyAllDrops(true)
            .withSwipeLeft(80) // Width of delete button
            .withSwipeRight(80) // Width of edit and share buttons
            .withSensitivity(10f)
            .withSurfaceThreshold(0.3f)

        touchHelper = ItemTouchHelper(touchCallback) // Create ItemTouchHelper and pass with parameter the SimpleDragCallback
        touchHelper.attachToRecyclerView(rv) // Attach ItemTouchHelper to RecyclerView

        //restore selections (this has to be done after the items were added)
        fastItemDrawerAdapter.withSavedInstanceState(savedInstanceState)

        binding.fab.setOnClickListener {
            editCreateProgram(NEW_ITEM_ID_INT,"")
        }

        //123
//        //set the back arrow in the toolbar
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeButtonEnabled(false)

//        return super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    private fun editCreateProgram(programId: Int, programName:String) {

        val action = ProgramsFragmentDirections
            .actionProgramsFragmentToProgramEditDialog(programId,programName)
        findNavController().navigate(action)
    }

    fun itemToSwipeableDrawerProgram(items: List<ProgramEntity>):List<SwipeableDrawerProgram>{

        val listOfSwipeable = mutableListOf<SwipeableDrawerProgram>()
        var x = 0
        items.forEach {

            val swipeableProgram = SwipeableDrawerProgram().withName("${it.nameString}")
            swipeableProgram.identifier = it.id.toLong()

//                swipeableProgram.withIsSwipeable(x % 5 != 0) //123
//                swipeableProgram.withIsDraggable(x % 5 != 0) //123
            swipeableProgram.withIsSwipeable(true)
            swipeableProgram.withIsDraggable(true)

            swipeableProgram.deleteAction = Consumer { item -> delete(item) }
            swipeableProgram.editAction = Consumer { item -> edit(item) }
            //swipeableItem.shareAction = Consumer { item -> share(item) }
            listOfSwipeable.add(swipeableProgram)
            x++
        }

        return listOfSwipeable
    }

    fun swipeableDrawerProgramToProgram(itemsSwipeable: List<SwipeableDrawerProgram>):List<ProgramEntity>{

        var listOfProgram = mutableListOf<ProgramEntity>()

        var x = 0

        // TODO: 07.02.2021  !!!!!!!!!!!!!!
        val itemsArray: MutableList<ItemEntity> = mutableListOf()
        
        itemsSwipeable.forEach {
            val item = ProgramEntity(it.identifier.toInt(),it.name.toString(),itemsArray)
            listOfProgram.add(item)
        }

        return listOfProgram
    }

    override fun onSaveInstanceState(_outState: Bundle) {
        var outState = _outState
        //add the values which need to be saved from the adapter to the bundle
        outState = fastItemDrawerAdapter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }




//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        //handle the click on the back arrow click
//        return when (item.itemId) {
//            android.R.id.home -> {
//                onBackPressed()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
////        123 2
////        // Inflate the menu items for use in the action bar
////        val inflater = menuInflater
////        inflater.inflate(R.menu.search, menu)
////
////        //search icon
////        menu.findItem(R.id.search).icon = IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_search).apply { colorInt = Color.BLACK; actionBar() }
////
////        val searchView = menu.findItem(R.id.search).actionView as SearchView
////        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
////            override fun onQueryTextSubmit(s: String): Boolean {
////                touchCallback.setIsDragEnabled(false)
////                fastItemDrawerAdapter.filter(s)
////                return true
////            }
////
////
////            override fun onQueryTextChange(s: String): Boolean {
////                fastItemDrawerAdapter.filter(s)
////                touchCallback.setIsDragEnabled(TextUtils.isEmpty(s))
////                return true
////            }
////        })
//
//        return super.onCreateOptionsMenu(menu)
//    }



    private fun delete(item: SwipeableDrawerProgram) {
        item.deleteAction = null
        val position12 = fastItemDrawerAdapter.getAdapterPosition(item)
        if (position12 != RecyclerView.NO_POSITION) {
            //this sample uses a filter. If a filter is used we should use the methods provided by the filter (to make sure filter and normal state is updated)
            fastItemDrawerAdapter.itemFilter.remove(position12)
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()

            viewModel.deletePrograms(swipeableDrawerProgramToProgram(listOf(item))) //ich
        }
    }

    private fun edit(item: SwipeableDrawerProgram) {
        item.editAction = null
        val position12 = fastItemDrawerAdapter.getAdapterPosition(item)
        if (position12 != RecyclerView.NO_POSITION) {
            editCreateProgram(item.identifier.toInt(),item.name.toString()) //!!!!!!!!!!!!!!!!!!!!!!!
        }
    }





    override fun itemTouchOnMove(oldPosition: Int, newPosition: Int): Boolean {
        DragDropUtil.onMove(fastItemDrawerAdapter.itemAdapter, oldPosition, newPosition)  // change position
        return true
    }

    override fun itemTouchDropped(oldPosition: Int, newPosition: Int) {
        val vh: RecyclerView.ViewHolder? = rv.findViewHolderForAdapterPosition(newPosition)
        if (vh is IDraggableViewHolder) {
            (vh as IDraggableViewHolder).onDropped()
        }
        // save the new item order, i.e. in your database
    }

    override fun itemTouchStartDrag(viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is IDraggableViewHolder) {
            (viewHolder as IDraggableViewHolder).onDragged()
        }
    }


    override fun itemSwiped(position: Int, direction: Int) {
        var directionStr = ""
        if (ItemTouchHelper.LEFT == direction) directionStr = "left"
        else if (ItemTouchHelper.RIGHT == direction) directionStr = "right"
        println("Item $position swiped $directionStr")
    }

    override fun itemUnswiped(position: Int) {
        println("Item $position unswiped")
    }


}
