package com.izhandroid.gradecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import com.izhandroid.gradecalculator.databinding.ActivityMainBinding

private lateinit var b: ActivityMainBinding
var sub1 = 0
var sub2 = 0
var sub3 = 0
var sub4 = 0
var sub5 = 0
var total = 0
var secured = 0
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)


        b.button.setOnClickListener {
            if (!b.sub1ext.text.isNullOrEmpty() && !b.sub1exttotal.text.isNullOrEmpty()) {

                sub1 = zerofier(b.sub1ext)+zerofier(b.sub1int)
                sub2 = zerofier(b.sub2ext)+zerofier(b.sub2int)
                sub3 = zerofier(b.sub3ext)+zerofier(b.sub3int)
                sub4 = zerofier(b.sub4ext)+zerofier(b.sub4int)
                sub5 = zerofier(b.sub5ext)+zerofier(b.sub5int)

                secured = sub1+ sub2 + sub3 + sub4 + sub5

                total = zerofier(b.sub1exttotal)+zerofier(b.sub1inttotal)+
                        zerofier(b.sub2exttotal)+zerofier(b.sub2inttotal)+
                        zerofier(b.sub3exttotal)+zerofier(b.sub3inttotal)+
                        zerofier(b.sub4exttotal)+zerofier(b.sub4inttotal)+
                        zerofier(b.sub5exttotal)+zerofier(b.sub5inttotal)
                if(total>= secured) {
                    b.marks.text = secured.toString()
                    b.total.text = total.toString()
                    val percentile = ((secured.toDouble()/ total)*100).toInt()
                    b.gpa.text = (percentile.toDouble()/10).toString()
                    b.grade.text = when (percentile) {
                        in 0..35 -> "F"
                        in 36..50 -> "E"
                        in 51..60 -> "D"
                        in 61..70 -> "C"
                        in 71..80 -> "B"
                        in 81..90 -> "A"
                        in 91..100 -> "A+"
                        else -> ""
                    }
                    val subs = listOf(sub1, sub2, sub3, sub4, sub5)

                    b.topsub.text = when (subs.maxOrNull()) {
                        sub2 -> b.subject2.text
                        sub3 -> b.subject3.text
                        sub4 -> b.subject4.text
                        sub5 -> b.subject5.text
                        else -> b.subject1.text
                    }

                }else{
                    Toast.makeText(this, "Total marks cannot be more than secured marks. Please recheck", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Enter atleast 1 subject marks", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun zerofier(tv :TextView) :Int{
       return if (tv.text.isEmpty())0 else tv.text.toString().toInt()
    }
}


