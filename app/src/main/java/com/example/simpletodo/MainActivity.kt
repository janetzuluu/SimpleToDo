package com.example.simpletodo

import android.os.Bundle
import android.os.FileUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    var listOfTasks= mutableListOf<String>()
    lateinit var adapter: taskitemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val onLongClickListner=object : taskitemAdapter.onLongClickListener{
            override fun onItemLongClicked(position: Int) {
              //remove the item from the list
                listOfTasks.removeAt(position)
            // notifiy adapater that our data set has changed
                adapter.notifyDataSetChanged()
                saveItems()
            }

        }
        //detect when user clicks the add button
        findViewById<Button>(R.id.button).setOnClickListener{

        }
loadItems()
        //look up the recycler view in the layout
        val recyclerView= findViewById<RecyclerView>(R.id.recyclerView)
        //pass sample of data into the adapter we created
         adapter=taskitemAdapter(listOfTasks,onLongClickListner)
        //attach recyler view with adapter
        recyclerView.adapter=adapter
        // Set layout manager to position the items
       recyclerView.layoutManager = LinearLayoutManager(this)
        val inputTextField=findViewById<EditText>(R.id.addTaskField)
        //set up button so that the user can enter a task to the list of there choice
        findViewById<Button>(R.id.button).setOnClickListener{
            //add the task into tge addtaskField
            val userInput=findViewById<EditText>(R.id.addTaskField).text.toString()
            //Add the string to the list of tasks
            listOfTasks.add(userInput)
            //notify adapter tjhat something has been added
            adapter.notifyItemInserted(listOfTasks.size-1)
            //clear out the text field for next input
            inputTextField.setText("")

saveItems()
        }
    }
    //save the data that the user inputted into the app.
    //by writting and reading from a file
    //create a method to get the file we need
    fun getDataFile(): File {
        //every line will represent a specific task
        return File(filesDir,"ToDoData.txt")
    }
    fun loadItems(){
        try{
        listOfTasks=org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset())}
        catch(ioException: IOException){
           ioException.printStackTrace()
        }

    }

    fun saveItems(){
        try{
        org.apache.commons.io.FileUtils.writeLines(getDataFile(),listOfTasks)
    }
        catch(ioException: IOException){
            ioException.printStackTrace()

        }        }
    //load the items by reading every line in the data file
}