package me.imzack.app.tick.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_problem.*
import me.imzack.app.tick.R
import me.imzack.app.tick.model.bean.Problem
import me.imzack.app.tick.util.ResourceUtil

class ProblemListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mProblems: Array<Problem>

    init {
        val descriptions = ResourceUtil.getStringArray(R.array.problem_descriptions)
        val solutions = ResourceUtil.getStringArray(R.array.problem_solutions)
        mProblems = Array(descriptions.size, { Problem(descriptions[it], solutions[it]) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_problem, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as ItemViewHolder
        val (description, solution) = mProblems[position]
        itemViewHolder.vDescriptionText.text = description
        itemViewHolder.vSolutionText.text = solution
    }

    override fun getItemCount() = mProblems.size

    class ItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}
