package app.superesenou.ru.neobisnoteapp.ui.add

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import app.superesenou.ru.neobisnoteapp.R
import kotlinx.android.synthetic.main.activity_add_info.*

class AddInfoActivity : AppCompatActivity(),AddInfoContract.View {

    lateinit var presenter: AddInfoPresenter

    var id=0
    var flag:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)
        setupBackButton()
        try{
            var bundle:Bundle=intent.extras
            id=bundle.getInt("ID",0)
            if(id!=0) {
                titleEt.setText(bundle.getString("name") )
                descEt.setText(bundle.getString("des") )
            }
        }catch (ex:Exception){}
        titleEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                init(true)
            }
        })

        descEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               init(true)
            }
        })
    }
    private fun setupBackButton() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
    }

    fun init(flag: Boolean){
        if(flag){
            presenter= AddInfoPresenter(this)
            presenter.save(this,titleEt.text.toString(),descEt.text.toString(),id,true)
        }
        else{
            presenter= AddInfoPresenter(this)
            presenter.save(this, titleEt.text.toString(), descEt.text.toString(), id, false)
       }
    }

    fun  btnAdd(view:View){
            flag=true
            init(false)
    }

    override fun onSuccssecFinish() {
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        flag=true
            if(!titleEt.text.equals("") || !descEt.text.equals(""))
                init(false)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!flag){
            if(!titleEt.text.equals("") || !descEt.text.equals(""))
                init(false)
        }
    }
}
