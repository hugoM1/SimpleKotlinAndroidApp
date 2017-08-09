package com.mecalco.hugo.mykotlinapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mecalco.hugo.mykotlinapplication.R
import com.mecalco.hugo.mykotlinapplication.model.Characters

/**
 * @author by hugo on 8/7/17.
 */
class HomeFragmentAdapter: RecyclerView.Adapter<HomeFragmentAdapter.HomeFragmentViewHolder>() {

    var mCharacters: List<Characters.DataBean.ResultsBean> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeFragmentViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.home_fragment_item, parent, false)

        return HomeFragmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeFragmentViewHolder?, position: Int) {
        val itemCharacter = mCharacters[position]
        holder?.update(itemCharacter)
    }

    override fun getItemCount(): Int {
        return mCharacters.size
    }

    class HomeFragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var itemText  = itemView.findViewById(R.id.home_item_text) as TextView

        fun update(character: Characters.DataBean.ResultsBean){
            itemText.text = character.name
        }

    }

}