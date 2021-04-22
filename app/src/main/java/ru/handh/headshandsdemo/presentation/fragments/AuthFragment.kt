package ru.handh.headshandsdemo.presentation.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import ru.handh.headshandsdemo.R
import ru.handh.headshandsdemo.data.repositories.WeatherRepoImpl
import ru.handh.headshandsdemo.databinding.FragmentAuthBinding
import ru.handh.headshandsdemo.domain.interactors.WeatherInteractor
import ru.handh.headshandsdemo.domain.models.WeatherDomain
import ru.handh.headshandsdemo.presentation.hideKeyboard
import ru.handh.headshandsdemo.presentation.onTouchDrawableEnd
import ru.handh.headshandsdemo.presentation.setSupportActionBar
import ru.handh.headshandsdemo.presentation.showTooltip
import ru.handh.headshandsdemo.presentation.utils.InputMask
import ru.handh.headshandsdemo.presentation.viewmodels.WeatherViewModel
import ru.handh.headshandsdemo.presentation.viewmodels.getViewModel


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    // TODO: Делегировать на DI или Service Locator
    private val weatherRepo by lazy { WeatherRepoImpl() }
    private val weatherInteractor by lazy { WeatherInteractor(weatherRepo) }
    private val weatherViewModel by lazy { getViewModel { WeatherViewModel(weatherInteractor) } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_auth, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthBinding.bind(view)

        setSupportActionBar(binding.tbToolbar, getString(R.string.toolbar_title_auth))

        binding.tilPassword.editText?.onTouchDrawableEnd { editText ->
            editText.showTooltip(context?.getString(R.string.password_tooltip))
        }

        binding.mbLogin.setOnClickListener {
            checkInputs {
                loadWeather()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_auth, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // TODO: Доп логику убрать в Extensions или ViewModel

    private fun checkInputs(successCallback: () -> Unit) {
        if (checkEmail() && checkPassword()) {
            successCallback()
        }
    }

    private fun checkInput(
        textInputLayout: TextInputLayout,
        condition: Boolean,
        errorText: String
    ): Boolean {
        textInputLayout.editText?.addTextChangedListener {
            textInputLayout.error = null
        }

        if (!condition) {
            textInputLayout.error = errorText
            return !condition
        }
        return condition
    }

    private fun checkEmail(): Boolean {
        return checkInput(
            binding.tilEmail,
            InputMask.isEmailValid(binding.tilEmail.editText?.text.toString()),
            "Указан некорректный email"
        )
    }

    private fun checkPassword(): Boolean {
        return checkInput(
            binding.tilPassword,
            InputMask.isPasswordValid(binding.tilPassword.editText?.text.toString()),
            "Указан некорректный пароль"
        )
    }

    private fun loadWeather() {
        val city = "Moscow"
        weatherViewModel.getWeather(city).observe(viewLifecycleOwner, { weatherDomain ->
            binding.root.hideKeyboard()

            if (weatherDomain == null) {
                showErrorToast()
            } else {
                showWeather(weatherDomain)
            }
        })
    }

    private fun showErrorToast() {
        Toast.makeText(context, getString(R.string.error_toast), Toast.LENGTH_LONG).show()
    }

    private fun showWeather(weatherDomain: WeatherDomain) {
        Snackbar.make(
            binding.root,
            "${weatherDomain.city} ${weatherDomain.temp}",
            Snackbar.LENGTH_LONG
        ).show()
    }

    companion object {

        const val TAG = "AuthFragment"

        fun newInstance(): AuthFragment {
            return AuthFragment()
        }
    }
}