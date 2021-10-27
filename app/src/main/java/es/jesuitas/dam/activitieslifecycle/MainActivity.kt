package es.jesuitas.dam.activitieslifecycle

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import es.jesuitas.dam.activitieslifecycle.databinding.ActivityMainBinding

const val KEY_TEXT = "text_key"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("MainActivity", "onCreate Called")
        if (savedInstanceState != null) {
            binding.editText.setText(savedInstanceState.getString(KEY_TEXT, "Nada"))
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop Called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val text : String = binding.editText.text.toString()
        outState.putString(KEY_TEXT, text)
        Log.i("MainActivity","onSaveInstanceState Called")
    }

        override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart Called")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareMenuButton -> onShare()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onShare() {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, R.string.share_text)
            type = "text/plain"
        }
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG).show()
        }
    }
}