package app.superesenou.ru.neobisnoteapp.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import app.superesenou.ru.neobisnoteapp.R
import app.superesenou.ru.neobisnoteapp.model.Note
import app.superesenou.ru.neobisnoteapp.ui.add.AddInfoActivity
import kotlinx.android.synthetic.main.activity_main.*
import app.superesenou.ru.neobisnoteapp.R.id.fab
import app.superesenou.ru.neobisnoteapp.data.DbManager


class MainActivity : AppCompatActivity(),MainContract.View,MainAdapter.Listener {

    lateinit var presenter: MainPresenter
    lateinit var adapter: MainAdapter
    var context:Context=this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        fab.setOnClickListener {
            var intent= Intent(this,AddInfoActivity::class.java)
            startActivity(intent)
        }
    }
    override  fun onResume() {
        super.onResume()
        init()
    }

    fun init(){
        presenter= MainPresenter(this)
        presenter.getLoadQueary("%",this)
    }

    override fun onSuccssecLoadQuery(list: ArrayList<Note>) {
        recyclerViewMain.layoutManager= GridLayoutManager(this,1)
        adapter= MainAdapter(list,this,this)
        recyclerViewMain.adapter=adapter
    }
    override fun setOnItemClick(position: Note) {

        val intent=Intent(this,AddInfoActivity::class.java)
        intent.putExtra("ID",position.ID)
        intent.putExtra("name",position.Name)
        intent.putExtra("des",position.Description)
        startActivity(intent)
    }

    override fun setOnItemClickDelete(position: Note) {
       var dbManager= DbManager(this)
        val selectionArgs= arrayOf(position.ID.toString())
        dbManager.Delete("ID=?",selectionArgs)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val sv: SearchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        val sm= getSystemService(Context.SEARCH_SERVICE) as SearchManager

        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                presenter.getLoadQueary("%"+ query +"%",context)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId){
                R.id.addNote->{
                    var intent= Intent(this,AddInfoActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
