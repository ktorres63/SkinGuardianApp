package com.idnp.skinguardianapp.ui.view.settings


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*


import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore


import com.idnp.skinguardianapp.databinding.FragmentSettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class SettingsFragment : Fragment() {

    companion object{
        const val KEY_DARK_MODE = "key_dark_mode"
        const val KEY_BLUETOOH = "key_bluetooh"
        const val KEY_VOLUME_LVL = "volume_lvl"
        const val KEY_NOTIFICATION = "key_notification"
    }

    private lateinit var binding: FragmentSettingsBinding
    var firstTime = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        // Acceder a las vistas directamente a través de binding
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect { settingsModel ->
                if (settingsModel != null) {
                    withContext(Dispatchers.Main) {
                        // Acceder al hilo principal para actualizar la interfaz de usuario
                        binding.switchBluetooh.isChecked = settingsModel.bluetooh
                        binding.switchDarkMode.isChecked = settingsModel.darkMode
                        binding.switchNotification.isChecked = settingsModel.notification
                        binding.seekBarVolume.progress = settingsModel.volume
                        firstTime = false
                    }
                }
            }
        }


        initUI()
        return binding.root
    }
    private fun initUI() {

        val seekBar = binding.seekBarVolume
        val textViewProgress = binding.textViewProgress

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Se llama cuando cambia el progreso del SeekBar
                textViewProgress.text = "Valor: $progress"
                Log.i("IMPRIME","Valor: $progress")
                CoroutineScope(Dispatchers.IO).launch {
                    saveVolume(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Se llama cuando el usuario toca el SeekBar
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Se llama cuando el usuario deja de tocar el SeekBar
            }
        })

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // El valor de isChecked indica si el Switch está activado (true) o desactivado (false)
            Log.i("IMPRIME", "Switch activado: $isChecked")

            // Guardar el estado del Switch en el DataStore
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, isChecked)
            }
        }

        binding.switchBluetooh.setOnCheckedChangeListener { _, isChecked ->
            // El valor de isChecked indica si el Switch está activado (true) o desactivado (false)
            Log.i("IMPRIMEE", "Switch activado: $isChecked")

            // Guardar el estado del Switch en el DataStore
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOH, isChecked)
            }
        }
        binding.switchNotification .setOnCheckedChangeListener { _, isChecked ->
            // El valor de isChecked indica si el Switch está activado (true) o desactivado (false)
            Log.i("IMPRIME", "Switch activado: $isChecked")

            // Guardar el estado del Switch en el DataStore
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_NOTIFICATION, isChecked)
            }
        }

    }

    // Función para guardar el volumen en el DataStore
    private suspend fun saveVolume(value: Int) {
        // Acceder al DataStore desde el contexto del fragmento
        requireContext().dataStore.edit { preferences ->
            preferences[intPreferencesKey(KEY_VOLUME_LVL)] = value
        }
    }
    private suspend fun saveOptions(key: String, value: Boolean){
        val pref = requireContext().dataStore
        requireContext().dataStore.edit  { preferences ->
            preferences[booleanPreferencesKey(key)] = value; }

    }

    private suspend fun getSettings(): Flow<SettingsModel> = flow {
        val dataStore = requireContext().dataStore

        // Collect the latest data from the data store
        dataStore.data.collect { preferences ->
            emit(
                SettingsModel(
                    darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: false,
                    bluetooh = preferences[booleanPreferencesKey(KEY_BLUETOOH)] ?: false,
                    volume = preferences[intPreferencesKey(KEY_VOLUME_LVL)] ?: 50,
                    notification = preferences[booleanPreferencesKey(KEY_NOTIFICATION)] ?: false
                )
            )
        }
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        (requireActivity() as? AppCompatActivity)?.delegate?.apply {
            localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        }
        requireActivity().recreate()

    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        (requireActivity() as? AppCompatActivity)?.delegate?.apply {
            localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        }
        requireActivity().recreate()
    }
}

