package deco.sleepp.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import deco.sleepp.R


/**
 * A simple [Fragment] subclass.
 */
class QuestionarioFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_questionario, container, false)
        return view
    }

}