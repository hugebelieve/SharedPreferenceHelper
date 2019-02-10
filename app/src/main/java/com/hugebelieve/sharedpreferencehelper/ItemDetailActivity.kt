package com.hugebelieve.sharedpreferencehelper

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.hugebelieve.sharedpreferencelibrary.SharedPref
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class ItemDetailActivity : AppCompatActivity() {


    private lateinit var fragment: ItemDetailFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { it ->
            val builder = AlertDialog.Builder(it.context)
            val mView = layoutInflater.inflate(R.layout.input_dialog, null)
            val prefMessage = mView.findViewById<TextInputEditText>(R.id.pref_message)
            val uniqueKey = intent.getStringExtra(ItemDetailFragment.ARG_ITEM_ID);
            builder.setView(mView)
                // Add action buttons
                .setPositiveButton("Submit"
                ) { dialog, id ->
                    if(!prefMessage.text.toString().isEmpty()){
                        //Here we are putting some value in our Shared Preference
                        SharedPref(baseContext).putData(uniqueKey, prefMessage.text.toString())

                        fragment = ItemDetailFragment().apply {
                            arguments = Bundle().apply {
                                putString(ItemDetailFragment.ARG_ITEM_ID,
                                    intent.getStringExtra(ItemDetailFragment.ARG_ITEM_ID))
                            }
                        }
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit()
                    }
                }
                .setNegativeButton("Cancel"
                ) { dialog, id ->
                    dialog.cancel()
                }
            builder.create().show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ItemDetailFragment.ARG_ITEM_ID,
                            intent.getStringExtra(ItemDetailFragment.ARG_ITEM_ID))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, ItemListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
