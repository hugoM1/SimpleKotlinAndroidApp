package com.mecalco.hugo.mykotlinapplication.home

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.mecalco.hugo.mykotlinapplication.MarvelApp
import com.mecalco.hugo.mykotlinapplication.R
import com.mecalco.hugo.mykotlinapplication.adapter.HomeFragmentAdapter
import com.mecalco.hugo.mykotlinapplication.model.Characters
import com.mecalco.hugo.mykotlinapplication.service.MarvelApiService
import retrofit2.Response

/**
 * @author by hugo on 8/7/17.
 */
class FragmentHome: Fragment() {

    companion object{
        val TAG = "FragmentHome"
        val fragmentHome = FragmentHome()
        lateinit var mApiService: MarvelApiService
        lateinit var mAdapter: HomeFragmentAdapter
        lateinit var mLoadingProgress : ProgressBar
    }

    lateinit var mRecyclerView: RecyclerView
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter = HomeFragmentAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater!!.inflate(R.layout.fragment_main, container, false)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        mRecyclerView.adapter = mAdapter

        mApiService = MarvelApiService(MarvelApp.mAPI)
        RetrieveCharactersTask().execute(TAG)
    }

    fun initViews(view: View?){
        mLoadingProgress = view?.findViewById(R.id.loading_progress) as ProgressBar
        mLoadingProgress.visibility = View.VISIBLE

        mRecyclerView = view.findViewById(R.id.home_recycler_view) as RecyclerView
        mRecyclerView.setHasFixedSize(true)

        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = mLayoutManager
    }

    class RetrieveCharactersTask : AsyncTask<String, Void, Response<Characters>?>() {
        override fun doInBackground(vararg p0: String?): Response<Characters>? {
            return mApiService.getCharacters()
        }

        override fun onPostExecute(result: Response<Characters>?) {
            mAdapter.mCharacters = result?.body()?.data?.results!!
            mLoadingProgress.visibility = View.INVISIBLE
        }
    }

}