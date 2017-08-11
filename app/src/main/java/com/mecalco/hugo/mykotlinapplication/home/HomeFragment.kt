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
class HomeFragment : Fragment() {

    companion object {
        @JvmField
        val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            return fragment
        }
    }

    lateinit var mAdapter: HomeFragmentAdapter
    lateinit var mLoadingProgress: ProgressBar
    lateinit var mRecyclerView: RecyclerView
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAdapter = HomeFragmentAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = null
        inflater?.let {
            view = it.inflate(R.layout.fragment_main, container, false)
        }

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        mRecyclerView.adapter = mAdapter

        RetrieveCharactersTask().execute(TAG)
    }

    fun initViews(view: View?) {
        view?.let {
            mLoadingProgress = view.findViewById<ProgressBar>(R.id.loading_progress)
            mLoadingProgress.visibility = View.VISIBLE

            mRecyclerView = view.findViewById<RecyclerView>(R.id.home_recycler_view)
            mRecyclerView.setHasFixedSize(true)
        }

        mLayoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager
        mRecyclerView.layoutManager = mLayoutManager
    }

    @SuppressWarnings("StaticFieldLeak")
    inner class RetrieveCharactersTask : AsyncTask<String, Void, Response<Characters>?>() {

        val mApiService: MarvelApiService = MarvelApiService(MarvelApp.mAPI)

        override fun doInBackground(vararg p0: String?): Response<Characters>? {
            return mApiService.getCharacters()
        }

        override fun onPostExecute(result: Response<Characters>?) {
            val results = result?.body()?.data?.results
            results?.let {
                mAdapter.mCharacters = it
            }

            mLoadingProgress.visibility = View.GONE
        }

    }

}