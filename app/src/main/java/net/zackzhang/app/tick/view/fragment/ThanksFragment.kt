package net.zackzhang.app.tick.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_thanks.*
import net.zackzhang.app.tick.R
import net.zackzhang.app.tick.view.adapter.LibraryListAdapter

class ThanksFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_thanks, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vLibraryList.adapter = LibraryListAdapter(activity!!)
        vLibraryList.setHasFixedSize(true)
    }
}