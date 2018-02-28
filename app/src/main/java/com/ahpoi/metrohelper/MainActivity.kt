package com.ahpoi.metrohelper

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.ahpoi.metrohelper.manager.ApiManager
import com.ahpoi.metrohelper.model.AppUser
import com.ahpoi.metrohelper.model.Journey
import com.ahpoi.metrohelper.model.JourneyResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.Extra

class JourneyAdapter(private val journeys: List<Journey>) : RecyclerView.Adapter<JourneyAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fromLocation.text = journeys[position].fromLocation
        holder.toLocation.text = journeys[position].toLocation
        holder.description.text = journeys[position].description ?: "n/a" // ?:Elvis operator to do a null check
        holder.departureTime.text = journeys[position].departureTime

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_plan, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return journeys.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fromLocation = itemView.findViewById<TextView>(R.id.fromLocation)
        val toLocation = itemView.findViewById<TextView>(R.id.toLocation)
        val description = itemView.findViewById<TextView>(R.id.description)
        val departureTime = itemView.findViewById<TextView>(R.id.departureTime)
    }

}

@EActivity(R.layout.activity_main)
class MainActivity : AppCompatActivity() {

    @Extra
    lateinit var user: AppUser

    @Bean(ApiManager::class)
    lateinit var apiManager: ApiManager

    @AfterViews
    fun init() {
        welcomeUserMsg()
        val journeys: List<Journey> = apiManager
                .getJourneys(userId = user.id).journeys.map {Journey.fromResponse(it) } //What is it??
        val adapter = JourneyAdapter(journeys)
        val recycleView = journeyListRV.apply {
            setAdapter(adapter)
            setItemViewCacheSize(10)
        }
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }

    //Can omit the return type `Unit`
    private fun welcomeUserMsg(): Unit {
        //String templates accessing properties of an object
        Snackbar.make(rootLayout, "Welcome: $user", Snackbar.LENGTH_LONG).show()
    }

}